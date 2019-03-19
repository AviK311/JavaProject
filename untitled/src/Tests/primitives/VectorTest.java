package primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void add() {
        Coordinate c1=new Coordinate(1);
        Coordinate c2=new Coordinate(2);
        Point3D p1=new Point3D(c1,c1,c1);
        Point3D p2=new Point3D(c2,c2,c2);
        Vector v1=new Vector(p1);
        Vector v2=new Vector(p2);
        Vector result=v1.add(v1);
        assertEquals("good",v2,result);
    }

    @Test
    public void subtract() {
        Coordinate c1=new Coordinate(1);
        Coordinate c2=new Coordinate(2);
        Coordinate c0=new Coordinate(0);
        Point3D p1=new Point3D(c0,c1,c2);
        Point3D p2=new Point3D(c1,c2,c2);
        Point3D p3=new Point3D(c1,c1,c0);
        Vector v1=new Vector(p1);
        Vector v2=new Vector(p2);
        Vector v3=new Vector(p3);
        Vector result=v2.subtract(v1);
        assertEquals("good",v3,result);
    }

    @Test
    public void scale() {
    }

    @Test
    public void length() {
    }

    @Test
    public void normalize() {
    }

    @Test
    public void crossProduct() {
    }

    @Test
    public void dotProduct() {
    }
}