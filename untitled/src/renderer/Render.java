package renderer;


import scene.*;
import elements.LightSource;
import geometries.*;
import primitives.Point3D;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import primitives.Ray;
//import java.awt.Color;
import primitives.Color;
import primitives.Vector;

public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

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
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            for (int j = 0; j < _imageWriter.getWidth(); j++) {
                Ray ray = _scene.getCamera().constructRayThroughAPixel(_imageWriter.getNx(),
                        _imageWriter.getNy(), j, i, _scene.getScreenDistance(), _imageWriter.getWidth(),
                        _imageWriter.getHeight());
                List<GeoPoint> intersectionPoints = _scene.getGeometries().findIntersections(ray);
                if (intersectionPoints.isEmpty()) {
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                } else {
                    GeoPoint closetPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closetPoint));
                }
            }
        }
        _imageWriter.writeToimage();
    }

    /**
     * calculates the color of the pixel according the the color of the geo, lights, directions, etc
     *
     * @param geoPoint
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint) {
        Geometry g = geoPoint.geo;
        Point3D p = geoPoint.point;
        Color returnColor = new Color(_scene.getAmbientLight().getIntensity());
        returnColor = returnColor.add(g.getEmission());
        Vector v = p.subtract(_scene.getCamera().getP0()).normalize();
        Vector n = g.getNormal(p);
        int nShininess = g.getnShininess();
        double kd = g.get_material().get_Kd();
        double ks = g.get_material().get_Ks();
        for (LightSource light : _scene.getLights()) {
            Vector l = light.getL(p);
            if (n.dotProduct(l) * n.dotProduct(v) > 0) {
                Color lightIntensity = light.getIntensity(p);
                Color diff = calcDiffusive(kd, l, n, lightIntensity);
                Color spec = calcSpecular(ks, l, n, v, lightIntensity, nShininess);
                returnColor = returnColor.add(diff, spec);
            }
        }
        return returnColor;
    }

    /**
     * prints a grid on the screen
     *
     * @param interval
     */
    public void printGrid(int interval) {
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            for (int j = 0; j < _imageWriter.getWidth(); j++) {
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(i, j, 255, 255, 255);
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
    private GeoPoint getClosestPoint(List<GeoPoint> pointList) {
        if (pointList.isEmpty()) return null;
        Point3D p0 = _scene.getCamera().getP0();
        GeoPoint closestPoint = pointList.get(0);
        for (GeoPoint p : pointList)
            if (p.point.distance(p0) < closestPoint.point.distance(p0))
                closestPoint = p;
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
        double ln = java.lang.Math.abs(l.dotProduct(n));
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
        Vector r = l.subtract(n.scale(2 * n.dotProduct(l)));
        double dotProduct = r.dotProduct(v.scale(-1));
        if (dotProduct < 0) dotProduct = 0;
        double num = 1;
        for (int i = 0; i < shininess; i++)
            num *= dotProduct;
        return intensity.scale(ks * num);
    }
}
