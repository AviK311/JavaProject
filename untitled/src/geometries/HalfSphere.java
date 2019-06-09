package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class HalfSphere extends Sphere {

   protected double distance;
   protected Point3D holePoint;

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
    public HalfSphere(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, float radius, Vector holeDirection, Point3D center, double distance) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, radius, center);
        if (distance>=2*radius)
            throw new IllegalArgumentException("No invisible Spheres!");
        this.distance = distance;
        holePoint = center.add(holeDirection.normalize().scale(radius));
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        List<GeoPoint> superList = super.findIntersections(myRay);
        if (superList == null)
            return null;
        superList.removeIf(gp->gp.point.distance(holePoint)<distance);
        if (superList.isEmpty())
            return null;
        return superList;
    }
}
