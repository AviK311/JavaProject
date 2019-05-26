package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Sphere extends RadialGeometry {
    private Point3D center;

    /**
     * ctor with basic params
     * @param radius
     * @param center
     */
    public Sphere(float radius, Point3D center) {
        super(radius);
        this.center = center;
    }

    /**
     * ctor with emission
     * @param emission
     * @param radius
     * @param center
     */
    public Sphere(Color emission, float radius, Point3D center) {
        super(emission,radius);
        this.center = center;
    }

    /**
     * ctor with material params
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     * @param radius
     * @param center
     */
    public Sphere(Color emission, int Shininess, double _Kd, double _Ks, float radius, Point3D center) {
        super(emission, Shininess, _Kd, _Ks, radius);
        this.center = center;
    }

    /**
     *
     * @return center
     */
    public Point3D getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector v=p.subtract(center);
        return v.normalize();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionPoints = new ArrayList<>();
        if (ray.getHead().equals(center)){//if the ray starts from the center of the sphere
            intersectionPoints.add(new GeoPoint(this, center.add(ray.getDirection().scale(radius))));
            return intersectionPoints;
        }
        Vector u = center.subtract(ray.getHead());
        double uLength = u.length();
        double tM = u.dotProduct(ray.getDirection());
        double d2 = uLength*uLength - tM*tM;//*squared* distance of ray vector from center
        if (Util.alignZero(d2 - radius*radius)>0)//if the ray does not intersect with the sphere
            return null;
        double tN = Math.sqrt(Util.alignZero(radius*radius - d2));
        double tMtNplus = tM+tN, tMtNminus = tM-tN;
        if (tMtNplus > 0) intersectionPoints.add(new GeoPoint(this, ray.getHead().add(ray.getDirection().scale(tMtNplus))));
        if (tMtNminus > 0 && tMtNminus-tMtNplus!=0 ) intersectionPoints.add( new GeoPoint(this, ray.getHead().add(ray.getDirection().scale(tMtNminus))));
        if (intersectionPoints.isEmpty())
            return null;
        return intersectionPoints;
    }
}
