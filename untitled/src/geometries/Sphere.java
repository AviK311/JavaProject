package geometries;
import primitives.Point3D;
public class Sphere extends RadialGeometry {
    public Sphere(float radius, Point3D center) {
        super(radius);
        this.center = center;
    }

    public Point3D getCenter() {
        return center;
    }

    public void setCenter(Point3D center) {
        this.center = center;
    }

    private Point3D center;
}
