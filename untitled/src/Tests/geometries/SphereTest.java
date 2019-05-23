package geometries;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SphereTest {

    @Test
    public void getNormal() {
        Coordinate zero = new Coordinate(0), one = new Coordinate(1);
        Point3D p1 = new Point3D(zero, zero, zero);
        Point3D p2 = new Point3D(zero, one, zero);
        Sphere sp = new Sphere(1.0f, p1);
        assertEquals(new Vector(p2),sp.getNormal(p2));

    }

    /**
     * The example from Chapter 6
     */
    @Test
    public void findIntersections() {
        Point3D p0 = new Point3D(0,0,0);
        Sphere sphere = new Sphere(200,new Point3D(0,0,-400));
        Vector v1 = new Vector(0,0,-1);
        Ray r =new Ray(v1,p0);
        List<Intersectable.GeoPoint> result = sphere.findIntersections(r);
        List<Intersectable.GeoPoint> a= new ArrayList<>();

        Point3D p1 =new Point3D(0,0,-600);
        Point3D p2 =new Point3D(0,0,-200);
        a.add(new Intersectable.GeoPoint(sphere, p1));
        a.add(new Intersectable.GeoPoint(sphere, p2));
        assertEquals("fail",a.size(),result.size());
    }

    /**
     * The diameter of this sphere is half the diameter of the previous one: from the center (0,0,-400) to (0,-200,-400)
     */
    @Test
    public void findIntersections2() {
        Point3D p0 = Point3D.ORIGIN_POINT;
        Sphere sphere = new Sphere(100,new Point3D(0,-100,-400));
        Vector v1 = new Vector(0,0,-1);
        Ray r =new Ray(v1,p0);
        List<Intersectable.GeoPoint> result = sphere.findIntersections(r);
        ArrayList<Intersectable.GeoPoint> a=new ArrayList<Intersectable.GeoPoint>();
        Point3D p1 =new Point3D(0,0,-400);
        a.add(new Intersectable.GeoPoint(sphere, p1));
        assertEquals("fail",a.size(),result.size());
    }

    /**
     * This sphere is similar to the previous one but with a smaller radius so there are no intersection points.
     */
    @Test
    public void findIntersections3() {
        Point3D p0 = Point3D.ORIGIN_POINT;
        Sphere sphere = new Sphere(80,new Point3D(0,-100,-400));
        Vector v1 = new Vector(0,0,-1);
        Ray r =new Ray(v1,p0);
        List<Intersectable.GeoPoint> result = sphere.findIntersections(r);
        assertEquals("fail",null,result);
    }
}