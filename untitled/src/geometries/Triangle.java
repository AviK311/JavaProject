package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Plane{
    Point3D p2;
    Point3D p3;

    /**
     * ctor with point params
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1,p1.subtract(p2).crossProduct(p1.subtract(p3)));
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
    }

    public Triangle(Color emmision, Point3D p1, Point3D p2, Point3D p3) {
        super(emmision,p1,p1.subtract(p2).crossProduct(p1.subtract(p3)));
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
    }

    public Triangle(Color emmision, int Shininess, double Kd, double Ks, Point3D p1, Point3D p2, Point3D p3) {
        super(emmision, Shininess, Kd, Ks, p1, p1.subtract(p2).crossProduct(p1.subtract(p3)));
        this.p2 = p2;
        this.p3 = p3;
    }

    /**
     *
     * @return p2
     */
    public Point3D getP2() {
        return p2;
    }

    /**
     *
     * @return p3
     */
    public Point3D getP3() {
        return p3;
    }

    @Override
    public Vector getNormal(Point3D p) {
       return this.normal;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionPoints = super.findIntersections(ray);
        if (intersectionPoints.isEmpty())
            return null;
        Point3D rayHead = ray.getHead();
        Vector v0= intersectionPoints.get(0).point.subtract(rayHead); // p - p0
        Vector v1= p1.subtract(rayHead); // T1 - P0
        Vector v2= p2.subtract(rayHead); // T2 - P0
        Vector v3= p3.subtract(rayHead); // T2 - P0
        Vector N1 = v2.crossProduct(v1).normalize(); // v2 x v1 / |v2 x v1|
        Vector N2 = v1.crossProduct(v3).normalize(); // v1 x v3 / |v1 x v3|
        Vector N3 = v3.crossProduct(v2).normalize(); // v3 x v2 / |v3 x v2|
        boolean sign1 = v0.dotProduct(N1) > 0;
        boolean sign2 = v0.dotProduct(N2) > 0;
        boolean sign3 = v0.dotProduct(N3) > 0;
//        double a= v0.dotProduct(N3);
//        Point3D p1 = new Point3D(new Coordinate(a),new Coordinate(0),new Coordinate(0));
//        intersectionPoints.set(0,p1);
        if (sign1==sign2&&sign1==sign3)
            return intersectionPoints;
        return null;
    }
}
