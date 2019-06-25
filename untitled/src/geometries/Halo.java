package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Sphere with 2 holes
 */
public class Halo extends HalfSphere {
    Point3D holePoint2;

    /**
     * ctor with params
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     * @param _Kr
     * @param _Kt
     * @param radius
     * @param holeDirection
     * @param center
     * @param distance
     */
    public Halo(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, double radius, Vector holeDirection, Point3D center, double distance) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, radius, holeDirection, center, distance);
        holePoint2 = center.add(holeDirection.rescale(-radius));
        if (distance*distance >= 2*radius*radius)
            throw new IllegalArgumentException("No invisible Spheres!");
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        List<GeoPoint> superList = super.findIntersections(myRay);
        if (superList == null) return null;
        superList.removeIf(gp->gp.point.distance(holePoint2)<distance);
        if (superList.isEmpty()) return null;
        return superList;
    }
}
