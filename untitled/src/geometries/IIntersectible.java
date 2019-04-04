package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;

public interface IIntersectible {
    ArrayList<Point3D> FindIntersections(Ray myRay);
}
