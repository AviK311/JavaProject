package geometries;

import primitives.*;

import java.util.List;

public class Square extends Plane {
    Point3D  p2, p3, p4;

    /**
     * ctor with material fields as params
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     * @param _Kr
     * @param _Kt
     * @param P1
     * @param v1
     * @param v2
     * @param length
     */
    public Square(Color emission, int Shininess,
                  double _Kd, double _Ks, double _Kr, double _Kt,
                  Point3D P1, Vector v1,
                  Vector v2, double length) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, P1, v1.crossProduct(v2));
        if (v1.dotProduct(v2)!=0)
            throw new IllegalArgumentException("the vectors are not orthogonal");
        v1 = v1.rescale(length);
        v2 = v2.rescale(length);
        p2 = p1.add(v1);
        p4 = p1.add(v2);
        p3 = p4.add(v1);




    }

    /**
     * ctor with material as a param
     * @param emission
     * @param material
     * @param P1
     * @param v1
     * @param v2
     * @param length
     */
    public Square(Color emission, Material material,
                  Point3D P1, Vector v1,
                  Vector v2, double length) {
        super(emission,material, P1, v1.crossProduct(v2));
        if (v1.dotProduct(v2)!=0)
            throw new IllegalArgumentException("the vectors are not orthogonal");
        v1 = v1.rescale(length);
        v2 = v2.rescale(length);
        p2 = p1.add(v1);
        p4 = p1.add(v2);
        p3 = p4.add(v1);
    }

    /**
     * ctor made for enclosing the circle
     * @param P1
     * @param v1
     * @param v2
     * @param length
     */
    public Square(Point3D P1, Vector v1,
                  Vector v2, double length) {
        super(P1, v1.crossProduct(v2));
        if (v1.dotProduct(v2)!=0)
            throw new IllegalArgumentException("the vectors are not orthogonal");
        v1 = v1.rescale(length);
        v2 = v2.rescale(length);
        p2 = p1.add(v1);
        p4 = p1.add(v2);
        p3 = p4.add(v1);




    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionPoints = super.findIntersections(ray);
        if (intersectionPoints == null || intersectionPoints.isEmpty())
            return null;
        Point3D rayHead = ray.getHead();
        Vector v0 = intersectionPoints.get(0).point.subtract(rayHead);
        Vector v1 = p1.subtract(rayHead);
        Vector v2 = p2.subtract(rayHead);
        Vector v3 = p3.subtract(rayHead);
        Vector v4 = p4.subtract(rayHead);
        if (v1.equals(ray.getDirection()) ||v2.equals(ray.getDirection())||v3.equals(ray.getDirection()))
            return null;
        Vector N1 = v2.crossProduct(v1).normalize();
        Vector N2 = v3.crossProduct(v2).normalize();
        Vector N3 = v4.crossProduct(v3).normalize();
        Vector N4 = v1.crossProduct(v4).normalize();
        boolean sign1 = v0.dotProduct(N1) > 0;
        boolean sign2 = v0.dotProduct(N2) > 0;
        boolean sign3 = v0.dotProduct(N3) > 0;
        boolean sign4 = v0.dotProduct(N4) > 0;
//        double a= v0.dotProduct(N3);
//        Point3D p1 = new Point3D(new Coordinate(a),new Coordinate(0),new Coordinate(0));
//        intersectionPoints.set(0,p1);
        if (sign1 == sign2 && sign1 == sign3 && sign1 == sign4)
            return intersectionPoints;
        return null;
    }

    @Override
    public double getMaxX() {
        return Double.max(Double.max(p1.getX().get(), p2.getX().get()), Double.max(p3.getX().get(), p4.getX().get()));
    }

    @Override
    public double getMinX() {
        return Double.min(Double.min(p1.getX().get(), p2.getX().get()), Double.min(p3.getX().get(), p4.getX().get()));
    }

    @Override
    public double getMaxY() {
        return Double.max(Double.max(p1.getY().get(), p2.getY().get()), Double.max(p3.getY().get(), p4.getY().get()));
    }

    @Override
    public double getMinY() {
        return Double.min(Double.min(p1.getY().get(), p2.getY().get()), Double.min(p3.getY().get(), p4.getY().get()));
    }

    @Override
    public double getMaxZ() {
        return Double.max(Double.max(p1.getZ().get(), p2.getZ().get()), Double.max(p3.getZ().get(), p4.getZ().get()));
    }

    @Override
    public double getMinZ() {
        return Double.min(Double.min(p1.getZ().get(), p2.getZ().get()), Double.min(p3.getZ().get(), p4.getZ().get()));
    }
}
