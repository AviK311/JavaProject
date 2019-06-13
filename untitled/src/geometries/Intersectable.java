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

    /**
     * max x point of the shape
     * @return
     */
    double getMaxX();

    /**
     * mix x point of the shape
     * @return
     */
    double getMinX();

    /**
     * max y point of the shape
     * @return
     */
    double getMaxY();

    /**
     * mix y point of the shape
     * @return
     */
    double getMinY();

    /**
     * max z point of the shape
     * @return
     */
    double getMaxZ();

    /**
     * miz z point of the shape
     * @return
     */
    double getMinZ();
}
