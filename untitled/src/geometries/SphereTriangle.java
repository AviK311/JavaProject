package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class SphereTriangle extends Sphere {

    protected double distance;
    protected Point3D p1_;
    protected Point3D p2_;
    protected Point3D p3_;
    Vector direction;

    public SphereTriangle(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, float radius, Vector holeDirection, Point3D center, double distance) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, radius, center);
        direction = holeDirection;
        if (distance>=2*radius)
            throw new IllegalArgumentException("No invisible Spheres!");
        this.distance = distance;
        Point3D p=center.add(holeDirection);

        //p2_ = center.add(holeDirection.normalize().scale(radius));
        Vector v1 = new Vector(holeDirection.getHead().getX().get()*-1,holeDirection.getHead().getY().get(),holeDirection.getHead().getZ().get());
        Vector v2 = new Vector(holeDirection.getHead().getX().get(),holeDirection.getHead().getY().get()*-1,holeDirection.getHead().getZ().get());
        Vector v3 = new Vector(holeDirection.getHead().getX().get(),holeDirection.getHead().getY().get(),holeDirection.getHead().getZ().get()*-1);
        p1_ = p.add(v1);
        p2_= p.add(v2);
        p3_ = p.add(v3);
    }




    @Override
    public List<Intersectable.GeoPoint> findIntersections(Ray myRay) {
        List<Intersectable.GeoPoint> superList = super.findIntersections(myRay);
        if (superList == null)
            return null;
        for(GeoPoint g: superList)
            if(inTrianle(g.point)&& g.point.subtract(this.getCenter().add(direction.scale(radius))).length()<=radius)
                g.geo=new Sphere(new Color(100, 0, 0),
                            100, 0.2, 0.3, 0.1, 0, 50,
                           new Point3D(0, 0, -100));
 //       superList.removeIf(gp->gp.point.distance(p1_)<distance);
        if (superList.isEmpty())
            return null;
        return superList;
    }

    public  Boolean inTrianle(Point3D p)
    {
        Ray ray = new Ray(this.getCenter().subtract(p),this.getCenter());
        Point3D rayHead = ray.getHead();
        Vector v0 = p.subtract(rayHead); // p - p0
        Vector v1 = p1_.subtract(rayHead); // T1 - P0
        Vector v2 = p2_.subtract(rayHead); // T2 - P0
        Vector v3 = p3_.subtract(rayHead); // T2 - P0
        if (v1.equals(ray.getDirection()) ||v2.equals(ray.getDirection())||v3.equals(ray.getDirection()))
            return false;
        Vector N1 = v2.crossProduct(v1).normalize(); // v2 x v1 / |v2 x v1|
        Vector N2 = v1.crossProduct(v3).normalize(); // v1 x v3 / |v1 x v3|
        Vector N3 = v3.crossProduct(v2).normalize(); // v3 x v2 / |v3 x v2|
        boolean sign1 = v0.dotProduct(N1) > 0;
        boolean sign2 = v0.dotProduct(N2) > 0;
        boolean sign3 = v0.dotProduct(N3) > 0;
//        double a= v0.dotProduct(N3);
//        Point3D p1 = new Point3D(new Coordinate(a),new Coordinate(0),new Coordinate(0));
//        intersectionPoints.set(0,p1);
        if (sign1 == sign2 && sign1 == sign3)
            return true;
        return false;
    }
}
