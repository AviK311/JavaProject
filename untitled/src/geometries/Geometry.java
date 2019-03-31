package geometries;


import java.awt.Color;
import primitives.*;
import java.util.ArrayList;

public abstract class Geometry {
    public abstract Vector getNormal(Point3D p);
    public abstract ArrayList<Point3D> FindIntersections(Ray myRay);
}
