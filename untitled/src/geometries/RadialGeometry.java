package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

public abstract class RadialGeometry extends Geometry {
    float radius;

    /**
     * radius ctor
     * @param radius
     */
    public RadialGeometry(float radius) {
        this.radius = radius;
    }

    /**
     * emission and radius ctor
     * @param emission
     * @param radius
     */
    public RadialGeometry(Color emission, float radius) {
        super(emission);
        this.radius = radius;
    }

    /**
     * ctor with more params
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     * @param radius
     */
    public RadialGeometry(Color emission, int Shininess, double _Kd, double _Ks, float radius) {
        super(emission, Shininess, _Kd, _Ks);
        this.radius = radius;
    }

    /**
     * @return radius
     */
    public  float getRadius() {
        return radius;
    }
}
