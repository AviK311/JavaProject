package geometries;


import primitives.*;

public abstract class Geometry implements IIntersectable {

    private Color emmision;
    public abstract Vector getNormal(Point3D p);


    public Geometry() {
        this.emmision = new Color();
    }

    public Geometry(Color emmision) {
        this.emmision = emmision;
    }
}
