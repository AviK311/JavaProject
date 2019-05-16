package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Plane extends Geometry {
    Point3D p1;
    Vector normal;

    /**
     * ctor with point and vector params
     * @param p1
     * @param normal
     */
    public Plane(Point3D p1, Vector normal) {
        this.p1 = new Point3D(p1);
        this.normal = normal.normalize();
    }

    public Plane(Color emmision, Point3D p1, Vector normal) {
        super(emmision);
        this.p1 = new Point3D(p1);
        this.normal = normal.normalize();
    }

    /**
     * ctor with point params
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3){
        Vector v1 = p1.subtract(p2);//if p1==p2, a zero vector constructor attempt exception will be thrown
        Vector v2 = p1.subtract(p3);//if p1==p3, a zero vector constructor attempt exception will be thrown
        this.p1 = new Point3D(p1);
        this.normal = v1.crossProduct(v2).normalize();//if the 3 dots are on the same line,
        // the crossproduct function will throw a zero vector constructor attempt exception
    }

    /**
     *
     * @return point3d pl
     */
    public Point3D getP1() {
        return p1;
    }

    /**
     *
     * @return normal vector
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return normal;
    }

    /**
     *
     * @param ray
     * @return return a list of intersection points to this plane
     */
    @Override
    public List<GeoPoint> FindIntersections(Ray ray) {
        ArrayList<GeoPoint> intersectionPoints = new ArrayList<>();
        Vector v1 = ray.getHead().subtract(p1); // P0 - Q0
        double d = normal.dotProduct(ray.getDirection()); // N * V
        if (d!=0) {//checking whether the normal and the ray direction are vertical
            double t = -normal.dotProduct(v1)/d; // -N * (P0 - QO) / (N * V)
            if (t!=0){
                Point3D p = ray.getHead().add(ray.getDirection().scale(t));
                intersectionPoints.add(new GeoPoint(this, p));
                return intersectionPoints;
            }
        }
        return  null;
    }
}
