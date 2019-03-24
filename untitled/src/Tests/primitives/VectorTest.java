package primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {
    Coordinate c0=new Coordinate(0);
    Coordinate c1=new Coordinate(1);
    Coordinate c2=new Coordinate(2);
    Coordinate c3=new Coordinate(3);
    Coordinate c4=new Coordinate(4);
    Point3D p1=new Point3D(c0,c1,c2);
    Point3D p2=new Point3D(c1,c2,c2);
    Vector v1=new Vector(p1);
    Vector v2=new Vector(p2);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void add() {
        Point3D p3=new Point3D(c1,c3,c4);
        Vector v3= new Vector(p3);
        Vector result=v1.add(v2);
        assertEquals(v3,result);
    }

    @Test
    public void subtract() {
        Point3D p3=new Point3D(c1,c1,c0);
        Vector v3= new Vector(p3);
        Vector result=v2.subtract(v1);
        assertEquals("good",v3,result);
    }

    @Test
    public void scale() {
        Coordinate c_2 = new Coordinate(-2);
        Coordinate c_4 = new Coordinate(-4);
        Point3D p = new Point3D(c0,c_2,c_4);
        Vector v = new Vector(p);
        Vector result = v1.scale(-2);
        assertEquals("fail",v,result);
    }

    @Test
    public void length() {
        double d=3.;
        double result = v2.length();
        assertEquals("fail",d,result,0.01);
    }

    @Test
    public void normalize() {
        Coordinate c1_3 = new Coordinate(1.0/3);
        Coordinate c2_3 = new Coordinate(2.0/3);
        Vector v = new Vector(new Point3D(c1_3,c2_3,c2_3));
        Vector result = v2.normalize();
        Coordinate c=new Coordinate(0.001);
        //Vector delta =new Vector(new Point3D(c,c,c));
        assertEquals("fail",v,result);

    }

    @Test
    public void crossProduct() {
        Coordinate c_1 = new Coordinate(-1);
        Coordinate c_2 = new Coordinate(-2);
        Vector v = new Vector(new Point3D(c_2,c2,c_1));
        Vector result = v1.crossProduct(v2);
        assertEquals("fail",v,result);
    }

    @Test
    public void dotProduct() {
        double d=6;
        double result = v2.dotProduct(v1);
        assertEquals("fail",d,result,0.01);
    }
}