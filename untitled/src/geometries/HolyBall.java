package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Sphere with 6 holes
 */
public class HolyBall extends Halo {
    Point3D holePoint3, holePoint4, holePoint5, holePoint6;
    private final static double sqrt2 = Math.sqrt(2);

    /**
     * ctor with params
     *
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     * @param _Kr
     * @param _Kt
     * @param radius
     * @param holeDirection1
     * @param holeDirection2
     * @param center
     * @param distance
     */
    public HolyBall(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, double radius, Vector holeDirection1, Vector holeDirection2, Point3D center, double distance) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, radius, holeDirection1, center, distance);
        double A = radius / sqrt2;
        double B = radius - A;
        if (distance * distance > A * A + B * B)
            throw new IllegalArgumentException("The holes in the sphere are too big!");
        if (holeDirection1.dotProduct(holeDirection2) != 0)
            throw new IllegalArgumentException("The vectors are not orthogonal!");
        holePoint3 = center.add(holeDirection2.rescale(radius));
        holePoint4 = center.add(holeDirection2.rescale(-radius));
        Vector holeDirection3 = holeDirection1.crossProduct(holeDirection2);
        holePoint5 = center.add(holeDirection3.rescale(radius));
        holePoint6 = center.add(holeDirection3.rescale(-radius));
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        List<GeoPoint> superList = super.findIntersections(myRay);
        if (superList == null) return null;
        superList.removeIf(gp -> gp.point.distance(holePoint3) < distance ||
                gp.point.distance(holePoint4) < distance ||
                gp.point.distance(holePoint5) < distance||
                gp.point.distance(holePoint6) < distance);
//        superList.removeIf(gp -> gp.point.distance(holePoint4) < distance);
//        superList.removeIf(gp -> gp.point.distance(holePoint5) < distance);
//        superList.removeIf(gp -> gp.point.distance(holePoint6) < distance);
        if (superList.isEmpty()) return null;
        return superList;
    }
}
