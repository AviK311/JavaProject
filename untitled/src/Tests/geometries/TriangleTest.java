package geometries;

import geometries.Intersectable.GeoPoint;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TriangleTest {

    @Test
    public void getNormal() {
        Coordinate zero = new Coordinate(0), one = new Coordinate(1);
        Point3D p1 = new Point3D(zero, zero, zero);
        Point3D p2 = new Point3D(one, zero, zero);
        Point3D p3 = new Point3D(zero, one, zero);
        Triangle tri = new Triangle(p1, p2, p3);
        assertEquals(new Vector(new Point3D(zero, zero, one)), tri.getNormal(p1));
    }

    @Test
    public void findIntersections() {
        Point3D p0 = Point3D.ORIGIN_POINT;
        Point3D[] pointArray = new Point3D[9];
        int index = 0;
        for (double i = -1; i < 2; i++)
            for (double j = -1; j < 2; j++)
                pointArray[index++] = new Point3D(i, j, -1);
        Triangle triangle;
        List<GeoPoint> result;
        Ray ray;
        //EPTest 1: intersect in triangle
        triangle = new Triangle(pointArray[0], pointArray[2], pointArray[7]);
        ray = new Ray(new Vector(0, 0, -1), p0);
        result = triangle.findIntersections(ray);
        assertEquals("fail EPTest 1", 1, result.size());
        //EPTest 2: point is near edge
        triangle = new Triangle(pointArray[2], pointArray[6], pointArray[8]);
        ray = new Ray(new Vector(1, 1, -1), p0);
        result = triangle.findIntersections(ray);
        assertEquals("fail EPTest 2", null, result);
        //EPTest 3: point is near corner
        triangle = new Triangle(pointArray[2], pointArray[4], pointArray[8]);
        ray = new Ray(new Vector(1, 0, -1), p0);
        result = triangle.findIntersections(ray);
        assertEquals("fail EPTest 3", null, result);
        //VBA 1: ray begins on plane inside triangle
        triangle = new Triangle(pointArray[1], pointArray[6], pointArray[8]);
        ray = new Ray(new Vector(0, 0, -1), new Point3D(0, 0, -1));
        result = triangle.findIntersections(ray);
        assertEquals("fail VBATest 1", null, result);
        //VBA 2: ray begins on plane on edge
        triangle = new Triangle(pointArray[2], pointArray[6], pointArray[8]);
        ray = new Ray(new Vector(0, 0, -1), pointArray[4]);
        result = triangle.findIntersections(ray);
        assertEquals("fail VBATest 2", null, result);
        //VBA 3: ray begins on plane on corner
        triangle = new Triangle(pointArray[2], pointArray[6], pointArray[8]);
        ray = new Ray(new Vector(0, 0, -1), pointArray[2]);
        result = triangle.findIntersections(ray);
        assertEquals("fail VBATest 3", null, result);
        //VBA 4: ray begins on extension of edge
        triangle = new Triangle(pointArray[0], pointArray[1], pointArray[3]);
        ray = new Ray(new Vector(0, 0, -1), pointArray[6]);
        result = triangle.findIntersections(ray);
        assertEquals("fail VBATest 4", null, result);
        //VBA 5: ray goes through edge
        triangle = new Triangle(pointArray[1], pointArray[3], pointArray[5]);
        ray = new Ray(new Vector(0, 0, -1), p0);
        result = triangle.findIntersections(ray);
        assertEquals("fail VBATest 5", null, result);
        //VBA 6: ray goes through corner
        triangle = new Triangle(pointArray[0], pointArray[1], pointArray[4]);
        ray = new Ray(new Vector(0, 0, -1), p0);
        result = triangle.findIntersections(ray);
        assertEquals("fail VBATest 6", null, result);
        //VBA 7: ray goes through extension of edge
        triangle = new Triangle(pointArray[0], pointArray[1], pointArray[3]);
        ray = new Ray(new Vector(0, 0, -1), new Point3D(1, -1, 0));
        result = triangle.findIntersections(ray);
        assertEquals("fail VBATest 7", null, result);
        //VBA 7: triangle is behind ray
        triangle = new Triangle(pointArray[1], pointArray[6], pointArray[8]);
        ray = new Ray(new Vector(0, 0, -1), new Point3D(0, 0, -3));
        result = triangle.findIntersections(ray);
        assertEquals("fail VBATest 7", null, result);
    }
}