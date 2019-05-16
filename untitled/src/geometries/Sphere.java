package geometries;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

public class Sphere extends RadialGeometry {
    private Point3D center;

    public Sphere(float radius, Point3D center) {
        super(radius);
        this.center = center;
    }

    public Sphere(Color emmision, float radius, Point3D center) {
        super(emmision,radius);
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

    /**
     *
     * @param ray
     * @return
     */
    @Override
    public ArrayList<GeoPoint> FindIntersections(Ray ray) {
        ArrayList<GeoPoint> intersectionPoints = new ArrayList<>();
        Vector u = center.subtract(ray.getHead());
        double uLength = u.length();
        double tM = u.dotProduct(ray.getDirection());
        double d = Math.sqrt(uLength*uLength - tM*tM);
        double tN = Math.sqrt(radius*radius - d*d);
        if (tM + tN > 0) intersectionPoints.add(new GeoPoint(this, ray.getHead().add(ray.getDirection().scale(tM + tN))));
        if (tM - tN > 0) intersectionPoints.add( new GeoPoint(this, ray.getHead().add(ray.getDirection().scale(tM - tN))));
        if (tM + tN == tM-tN && tM-tN != 0)//0nly one point
            intersectionPoints.remove(0);
        if (intersectionPoints.isEmpty())
            return null;
        return intersectionPoints;
    }
}
