package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.util.Objects;

public class Triangle extends Plane{
    Point3D p2;
    Point3D p3;

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1,p1.subtract(p2).crossProduct(p1.subtract(p3)));
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
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
}
