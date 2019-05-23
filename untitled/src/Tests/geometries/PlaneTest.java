package geometries;

import elements.Camera;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void getNormal() {
        Vector vec = new Vector(1,2,3);
        Plane plane = new Plane(Point3D.ORIGIN_POINT ,vec );
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
        List<GeoPoint> result = plane.findIntersections(r);
        assertEquals("fail",1,result.size());

    }

    /**
     * When the plane is adjacent to the Camera
     */
    @Test
    public void getPlanePointsThroughPixels1() {
        Camera c = new Camera();
        Plane p = new Plane(new Point3D(0,0,-1), new Vector(0,0,-1));
        List<GeoPoint> results = new ArrayList<>();
        for (int i = 1; i < 4; i++)
            for(int j = 1; j< 4; j++){
                List<GeoPoint> rayResult = p.findIntersections(c.constructRayThroughAPixel(3, 3, i, j, 100, 150, 150));
                if (rayResult!=null)
                    results.addAll(rayResult);
            }
        assertEquals(9,results.size());

    }

    /**
     * when the plane is perpendicular to the Camera
     */
    @Test
    public void getPlanePointsThroughPixels2() {
        Camera c = new Camera();
        Plane p = new Plane(new Point3D(0,0,-1), new Vector(1,0,0));
        List<GeoPoint> results = new ArrayList<>();
        for (int i = 1; i < 4; i++)
            for(int j = 1; j< 4; j++) {
                List<GeoPoint> rayResult = p.findIntersections(c.constructRayThroughAPixel(3, 3, i, j, 100, 150, 150));
                if (rayResult!=null)
                     results.addAll(rayResult);
            }
        assertEquals(0,results.size());

    }
}