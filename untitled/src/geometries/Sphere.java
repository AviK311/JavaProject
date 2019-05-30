package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Sphere extends RadialGeometry {
    private Point3D center;

    /**
     * ctor with basic params
     *
     * @param radius
     * @param center
     */
    public Sphere(float radius, Point3D center) {
        super(radius);
        this.center = center;
    }

    /**
     * ctor with emission
     *
     * @param emission
     * @param radius
     * @param center
     */
    public Sphere(Color emission, float radius, Point3D center) {
        super(emission, radius);
        this.center = center;
    }

    /**
     * ctor with material params
     *
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     * @param radius
     * @param center
     */
    public Sphere(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, float radius, Point3D center) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, radius);
        this.center = center;
    }

    /**
     * @return center
     */
    public Point3D getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector v = p.subtract(center);
        return v.normalize();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        if (ray.getHead().equals(center)) {//if the ray starts from the center of the sphere
            List<GeoPoint> intersectionPoints = new ArrayList<>();
            intersectionPoints.add(new GeoPoint(this, center.add(ray.getDirection().scale(radius))));
            return intersectionPoints;
        }
        Vector u = center.subtract(ray.getHead());
        double uLength = u.length();
        double tM = Util.alignZero(u.dotProduct(ray.getDirection()));
        double d2 = Util.alignZero(uLength * uLength - tM * tM);//*squared* distance of ray vector from center
        double temp = Util.alignZero(d2 - radius * radius);
        if (temp > 0)//if the ray does not intersect with the sphere
            return null;
        if (temp == 0) {
            if (tM <= 0) return null;
            List<GeoPoint> intersectionPoints = new ArrayList<>();
            intersectionPoints.add(new GeoPoint(this, center.add(ray.getDirection().scale(tM))));
            return intersectionPoints;
        }
        double tN = Math.sqrt(Util.alignZero(radius * radius - d2));
        double tMtNplus = Util.alignZero(tM + tN),
                tMtNminus = Util.alignZero(tM - tN);


        List<GeoPoint> intersectionPoints = new ArrayList<>();
        if (tMtNplus>0)
            intersectionPoints.add(new GeoPoint(this, ray.getHead().add(ray.getDirection().scale(tMtNplus))));
        if (tMtNminus > 0)
            intersectionPoints.add(new GeoPoint(this, ray.getHead().add(ray.getDirection().scale(tMtNminus))));
        if (intersectionPoints.isEmpty())
            return null;
        return intersectionPoints;
    }
}
