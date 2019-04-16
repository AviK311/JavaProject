package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Tube extends RadialGeometry {
    Vector axisDirection;

    /**
     * ctor with params
     * @param radius
     * @param axisDirection
     */
    public Tube(float radius, Vector axisDirection) {
        super(radius);
        this.axisDirection = axisDirection;
    }

    /**
     *
     * @return axisDirection
     */
    public Vector getAxisDirection() {
        return axisDirection;
    }
}
