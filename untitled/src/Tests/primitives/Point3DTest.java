package primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Point3DTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void add() {
        Point3D p1 =new Point3D(1,2,3);
        Vector v = new Vector(1,1,1);
        Point3D p2 =new Point3D(2,3,4);
        assertEquals("fail",p2,p1.add(v));
    }

    @Test
    public void equals() {
        Point3D p1 =new Point3D(1,2,3);
        Point3D p2 =new Point3D(1,2,3);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void subtract() {
        Point3D p1 =new Point3D(1,2,4);
        Point3D p2 =new Point3D(2,3,4);
        Vector v = new Vector(-1,-1,0);
        assertEquals("fail",v,p1.subtract(p2));
    }

    @Test
    public void distance() {
        Point3D p1 =new Point3D(1,2,4);
        Point3D p2 =new Point3D(2,3,4);
        double d=Math.sqrt(2);
        double result = p1.distance(p2);
        assertEquals("fail",d,result,0.01);
    }

    @Test
    public void distanceSquared() {
        Point3D p1 =new Point3D(1,2,4);
        Point3D p2 =new Point3D(2,3,4);
        double d=2.;
        double result = p1.distanceSquared(p2);
        assertEquals("fail",d,result,0.01);
    }
}