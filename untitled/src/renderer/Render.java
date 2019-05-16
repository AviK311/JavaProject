package renderer;


import Scene.scene;
import elements.LightSource;
import geometries.Geometry;
import geometries.GeometryList;
import geometries.IIntersectable;
import primitives.Point3D;
import import geometries.IIntersectable.GeoPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import primitives.Ray;
//import java.awt.Color;
import primitives.Color;
import primitives.Vector;

public class Render {
    scene _scene;
    ImageWriter _imageWriter;

    public Render(scene _scene, ImageWriter imageWriter) {
        this._scene = _scene;
        this._imageWriter = imageWriter;
    }
    public Render() {
        this._imageWriter = new ImageWriter("rr",500,500,500,500);
    }

    public scene get_scene() {
        return _scene;
    }

    public void set_scene(scene _scene) {
        this._scene = _scene;
    }

    public ImageWriter get_imageWriter() {
        return _imageWriter;
    }

    public void set_imageWriter(ImageWriter _imageWriter) {
        this._imageWriter = _imageWriter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Render)) return false;
        Render render = (Render) o;
        return get_scene().equals(render.get_scene()) &&
                get_imageWriter().equals(render.get_imageWriter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_scene(), get_imageWriter());
    }

    public void renderImage(){
        for (int i = 0; i< _imageWriter.getHeight(); i++){
            for (int j = 0; j< _imageWriter.getWidth(); j++)
            {
                Ray ray = _scene.getCamera().constructRayThroughAPixel(_imageWriter.getNx(),
                        _imageWriter.getNy(), j, i,_scene.getScreenDistance(), _imageWriter.getWidth(),
                       _imageWriter.getHeight());
                List<GeoPoint> intersectionPoints = getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty()){
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                }
                else {
                    GeoPoint closetPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j,i,calcColor(GeoPoint));
                }

            }
        }
        _imageWriter.writeToimage();
    }

    private List<GeoPoint> getSceneRayIntersections(Ray ray){
        Iterator<IIntersectable> geometries = _scene.getGeoIterator();
        List<GeoPoint> intersectionPoints = new ArrayList<GeoPoint>();
        while (geometries.hasNext())
        {
            IIntersectable geometry = geometries.next();
            List<GeoPoint> geometryIntersectionPoints = geometry.FindIntersections(ray);
            if (geometryIntersectionPoints!=null)
                intersectionPoints.addAll(geometryIntersectionPoints);
        }
      return intersectionPoints;
    }

    private Color calcColor(GeoPoint geoPoint){
        Geometry g = geoPoint.geo;
        Point3D p = geoPoint.point;
        Color returnColor = new Color(_scene.getAmbientLight().getIntensity());
        returnColor = returnColor.add(g.getEmmision());
        Vector v = p.subtract(_scene.getCamera().getP0()).normalize();
        Vector n = g.getNormal(p);
        int nShininess = g.getnShininess();
        double kd = g.get_material().get_Kd();
        double ks = g.get_material().get_Ks();
        for (LightSource light: _scene.getLights()){
            Vector l = light.getL(p);
            if (n.dotProduct(l)*n.dotProduct(v)>0){
                Color lightIntensity = light.getIntensity(p);
                returnColor = returnColor.add(null);
            }
        }
    }

    public void printGrid(int interval)
    {
        for (int i = 0; i< _imageWriter.getHeight(); i++){
            for (int j = 0; j< _imageWriter.getWidth(); j++)
            {
                if(i%interval==0||j%interval==0)
                    _imageWriter.writePixel(i,j,255,255,255);
            }
        }
        _imageWriter.writeToimage();
    }
    private GeoPoint getClosestPoint(List<GeoPoint> pointList){
        if (pointList.isEmpty()) return null;
        Point3D p0 = _scene.getCamera().getP0();
        GeoPoint closestPoint = pointList.get(0);
        for (GeoPoint p: pointList)
             if (p.getPoint().distance(p0) < closestPoint.getPoint().distance(p0))
                 closestPoint = p;
        return closestPoint;

    }
}
