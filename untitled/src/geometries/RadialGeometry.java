package geometries;

import primitives.Color;

public abstract class RadialGeometry extends Geometry {
    double radius;

    /**
     * radius ctor
     *
     * @param radius
     */
    public RadialGeometry(float radius) {
        this.radius = radius;
    }

    /**
     * emission and radius ctor
     *
     * @param emission
     * @param radius
     */
    public RadialGeometry(Color emission, float radius) {
        super(emission);
        this.radius = radius;
    }

    /**
     * ctor with more params
     *
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     * @param radius
     */
    public RadialGeometry(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, double radius) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt);
        this.radius = radius;
    }

    /**
     * @return radius
     */
    public double getRadius() {
        return radius;
    }
}
