package geometries;
import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    private Point3D center;
    public Sphere(float radius, Point3D center) {
        super(radius);
        this.center = center;
    }

    public Point3D getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector v=center.subtract(p);
        return v.normalize();
    }
}
