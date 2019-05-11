package primitives;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {
    
    Point3D p1=new Point3D(0,1,2);
    Point3D p2=new Point3D(1,2,2);
    Vector v1=new Vector(p1);
    Vector v2=new Vector(p2);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void add() {
        Vector v3= new Vector(1,3,4);
        Vector result=v1.add(v2);
        assertEquals(v3,result);
    }

    @Test
    public void subtract() {
        Vector v3= new Vector(1,1,0);
        Vector result=v2.subtract(v1);
        assertEquals("good",v3,result);
    }

    /**
     * scale test
     */
    @Test
    public void scale() {
        
        Vector v = new Vector(0,-2,-4);
        Vector result = v1.scale(-2);
        assertEquals("fail",v,result);
    }

    /**
     * length test
     */
    @Test
    public void length() {
        double d=3.;
        double result = v2.length();
        assertEquals("fail",d,result,0.01);
    }

    @Test
    public void normalize() {

        Vector v = new Vector(1.0/3,2.0/3,2.0/3);
        Vector result = v2.normalize();
        assertEquals("fail",v,result);
    }

    @Test
    public void crossProduct() {

        Vector v = new Vector(-2,2,-1);
        Vector result = v1.crossProduct(v2);
        assertEquals("fail",v,result);
    }

    @Test
    public void dotProduct() {
        double d=6;
        double result = v2.dotProduct(v1);
        assertEquals("fail",d,result,0.01);
    }

    @Test
    public void zeroVector() {
        try {
            new Vector(Point3D.ORIGIN_POINT);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage(), true);
        }
    }
}