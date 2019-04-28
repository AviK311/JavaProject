package geometries;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

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

    @Test   // The example from Chapter 6
    public void findIntersections() {
        Point3D p0 = new Point3D(0,0,0);
        Sphere sphere = new Sphere(200,new Point3D(0,0,-400));
        Vector v1 = new Vector(0,0,-1);
        Ray r =new Ray(v1,p0);
        ArrayList<Point3D> result = sphere.FindIntersections(r);
        ArrayList<Point3D> a=new ArrayList<Point3D>();
        Point3D p1 =new Point3D(0,0,-600);
        Point3D p2 =new Point3D(0,0,-200);
        a.add(p1);
        a.add(p2);
        assertEquals("fail",a,result);
    }
    @Test   // This sphere is half from the previous one: from the center (0,0,-400) to (0,-200,-400)
    public void findIntersections2() {
        Point3D p0 = new Point3D(0,0,0);
        Sphere sphere = new Sphere(100,new Point3D(0,-100,-400));
        Vector v1 = new Vector(0,0,-1);
        Ray r =new Ray(v1,p0);
        ArrayList<Point3D> result = sphere.FindIntersections(r);
        ArrayList<Point3D> a=new ArrayList<Point3D>();
        Point3D p1 =new Point3D(0,0,-400);
        a.add(p1);
        assertEquals("fail",a,result);
    }

    @Test //This sphere is similar to the previous one but with a smaller radius so there are no intersection points.
    public void findIntersections3() {
        Point3D p0 = new Point3D(0,0,0);
        Sphere sphere = new Sphere(80,new Point3D(0,-100,-400));
        Vector v1 = new Vector(0,0,-1);
        Ray r =new Ray(v1,p0);
        ArrayList<Point3D> result = sphere.FindIntersections(r);
        assertEquals("fail",null,result);
    }
}