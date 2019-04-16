package geometries;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void getNormal() {
        Coordinate zero = new Coordinate(0), one = new Coordinate(1);
        Point3D p1 = new Point3D(zero, zero, zero);
        Point3D p2 = new Point3D(one, zero, zero);
        Point3D p3 = new Point3D(zero, one, zero);
        Triangle tri = new Triangle(p1, p2, p3);
        assertEquals(new Vector(new Point3D(zero, zero, one)),tri.getNormal(p1));
    }

    @Test
    public void findIntersections() {
        Point3D p1 = new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        Point3D p1_ = new Point3D(new Coordinate(0),new Coordinate(100),new Coordinate(-200));
        Point3D p2_ = new Point3D(new Coordinate(100),new Coordinate(-200),new Coordinate(-200));
        Point3D p3_ = new Point3D(new Coordinate(-200),new Coordinate(-200),new Coordinate(-200));
        Vector v1 = new Vector(new Point3D(new Coordinate(1.0/3),new Coordinate(1.0/3),new Coordinate(1.0/3)));
        Triangle triangle = new Triangle(p1_,p2_,p3_);
        Ray r =new Ray(v1,p1);
        ArrayList<Point3D> result = triangle.FindIntersections(r);
        ArrayList<Point3D> a=new ArrayList<Point3D>();
        Point3D p3 =new Point3D(new Coordinate(-200),new Coordinate(-200),new Coordinate(-200));
        a.add(p3);
        assertEquals("fail",a,result);
    }

    @Test
    public void findIntersections2() {
        Point3D p1 = new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        Point3D p1_ = new Point3D(new Coordinate(0),new Coordinate(100),new Coordinate(-200));
        Point3D p2_ = new Point3D(new Coordinate(100),new Coordinate(-100),new Coordinate(-200));
        Point3D p3_ = new Point3D(new Coordinate(-100),new Coordinate(-100),new Coordinate(-200));
        Vector v1 = new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1)));
        Triangle triangle = new Triangle(p1_,p2_,p3_);
        Ray r =new Ray(v1,p1);
        ArrayList<Point3D> result = triangle.FindIntersections(r);
        ArrayList<Point3D> a=new ArrayList<Point3D>();
        Point3D p3 =new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-200));
        a.add(p3);
        assertEquals("fail",a,result);
    }
}