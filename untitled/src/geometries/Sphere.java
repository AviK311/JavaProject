package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

public class Sphere extends RadialGeometry {
    private Point3D center;
    public Sphere(float radius, Point3D center) {
        super(radius);
        this.center = center;
    }

    public Point3D getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector v=p.subtract(center);
        return v.normalize();
    }

    @Override
    public ArrayList<Point3D> FindIntersections(Ray ray) {
        //double t=1;
        //Point3D p = ray.getHead().add(ray.getDirection().scale(t));
        return null;
    }
}
