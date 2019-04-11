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


}