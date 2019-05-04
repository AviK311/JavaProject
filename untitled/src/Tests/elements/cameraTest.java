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
        assertEquals(new Vector(0,0,-1).normalize(), ray.getDirection());
    }


    @Test
    public void constructRayThroughCorner() {
        camera camera = new camera();
        Ray ray = camera.constructRayThroughAPixel(3, 3,3, 3, 100, 150, 150);
        if (!ray.getHead().equals(camera.getP0()))
            fail("The ray head is " + ray.getHead() +"\nand the camera point is " + camera.getP0());
        assertEquals(new Vector(50, -50, -100).normalize(),ray.getDirection());
    }

    @Test
    public void testRaysConstruction(){

        final int WIDTH  = 3;
        final int HEIGHT = 3;


        Point3D p = Point3D.ORIGIN_POINT;
        Point3D p1 = new Point3D(0,1,0);
        Point3D p2 = new Point3D(0,0,-1);


        Vector v1 = new Vector(p1);
        Vector v2 = new Vector(p2);

        Point3D[][] screen = new Point3D [HEIGHT][WIDTH];

        camera camera = new camera(p,v1, v2);

        System.out.println("Camera:\n" + camera);
        System.out.println("##");

        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){

                Ray ray = camera.constructRayThroughAPixel(
                        WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

                screen[i][j] = ray.getHead();

                System.out.print(" ^^ " + screen[i][j]);
                System.out.println(" *** " + ray.getDirection());

                // Checking z-coordinate
                //	assertTrue(Double.compare(screen[i][j].getZ().getCoordinate(), -1.0) == 0);
                assertEquals(screen[i][j].getZ().get(), -1.0,0.1);
                // Checking all options
                double x = screen[i][j].getX().get();
                double y = screen[i][j].getX().get();

                if (Double.compare(x, 3) == 0 ||
                        Double.compare(x, 0) == 0 ||
                        Double.compare(x, -3) == 0){
                    if (Double.compare(y, 3) == 0 ||
                            Double.compare(y, 0) == 0 ||
                            Double.compare(y, -3) == 0){
                        assertTrue(true);
                    } else {
                        fail("Wrong y coordinate");
                    }
                } else {
                    fail("Wrong x coordinate");
                }

            }
            System.out.println("--");
        }

    }

}