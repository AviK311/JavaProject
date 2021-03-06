package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry implements Intersectable {
    /**
     * ctor with material as a param
     *
     * @param color
     * @param _material
     */
    public Geometry(Color color, Material _material) {
        this._material = _material;
        this.emission = color;
    }

    /**
     * returns the emission
     *
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
     *
     * @param p
     * @return normal vector
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * return the shininess of the geometry
     *
     * @return shininess
     */
    public int getnShininess() {
        return _material.getnShininess();
    }

    /**
     * return the material of the geometry
     *
     * @return material
     */
    public Material get_material() {
        return _material;
    }

    Material _material;

    /**
     * ctor for black
     */
    public Geometry() {
        this.emission = new Color();
    }

    /**
     * ctor with color
     *
     * @param emission
     */
    public Geometry(Color emission) {
        this.emission = emission;
    }

    /**
     * params ctor
     *
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
