package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public interface IIntersectable {
    List<Point3D> FindIntersections(Ray myRay);
}
