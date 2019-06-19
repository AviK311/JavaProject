package renderer;


import elements.PointLight;
import scene.*;
import elements.LightSource;
import geometries.*;
import primitives.Point3D;
import geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import primitives.Ray;
//import java.awt.Color;
import primitives.Color;
import primitives.Vector;

import static primitives.Util.uscale;

public class Render {
    int totalTheoreticalRays = 0, totalActualRays = 0;
    int totalTheoreticalIntersections = 0, totalActualIntesections = 0;
    private Scene _scene;
    private ImageWriter _imageWriter;
    private final static double EPSILON = 0.001;
    private final static double MINIMUM_K = 0.0001;
    private final static double MINIMUM_KTR = 0.000001;
    private final static int MAX_CALC_COLOR = 10;

    public Render(Scene _scene, ImageWriter imageWriter) {
        this._scene = _scene;
        this._imageWriter = imageWriter;
    }

    public Scene getScene() {
        return _scene;
    }

    public ImageWriter getImageWriter() {
        return _imageWriter;
    }

    /**
     * creates the image using the scene
     */
    public void renderImage() {
        int nx = _imageWriter.getNx();
        int ny = _imageWriter.getNy();
        double height = _imageWriter.getHeight();
        double width = _imageWriter.getWidth();
        double dist = _scene.getScreenDistance();
        totalTheoreticalRays = nx*ny;
        totalTheoreticalIntersections = totalTheoreticalRays * _scene.getGeometries(0).getSize();
        double r=0, g=0, b=0;
        for (int i = 0; i < ny; i++) {
            for (int j = 0; j < nx; j++) {
                ArrayList<Ray> rays = _scene.getCamera().constructRaysThroughAPixel(nx, ny, j, i, dist, width, height);
                for (Iterator<Ray> iterator = rays.iterator(); iterator.hasNext();) 
                {
                    //Ray ray = _scene.getCamera().constructRayThroughAPixel(nx, ny, j, i, dist, width, height);
                    Ray ray = iterator.next();
                    List<GeoPoint> intersectionPoints = new ArrayList<>();
                    if (_scene.checkBoundaries(ray)) {
                        intersectionPoints = _scene.getGeometries(0).findIntersections(ray);
                        totalActualRays++;
                        totalActualIntesections += _scene.getGeometries(0).getSize();
                    } else if (_scene.getGeometries(1).getSize() > 0) {
                        intersectionPoints = _scene.getGeometries(1).findIntersections(ray);
                        totalActualRays++;
                        totalActualIntesections += _scene.getGeometries(1).getSize();
                    }
//
                    if (intersectionPoints.isEmpty()) {
                        //_imageWriter.writePixel(i, j, _scene.getBackground().getColor());
                        r+=_scene.getBackground().getColor().getRed();
                        g+=_scene.getBackground().getColor().getGreen();
                        b+=_scene.getBackground().getColor().getBlue();
                    } else {
                        GeoPoint closetPoint = getClosestPoint(intersectionPoints, _scene.getCamera().getP0());
                        //_imageWriter.writePixel(i, j, calcColor(closetPoint, ray).getColor());
                        r+=calcColor(closetPoint, ray).getColor().getRed();
                        g+=calcColor(closetPoint, ray).getColor().getGreen();
                        b+=calcColor(closetPoint, ray).getColor().getBlue();

                    }
                    intersectionPoints.clear();
                }
                Color color = new Color(r/5,g/5,b/5);
                _imageWriter.writePixel(i, j, color.getColor());
            }
        }
        System.out.println("the total number of rays should have been: " + totalTheoreticalRays);
        System.out.println("the total ray/geo intersection tests should have been: " + totalTheoreticalIntersections);
        System.out.println("the actual number of rays: " + totalActualRays);
        System.out.println("the actual ray/geo intersection tests: " + totalActualIntesections);

        _imageWriter.writeToimage();
    }

    /**
     * comfortable face for the longer call of calcColor
     * @param gp
     * @param inRay
     * @return
     */
    private Color calcColor(GeoPoint gp, Ray inRay){
        return calcColor(gp, inRay, MAX_CALC_COLOR, 1).add(_scene.ambientLight.getIntensity());
    }

    /**
     * calculates the color of the pixel according the the color of the geo, lights, directions, etc
     *
     * @param geoPoint
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        if (level==0||k<MINIMUM_K)
            return Color.BLACK;
        Geometry g = geoPoint.geo;
        Point3D p = geoPoint.point;
        Color returnColor = new Color(_scene.getAmbientLight().getIntensity());
        returnColor = returnColor.add(g.getEmission());
        Vector v = inRay.getDirection();
        Vector n = g.getNormal(p);
        int nShininess = g.getnShininess();
        double kd = g.get_material().getKd();
        double ks = g.get_material().getKs();
        for (LightSource light : _scene.getLights()) {
            Vector l = light.getL(p);
            if (n.dotProduct(l) * n.dotProduct(v) > 0) {
                double distanceLimit = Double.MAX_VALUE;
                if (light instanceof PointLight)
                    distanceLimit = p.distance(((PointLight)light).getPosition());
                double ktr = transparency(l,geoPoint,n, distanceLimit);
                if (ktr > MINIMUM_KTR) {
                    Color lightIntensity = light.getIntensity(p).scale(ktr);
                    Color diff = calcDiffusive(kd, l, n, lightIntensity);
                    Color spec = calcSpecular(ks, l, n, v, lightIntensity, nShininess);
                    returnColor = returnColor.add(diff, spec);
                }
            }
        }

        double kr = g.get_material().getKr();
        Color reflectedLight = Color.BLACK;
        if (kr!=0) {
            Ray reflectedRay = constructReflectedRay(n, p, inRay);
            GeoPoint gpReflect = findClosestIntersection(reflectedRay);
            if (gpReflect != null)
                reflectedLight = calcColor(gpReflect, reflectedRay, level - 1, k * kr).scale(kr);
        }

        double kt = g.get_material().getKt();
        Color refractedLight = Color.BLACK;
        if (kt!=0) {
            Ray refractedRay = constructRefractedRay(n, p, inRay);
            GeoPoint gpRefract = findClosestIntersection(refractedRay);
            if (gpRefract != null)
                refractedLight = calcColor(gpRefract, refractedRay, level - 1, k * kt).scale(kt);
        }
        returnColor = returnColor.add(reflectedLight, refractedLight);
        return returnColor;
    }

    /**
     * prints a grid on the screen
     *
     * @param interval
     */
    public void printGrid(int interval) {
        int nx = _imageWriter.getNx();
        int ny = _imageWriter.getNy();
        for (int i = 0; i < ny; i++) {
            for (int j = 0; j < nx; j++) {
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(i, j, java.awt.Color.WHITE);
            }
        }
        _imageWriter.writeToimage();
    }

    /**
     * returns the closes point to the camera origin on the pointList
     *
     * @param pointList
     * @return GeoPoint
     */
    private GeoPoint getClosestPoint(List<GeoPoint> pointList, Point3D p0) {
        if (pointList.isEmpty()) return null;
        GeoPoint closestPoint = pointList.get(0);
        double distance = closestPoint.point.distance(p0);
        for (GeoPoint p : pointList) {
            double temp = p.point.distance(p0);
            if (temp < distance) {
                closestPoint = p;
                distance = temp;
            }
        }
        return closestPoint;
    }

    /**
     * calculates intensity*(l*n)*kd
     *
     * @param kd
     * @param l
     * @param n
     * @param intensity
     * @return color
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color intensity) {
        double ln = Math.abs(n.dotProduct(l));
        return intensity.scale(kd * ln);
    }

    /**
     * calculates ks(v*r)^n*iL
     *
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param intensity
     * @param shininess
     * @return color
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, Color intensity, int shininess) {
        double dn = n.dotProduct(l)*2;
        Vector r = l.subtract(n.scale(dn));
        double vrDotProduct = r.dotProduct(v);
        if (vrDotProduct >= 0)
            return Color.BLACK;
        double res = Math.pow(-vrDotProduct, shininess);
        return intensity.scale(ks*res);
    }

    public ImageWriter get_imageWriter() {
        return _imageWriter;
    }

    /**
     * checks whether the geometry is shadowed by another geometry
     * @param l direction from light
     * @param gp geopoint
     * @param normal of geometry at point
     * @return true of false
     */
    private double transparency(Vector l, GeoPoint gp, Vector normal, double maxDistance) {
        Vector lightDirection = l.scale(-1);
//        lightDirection = l;
        Vector epsVector = normal.scale(normal.dotProduct(lightDirection) > 0 ? EPSILON : -EPSILON);
//        Vector epsVector = normal.scale(2);
        Point3D lightPoint = gp.point.add(epsVector);
        Ray rayToLight = new Ray(lightDirection, lightPoint);
        List<GeoPoint> blockers = _scene.getGeometries(0).findIntersections(rayToLight);
        blockers.removeIf(geoPoint -> geoPoint.point.distance(gp.point)>maxDistance);
        double ktr = 1;
        for (GeoPoint geoPoint:blockers)
            ktr*=geoPoint.geo.get_material().getKt();
        return ktr;

    }

    /**
     * calculates the ray that bounces off the geometry
     * @param n
     * @param point
     * @param inRay
     * @return ray
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay){
        Vector l = inRay.getDirection();
        double dn = n.dotProduct(l)*2;
        return new Ray(l.subtract(n.scale(dn)), point.add(n.scale(EPSILON)));
    }

    /**ד
     * calculates the ray that comes out the other side of a geometry
     * @param point
     * @param inRay
     * @return ray
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay){
        return new Ray(inRay.getDirection(), point.add(n.scale(-EPSILON)));
    }

    /**
     * calculates the closest point to the ray head
     * @param ray
     * @return geopoint
     */
    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> l = _scene.getGeometries(0).findIntersections(ray);
        return getClosestPoint(l, ray.getHead());

    }
}
