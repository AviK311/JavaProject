package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane extends Geometry {
    Point3D p1;
    Vector normal;

    public Plane(Point3D p1, Vector normal) {
        this.p1 = p1;
        this.normal = normal;
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
}
