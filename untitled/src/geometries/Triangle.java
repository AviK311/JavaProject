package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Objects;

public class Triangle extends Plane{
    Point3D p2;
    Point3D p3;

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1,p1.subtract(p2).crossProduct(p1.subtract(p3)));
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
        normal=p1.subtract(p2).crossProduct(p1.subtract(p3));//to check
    }


    public Point3D getP2() {
        return p2;
    }

    public Point3D getP3() {
        return p3;
    }

    @Override
    public Vector getNormal(Point3D p) {
       return this.normal;
    }

    @Override
    public ArrayList<Point3D> FindIntersections(Ray ray) {
        ArrayList<Point3D> intersectionPoints = super.FindIntersections(ray);
        Vector v0= intersectionPoints.get(0).subtract(ray.getHead()); // p - p0
        Vector v1= p1.subtract(ray.getHead()); // T1 - P0
        Vector v2= p2.subtract(ray.getHead()); // T2 - P0
        Vector v3= p3.subtract(ray.getHead()); // T2 - P0
        Vector v21 = v2.crossProduct(v1); // v2 x v1
        Vector v13 = v1.crossProduct(v3); // v1 x v3
        Vector v32 = v3.crossProduct(v2); // v3 x v2
        Vector  N1 = v21.scale(1.0/v21.length()); // v2 x v1 / |v2 x v1|
        Vector  N2 = v13.scale(1.0/v13.length()); // v2 x v1 / |v2 x v1|
        Vector  N3 = v32.scale(1.0/v32.length()); // v2 x v1 / |v2 x v1|
        double sign1=v0.dotProduct(N1);
        double sign2=v0.dotProduct(N2);
        double sign3=v0.dotProduct(N3);
        if ((sign1>0&&sign2>0&&sign3>0)||(sign1<0&&sign2<0&&sign3<0))
            return intersectionPoints;
        return null;
    }
}
