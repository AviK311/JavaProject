package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    class GeoPoint {
        public Geometry geo;
        public Point3D point;

        public GeoPoint(Geometry geo, Point3D point) {
            this.geo = geo;
            this.point = point;
        }
    }

    /**
     * returns all the points of the object that intersect with myRay
     * @param myRay
     * @return list of points
     */
    List<GeoPoint> findIntersections(Ray myRay);
}
