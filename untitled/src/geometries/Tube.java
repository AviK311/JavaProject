package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        return null;
    }

    @Override
    public double getMaxX() {
        return 0;
    }

    @Override
    public double getMinX() {
        return 0;
    }

    @Override
    public double getMaxY() {
        return 0;
    }

    @Override
    public double getMinY() {
        return 0;
    }

    @Override
    public double getMaxZ() {
        return 0;
    }

    @Override
    public double getMinZ() {
        return 0;
    }
}
