package geometries;

//import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOpt;
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
     * 4 EP tests
     * 11 VBA tests
     */
    @Test
    public void findIntersections() {
        Point3D p0 = Point3D.ORIGIN_POINT;

        //EPTest 1: Classic situation
        Sphere sphere = new Sphere(5,new Point3D(0,0,-10));
        Ray r = new Ray(new Vector(0,-0.5,-1),p0);
        List<Intersectable.GeoPoint> result = sphere.findIntersections(r);
        assertEquals("failed EPTest 1",2,result.size());

        //EPTest 2: Ray does not go through the sphere
        r = new Ray(new Vector(0,1,0), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed EPTest 2",null,result);

        //EPTest 3: Ray points away from the sphere
        r = new Ray(new Vector(0,0.5,1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed EPTest 3",null,result);

        //EPTest 4: Ray begins inside sphere
        sphere = new Sphere(5, new Point3D(0,0,-1));
        r = new Ray(new Vector(0,-1,-1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed EPTest 4",1,result.size());

        //VBATest 1: ray begins on sphere but points away
        sphere = new Sphere(5, new Point3D(0,0,-5));
        r = new Ray(new Vector(0,-1,1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 1",null,result);

        //VBATest 2: ray begins on sphere and points inwards
        sphere = new Sphere(5, new Point3D(0,0,-5));
        r = new Ray(new Vector(0,-1,-1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 2",1,result.size());

        //VBATest 3: ray is tangent to the sphere
        sphere = new Sphere(1, new Point3D(1,0,-1));
        r = new Ray(new Vector(0,0,-1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 3",1,result.size());

        //VBATest 4: ray is tangent to the sphere and head is on sphere
        sphere = new Sphere(5, new Point3D(0,0,-5));
        r = new Ray(new Vector(0,1,0), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 4",null,result);

        //VBATest 5: ray tail is tangent to the sphere and head is not on sphere
        sphere = new Sphere(5, new Point3D(0,-5,-5));
        r = new Ray(new Vector(0,1,0), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 5",null,result);

        //VBATest 6: ray head on sphere, ray tail extends to center
        sphere = new Sphere(5, new Point3D(0,0,-5));
        r = new Ray(new Vector(0,0,1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 6",null,result);

        //VBATest 7: ray head outside sphere, ray tail extends to center
        sphere = new Sphere(5, new Point3D(0,0,-6));
        r = new Ray(new Vector(0,0,1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 7",null,result);

        //VBATest 8: ray head on sphere center
        sphere = new Sphere(5, new Point3D(0,0,0));
        r = new Ray(new Vector(0,0,-1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 8",1,result.size());

        //VBATest 9: ray head on sphere, pointing to center
        sphere = new Sphere(5, new Point3D(0,0,-5));
        r = new Ray(new Vector(0,0,-1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 9",1,result.size());

        //VBATest 10: ray head outside sphere, pointing to center
        sphere = new Sphere(5, new Point3D(0,0,-6));
        r = new Ray(new Vector(0,0,-1), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 10",2,result.size());

        //VBATest 11: ray head outside sphere, pointing vertically from sphere center
        sphere = new Sphere(1, new Point3D(0,0,-2));
        r = new Ray(new Vector(1,0,0), p0);
        result = sphere.findIntersections(r);
        assertEquals("failed VBATest 11",null,result);
    }

}