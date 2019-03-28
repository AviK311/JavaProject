package elements;

import geometries.Triangle;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class cameraTest {

    @Test
    public void getvRight() {
        // TODO: 3/28/2019 Add a test for getvRight
}

    @Test
    public void constructRayThrowAPixel() {
        // TODO: 3/28/2019 Add a test for constructRayThrowAPixel

        Coordinate zero = new Coordinate(0), one = new Coordinate(1);
        Point3D p0 = Point3D.originPoint;
        Point3D p1 = new Point3D(one, zero, zero);
        Point3D p2 = new Point3D(zero, one, zero);
        Point3D p3 = new Point3D(zero, zero, one);

        camera camera = new camera(p0,new Vector(p1),new Vector(p2),new Vector(p3));
        Ray ray = camera.constructRayThrowAPixel(10, 10,1, 1, 10, 10, 10);
        if (!ray.getHead().equals(camera.getP0()))
            fail("The ray head is " + ray.getHead() +"\nand the camera point is " + camera.getP0());
        assertEquals(ray.getDirection(), new Vector(new Point3D(3.5, -3.5, -3.5)));
    }
}