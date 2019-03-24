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
        Coordinate c=new Coordinate(1);
        Coordinate c2=new Coordinate(3);
        Point3D p=new Point3D(c,c,c);
        Point3D p2=new Point3D(c2,c2,c2);
        Vector v=new Vector(p);
        Vector v2=new Vector(p2);
        Vector result=v.add(v);
        assertEquals("good",v2,result);
    }

    @Test
    public void subtract() {
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