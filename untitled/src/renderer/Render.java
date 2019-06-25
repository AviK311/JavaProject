package renderer;


import elements.PointLight;
import scene.*;
import elements.LightSource;
import geometries.*;
import primitives.Point3D;
import geometries.Intersectable.GeoPoint;


import primitives.Ray;
//import java.awt.Color;
import primitives.Color;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Render {


    private Scene _scene;
    private ImageWriter _imageWriter;
    private ExecutorService threadPool;
    private int remainingColumns = 0;
    private long renderStartTime = 0;
    private final static double EPSILON = 0.001;
    private final static double MINIMUM_K = 0.0001;
    private final static double MINIMUM_KTR = 0.000001;
    private final static int MAX_CALC_COLOR = 5;
    private final static int CORES = Runtime.getRuntime().availableProcessors();

    /**
     * class for running in threadpool.
     * in charge of writing a column
     */
    private class columnWriter implements Runnable {
        int nx, ny, columnNumber;
        double dist, width, height;

        /**
         * ctor with all params needed for the column
         *
         * @param nx
         * @param ny
         * @param columnNumber
         * @param dist
         * @param width
         * @param height
         */
        public columnWriter(int nx, int ny, int columnNumber, double dist, double width, double height) {
            this.nx = nx;
            this.ny = ny;
            this.columnNumber = columnNumber;
            this.dist = dist;
            this.width = width;
            this.height = height;
        }

        /**
         * function that the thread pool calls
         */
        @Override
        public void run() {
            for (int j = 0; j < nx; j++) {

                ArrayList<Ray> rays = _scene.getCamera().constructRaysThroughAPixel(nx, ny, j, columnNumber, dist, width, height);
                Color color = Color.BLACK;
                for (Ray ray : rays) {

                    //Ray ray = _scene.getCamera().constructRayThroughAPixel(nx, ny, j, i, dist, width, height);
                    List<GeoPoint> intersectionPoints = new ArrayList<>();
                    if (_scene.checkBoundaries(ray)) {
                        intersectionPoints = _scene.getGeometries(0).findIntersections(ray);
                    } else if (_scene.getGeometries(1).getSize() > 0) {
                        intersectionPoints = _scene.getGeometries(1).findIntersections(ray);
                    }
//
                    if (intersectionPoints == null || intersectionPoints.isEmpty()) {
                        //_imageWriter.writePixel(i, j, _scene.getBackground().getColor());
                        color = color.add(_scene.getBackground());
                    } else {
                        GeoPoint closetPoint = getClosestPoint(intersectionPoints, _scene.getCamera().getP0());
                        //_imageWriter.writePixel(i, j, calcColor(closetPoint, ray).getColor());
                        color = color.add(calcColor(closetPoint, ray));

                    }
                    if (intersectionPoints != null)
                        intersectionPoints.clear();
                }
                color = color.scale((double) 1 / rays.size());
                _imageWriter.writePixel(columnNumber, j, color.getColor());
            }
            remainingColumns--;
            if (remainingColumns % 10 == 0)
                System.out.println("columns remaining = " + remainingColumns);
            if (remainingColumns == 0) {
                threadPool.shutdown();
                _imageWriter.writeToimage();
                long elapsedTime = System.currentTimeMillis() - renderStartTime;
                double seconds = elapsedTime * 0.001;
                int minutes = (int) seconds / 60;
                seconds -= minutes * 60;
//                elapsedTime*=0.001;
//                long hours = elapsedTime/3600;
//                elapsedTime-=hours*3600;
//                long minutes = elapsedTime/60;
//                long seconds = elapsedTime - minutes*60;
//                System.out.println("Runtime = " + hours+"/"+minutes+"/"+seconds);
                System.out.println("Runtime = " + minutes + " minutes and " + (int) seconds + " seconds");

            }

        }
    }

    /**
     * ctor
     * in the ctor i initialize the threadpool with 1 less core that the computer has,
     * so i can still use the CPU for other things
     *
     * @param _scene
     * @param imageWriter
     */
    public Render(Scene _scene, ImageWriter imageWriter) {
        this._scene = _scene;
        this._imageWriter = imageWriter;
        threadPool = Executors.newFixedThreadPool(CORES - 1);

    }

    /**
     * returns the scene
     *
     * @return
     */
    public Scene getScene() {
        return _scene;
    }

    /**
     * returns the image writer
     *
     * @return
     */
    public ImageWriter getImageWriter() {
        return _imageWriter;
    }

    /**
     * creates the image using the scene
     */
    public void renderImage() {
        renderStartTime = System.currentTimeMillis();
        int nx = _imageWriter.getNx();
        int ny = _imageWriter.getNy();
        double height = _imageWriter.getHeight();
        double width = _imageWriter.getWidth();
        double dist = _scene.getScreenDistance();
        remainingColumns = nx;
        double r = 0, g = 0, b = 0;
        for (int i = 0; i < ny; i++) {
            threadPool.execute(new columnWriter(nx, ny, i, dist, width, height));
//            new columnWriter(nx,ny,i,dist,width,height).run();
        }
//        long elapsedTime =  System.currentTimeMillis() - renderStartTime;
//        System.out.println(elapsedTime * 0.001 + " Seconds");


    }

    /**
     * comfortable face for the longer call of calcColor
     *
     * @param gp
     * @param inRay
     * @return
     */
    private Color calcColor(GeoPoint gp, Ray inRay) {
        return calcColor(gp, inRay, MAX_CALC_COLOR, 1).add(_scene.ambientLight.getIntensity());
    }

    /**
     * calculates the color of the pixel according the the color of the geo, lights, directions, etc
     *
     * @param geoPoint
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        if (level == 0 || k < MINIMUM_K)
            return Color.BLACK;
        Geometry g = geoPoint.geo;
        Point3D p = geoPoint.point;
        Color returnColor = new Color(_scene.getAmbientLight().getIntensity());
        returnColor = returnColor.add(g.getEmission());
        Vector v = inRay.getDirection();
        Vector n = g.getNormal(p);
        if (g instanceof Plane && n.dotProduct(v) > 0)
            n = n.scale(-1);

        int nShininess = g.getnShininess();
        double kd = g.get_material().getKd();
        double ks = g.get_material().getKs();
        for (LightSource light : _scene.getLights()) {
            Vector l = light.getL(p);
            if (n.dotProduct(l) * n.dotProduct(v) > 0) {
                double distanceLimit = Double.MAX_VALUE;
                if (light instanceof PointLight)
                    distanceLimit = p.distance(((PointLight) light).getPosition());
                double ktr = transparency(l, geoPoint, n, distanceLimit);
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
        if (kr != 0) {
            Ray reflectedRay = constructReflectedRay(n, p, inRay);
            GeoPoint gpReflect = findClosestIntersection(reflectedRay);
            if (gpReflect != null)
                reflectedLight = calcColor(gpReflect, reflectedRay, level - 1, k * kr).scale(kr);
        }

        double kt = g.get_material().getKt();
        Color refractedLight = Color.BLACK;
        if (kt != 0) {
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
        double dn = n.dotProduct(l) * 2;
        Vector r = l.subtract(n.scale(dn));
        double vrDotProduct = r.dotProduct(v);
        if (vrDotProduct >= 0)
            return Color.BLACK;
        double res = Math.pow(-vrDotProduct, shininess);
        return intensity.scale(ks * res);
    }



    /**
     * checks whether the geometry is shadowed by another geometry
     *
     * @param l      direction from light
     * @param gp     geopoint
     * @param normal of geometry at point
     * @param maxDistance geomeries beyond this distance are not in the way of the light
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
        blockers.removeIf(geoPoint -> geoPoint.point.distance(gp.point) > maxDistance);
        double ktr = 1;
        for (GeoPoint geoPoint : blockers)
            ktr *= geoPoint.geo.get_material().getKt();
        return ktr;

    }

    /**
     * calculates the ray that bounces off the geometry
     *
     * @param n
     * @param point
     * @param inRay
     * @return ray
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
        Vector l = inRay.getDirection();
        double dn = n.dotProduct(l) * 2;
        return new Ray(l.subtract(n.scale(dn)), point.add(n.scale(EPSILON)));
    }

    /**
     * ד
     * calculates the ray that comes out the other side of a geometry
     *
     * @param point
     * @param inRay
     * @return ray
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
        return new Ray(inRay.getDirection(), point.add(n.scale(-EPSILON)));
    }

    /**
     * calculates the closest point to the ray head
     *
     * @param ray
     * @return geopoint
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> l = _scene.getGeometries(0).findIntersections(ray);
        return getClosestPoint(l, ray.getHead());

    }
}
