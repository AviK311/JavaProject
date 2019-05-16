package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public interface IIntersectable {
    public class GeoPoint{
        public Geometry geo;
        public Point3D point;
        public GeoPoint(Geometry geo, Point3D point) {
            this.geo = geo;
            this.point = point;
        }

    }
    List<GeoPoint> FindIntersections(Ray myRay);
}
