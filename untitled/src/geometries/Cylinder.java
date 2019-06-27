package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Cylinder extends Tube {
    Point3D axisPoint;

    /**
     * ctor with params
     *
     * @param radius
     * @param axisDirection
     * @param axisPoint
     */
    public Cylinder(float radius, Vector axisDirection, Point3D axisPoint) {
        super(radius, axisDirection);
        this.axisPoint = axisPoint;
    }

    /**
     * @return axisPoint
     */
    public Point3D getAxisPoint() {
        return axisPoint;
    }
}
