package renderer;


import Scene.scene;
import primitives.Point3D;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Render {
    scene _scene;
    ImageWriter imageWriter;

    public Render(scene _scene, ImageWriter imageWriter) {
        this._scene = _scene;
        this.imageWriter = imageWriter;
    }
    public Render() {
        this.imageWriter = new ImageWriter("rr",500,500,500,500);
    }

    public scene get_scene() {
        return _scene;
    }

    public void set_scene(scene _scene) {
        this._scene = _scene;
    }

    public ImageWriter getImageWriter() {
        return imageWriter;
    }

    public void setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Render)) return false;
        Render render = (Render) o;
        return get_scene().equals(render.get_scene()) &&
                getImageWriter().equals(render.getImageWriter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_scene(), getImageWriter());
    }
    public void printGrid(int interval)
    {
        for (int i=0; i<imageWriter.getHeight();i++){
            for (int j=0; j<imageWriter.getWidth();j++)
            {
                if(i%interval==0||j%interval==0)
                    imageWriter.writePixel(i,j,255,255,255);
            }
        }
        imageWriter.writeToimage();
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
