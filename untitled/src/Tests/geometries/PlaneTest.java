package geometries;

import elements.camera;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void getNormal() {
        Vector vec = new Vector(1,2,3);
        Plane plane = new Plane(null ,vec );
        assertTrue(vec.normalize().equals(plane.getNormal()) && vec.normalize().equals(plane.getNormal(null)));
    }

    @Test
    public void findIntersections() {
        Point3D p1 = new Point3D(0,0,0);
        Point3D p2 = new Point3D(0,100,-200);
        Vector v1 = new Vector(1.0/3,1.0/3,1.0/3);
        Vector v2 = new Vector(0,0,-1);
        Plane plane = new  Plane(p2,v2);
        Ray r =new Ray(v1,p1);
        ArrayList<Point3D> result = plane.FindIntersections(r);
        ArrayList<Point3D> a=new ArrayList<Point3D>();
        Point3D p3 =new Point3D(-200,-200,-200);
        a.add(p3);
        assertEquals("fail",a,result);

    }

    /**
     * When the plane is adjacent to the camera
     */
    @Test
    public void getPlanePointsThroughPixels1() {
        camera c = new camera();
        Plane p = new Plane(new Point3D(0,0,-1), new Vector(0,0,-1));
        ArrayList<Point3D> results = new ArrayList<>();
        for (int i = 1; i < 4; i++)
            for(int j = 1; j< 4; j++){
                ArrayList<Point3D> rayResult = p.FindIntersections(c.constructRayThroughAPixel(3, 3, i, j, 100, 150, 150));
                if (rayResult!=null)
                    results.addAll(rayResult);
            }
        assertEquals(9,results.size());

    }

    /**
     * when the plane is perpendicular to the camera
     */
    @Test
    public void getPlanePointsThroughPixels2() {
        camera c = new camera();
        Plane p = new Plane(new Point3D(0,0,-1), new Vector(1,0,0));
        ArrayList<Point3D> results = new ArrayList<>();
        for (int i = 1; i < 4; i++)
            for(int j = 1; j< 4; j++) {
                ArrayList<Point3D> rayResult = p.FindIntersections(c.constructRayThroughAPixel(3, 3, i, j, 100, 150, 150));
                if (rayResult!=null)
                     results.addAll(rayResult);
            }
        assertEquals(0,results.size());

    }
}