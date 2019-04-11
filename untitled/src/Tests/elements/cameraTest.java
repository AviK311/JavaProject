package elements;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class cameraTest {

    @Test
    public void constructRayThroughCenter() {
        camera camera = new camera();
        Ray ray = camera.constructRayThroughAPixel(3, 3,2, 2, 100, 150, 150);
        if (!ray.getHead().equals(camera.getP0()))
            fail("The ray head is " + ray.getHead() +"\nand the camera point is " + camera.getP0());
        assertEquals(new Vector(new Point3D(0, 0, -1)).normalize(), ray.getDirection());
    }


    @Test
    public void constructRayThroughCorner() {
        camera camera = new camera();
        Ray ray = camera.constructRayThroughAPixel(3, 3,3, 3, 100, 150, 150);
        if (!ray.getHead().equals(camera.getP0()))
            fail("The ray head is " + ray.getHead() +"\nand the camera point is " + camera.getP0());
        assertEquals(new Vector(new Point3D(50, -50, -100)).normalize(),ray.getDirection());
    }
}