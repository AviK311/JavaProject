package geometries;


import primitives.*;

public abstract class Geometry implements Intersectable {
    /**
     * returns the emission
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    public void setEmission(Color emission) {
        this.emission = emission;
    }

    private Color emission;

    /**
     * returns the normal vector from a specific point on the geometry
     * @param p
     * @return normal vector
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * return the shininess of the geometry
     * @return shininess
     */
    public int getnShininess() {
        return _material.getnShininess();
    }


    /**
     * return the material of the geometry
     * @return material
     */
    public Material get_material() {
        return _material;
    }

    Material _material;


    public Geometry() {
        this.emission = new Color();
    }
    public Geometry(Color emission) {
        this.emission = emission;
    }

    /**
     * params ctor
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     */
    public Geometry(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt) {
        this.emission = emission;
        this._material = new Material(_Kd, _Ks, _Kr, _Kt, Shininess);
    }
}
