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

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GeoPoint Geo = (GeoPoint) obj;
            return Geo.point.equals(point) && Geo.geo.equals(geo);
        }
    }
    List<GeoPoint> FindIntersections(Ray myRay);
}
