package geometries;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.IIntersectable.GeoPoint;

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

    /**
     *  The example from Chapter 6
     */
    @Test
    public void findIntersections() {
        Point3D p0 = Point3D.ORIGIN_POINT;
        Point3D p1 = new Point3D(0,100,-200);
        Point3D p2 = new Point3D(100,-100,-200);
        Point3D p3 = new Point3D(-100,-100,-200);
        Triangle triangle=new Triangle(p1,p2,p3);
        Vector v1 = new Vector(0,0,-1);
        Ray r =new Ray(v1,p0);
        ArrayList<GeoPoint> result = (ArrayList<GeoPoint>)triangle.FindIntersections(r);
        ArrayList<GeoPoint> a=new ArrayList<GeoPoint>();
        Point3D p4 =new Point3D(0,0,-200);
        a.add(new GeoPoint(triangle, p4));
        assertEquals("fail",a,result);

    }
    @Test
    public void findIntersections2() {
        Point3D p0 =Point3D.ORIGIN_POINT;
        Point3D p1 = new Point3D(0,100,-200);
        Point3D p2 = new Point3D(100,-100,-200);
        Point3D p3 = new Point3D(-200,-200,-200);
        Triangle triangle=new Triangle(p1,p2,p3);
        Vector v1 = new Vector(0,1,-5);
        Ray r =new Ray(v1,p0);
        ArrayList<GeoPoint> result = (ArrayList<GeoPoint>)triangle.FindIntersections(r);
        ArrayList<GeoPoint> a=new ArrayList<GeoPoint>();
        Point3D p4 =new Point3D(0,40,-200);
        a.add(new GeoPoint(triangle,p4));
        assertEquals("fail",a,result);

    }

    @Test
    public void findIntersections3() {
        Point3D p0 = new Point3D(0,0,0);
        Point3D p1 = new Point3D(0,100,-200);
        Point3D p2 = new Point3D(100,-100,-200);
        Point3D p3 = new Point3D(-204,-203,-200);
        Triangle triangle=new Triangle(p1,p2,p3);
        Vector v1 = new Vector(1,1,1);
        Ray r =new Ray(v1,p0);
        ArrayList<GeoPoint> result = (ArrayList<GeoPoint>)triangle.FindIntersections(r);
        ArrayList<GeoPoint> a=new ArrayList<GeoPoint>();
        Point3D p4 =new Point3D(-200,-200,-200);
        a.add(new GeoPoint(triangle, p4));
        assertEquals("fail",a,result);

    }
}