package geometries;


import primitives.*;

public abstract class Geometry implements IIntersectable {

    public Color getEmmision() {
        return emmision;
    }

    private Color emmision;
    public abstract Vector getNormal(Point3D p);

    public int getnShininess() {
        return nShininess;
    }

    int nShininess;

    public Material get_material() {
        return _material;
    }

    Material _material;


    public Geometry() {
        this.emmision = new Color();
    }
    public Geometry(Color emmision) {
        this.emmision = emmision;
    }

    public Geometry(Color emmision, int Shininess, double _Kd, double _Ks) {
        this.emmision = emmision;
        this.nShininess = Shininess;
        this._material = new Material(_Kd, _Ks);
    }
}
