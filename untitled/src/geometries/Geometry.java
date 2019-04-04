package geometries;


import java.awt.Color;
import primitives.*;
import java.util.ArrayList;

public abstract class Geometry implements IIntersectible{
    public abstract Vector getNormal(Point3D p);

}
