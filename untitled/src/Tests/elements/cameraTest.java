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
        camera camera = new camera();
        Ray ray = camera.constructRayThrowAPixel(10, 10,1, 1, 10, 10, 10);
        if (!ray.getHead().equals(camera.getP0()))
            fail("The ray head is " + ray.getHead() +"\nand the camera point is " + camera.getP0());
        assertEquals(new Vector(new Point3D(-4.5, 4.5, -10)).normalize(), ray.getDirection());
    }
    @Test
    public void constructRayThrowAPixel2() {
        // TODO: 3/28/2019 Add a test for constructRayThrowAPixel

        camera camera = new camera();
        Ray ray = camera.constructRayThrowAPixel(3, 3,3, 3, 100, 150, 150);
        if (!ray.getHead().equals(camera.getP0()))
            fail("The ray head is " + ray.getHead() +"\nand the camera point is " + camera.getP0());
        assertEquals(new Vector(new Point3D(50, -50, -100)).normalize(),ray.getDirection());
    }
}