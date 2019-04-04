package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

public class Plane extends Geometry {
    Point3D p1;
    Vector normal;

    public Plane(Point3D p1, Vector normal) {
        this.p1 = p1;
        this.normal = normal.normalize();
    }
    public Plane(Point3D p1, Point3D p2, Point3D p3){
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        this.p1 = p1;
        this.normal = v1.crossProduct(v2);
    }

    public Point3D getP1() {
        return p1;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return normal;
    }

    @Override
    public ArrayList<Point3D> FindIntersections(Ray ray) {
        ArrayList<Point3D> intersectionPoints = new ArrayList<Point3D>();
        Vector v1=ray.getHead().subtract(p1); // P0 - Q0
        double d= normal.dotProduct(ray.getDirection()); // N * V
        if (d!=0) {
            double t = -normal.dotProduct(v1)/d; // -N * (P0 - QO) / (N * V)
            if (t>0){
                Point3D p = ray.getHead().add(ray.getDirection().scale(t));
                intersectionPoints.add(p);
                return intersectionPoints;
            }
        }
        return  null;
    }
}
