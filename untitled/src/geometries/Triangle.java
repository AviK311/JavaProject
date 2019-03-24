package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.util.Objects;

public class Triangle extends Geometry{
    Point3D p1;
    Point3D p2;
    Point3D p3;

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = new Point3D(p1);
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
    }

      public Point3D getP1() {
        return p1;
    }

    public Point3D getP2() {
        return p2;
    }

    public Point3D getP3() {
        return p3;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        return v1.crossProduct(v2).normalize();
    }
}
