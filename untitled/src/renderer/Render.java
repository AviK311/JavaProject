package renderer;


import Scene.scene;
import geometries.Geometry;
import geometries.IIntersectable;
import primitives.Point3D;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import primitives.Ray;
//import java.awt.Color;
import primitives.Color;

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
                List<Point3D> intersectionPoints = getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty()){
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                }
                else {
                    Point3D closetPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j,i,calcColor(closetPoint));
                }

            }
        }
        _imageWriter.writeToimage();
    }

    private List<Point3D> getSceneRayIntersections(Ray ray){
        Iterator<IIntersectable> geometries = _scene.getGeoIterator();
        List<Point3D> intersectionPoints = new ArrayList<Point3D>();
        while (geometries.hasNext())
        {
            IIntersectable geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);
            intersectionPoints.addAll(geometryIntersectionPoints);
        }
      return intersectionPoints;
    }

    private Color calcColor(Point3D point){
        return new Color(0,150,0);
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
    private Point3D getClosestPoint(List<Point3D> pointList){
        if (pointList.isEmpty()) return null;
        Point3D p0 = _scene.getCamera().getP0();
        Point3D closestPoint = pointList.get(0);
        for (Point3D p: pointList)
             if (p.distance(p0) < closestPoint.distance(p0))
                 closestPoint = p;
        return closestPoint;

    }
}
