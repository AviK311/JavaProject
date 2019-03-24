package geometries;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void getNormal() {
        Vector vec = new Vector(new Point3D(new Coordinate(1),new Coordinate(2), new Coordinate(3)));
        Plane plane = new Plane(null ,vec );
        assertTrue(vec.equals(plane.getNormal()) && vec.equals(plane.getNormal(null)));
    }
}