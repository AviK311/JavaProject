package geometries;


import primitives.*;

public abstract class Geometry implements IIntersectable {
    public abstract Vector getNormal(Point3D p);

}
