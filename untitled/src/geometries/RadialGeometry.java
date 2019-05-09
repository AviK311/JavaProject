package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

public class RadialGeometry extends Geometry{
    float radius;

    public RadialGeometry(float radius) {
        this.radius = radius;
    }

    public RadialGeometry(Color emmision, float radius) {
        super(emmision);
        this.radius = radius;
    }

    /**
     *
     * @return radius
     */
    public float getRadius() {
        return radius;
    }

//    public void setRadius(float radius) {
//        this.radius = radius;
//    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public ArrayList<Point3D> FindIntersections(Ray myRay) {
        return null;
    }
}
