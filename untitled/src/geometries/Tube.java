package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Tube extends RadialGeometry {
    Vector axisDirection;

    public Tube(float radius, Vector axisDirection) {
        super(radius);
        this.axisDirection = axisDirection;
    }

    public Vector getAxisDirection() {
        return axisDirection;
    }
}
