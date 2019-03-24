package geometries;

import primitives.Point3D;
import primitives.Vector;

public class RadialGeometry extends Geometry{
    float radius;

    public RadialGeometry(float radius) {
        this.radius = radius;
    }
    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
