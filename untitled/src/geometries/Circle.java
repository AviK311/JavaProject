package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Circle extends Plane {
    double radius;
    public Circle(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, Point3D p1, Vector normal, double Radius) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, p1, normal);
        radius = Radius;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> superList =  super.findIntersections(ray);
        if (superList == null) return null;
        if (superList.get(0).point.distance(p1) > radius)
            return null;
        return superList;
    }
}
