package geometries;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void getNormal() {
        Vector vec = new Vector(new Point3D(new Coordinate(1),new Coordinate(2), new Coordinate(3)));
        Plane plane = new Plane(null ,vec );
        assertTrue(vec.equals(plane.getNormal()) && vec.equals(plane.getNormal(null)));
    }

    @Test
    public void findIntersections() {
        Point3D p1 = new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        Point3D p2 = new Point3D(new Coordinate(0),new Coordinate(100),new Coordinate(-200));
        Vector v1 = new Vector(new Point3D(new Coordinate(1.0/3),new Coordinate(1.0/3),new Coordinate(1.0/3)));
        Vector v2 = new Vector(new Point3D(new Coordinate(0),new Coordinate(0), new Coordinate(-1)));
        Plane plane = new  Plane(p2,v2);
        Ray r =new Ray(v1,p1);
        ArrayList<Point3D> result = plane.FindIntersections(r);
        ArrayList<Point3D> a=new ArrayList<Point3D>();
        Point3D p3 =new Point3D(new Coordinate(-200),new Coordinate(-200),new Coordinate(-200));
        a.add(p3);
        assertEquals("fail",a,result);

    }
}