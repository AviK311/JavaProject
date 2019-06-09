package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Plane2 extends Plane {
    public Plane2(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, Point3D p1, Vector normal) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, p1, normal);
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> superList = super.findIntersections(ray);
        if (superList==null)
            return null;
        List<GeoPoint> list = new ArrayList<>();
        for (GeoPoint g: superList) {
            Point3D gp =g.point;
            Point3D point = gp.add(new Vector(10,0,0));
            if (Math.abs(g.point.getZ().get() - (int) g.point.getZ().get()) < 0.5) {
                g.geo.setEmission(new Color(60, 0, 0));
                list.add(g);
            }
        }
  //      superList.removeIf(gp-> Math.abs(gp.point.getZ().get()- (int)gp.point.getZ().get())<0.5);

      //  superList.addAll(list);
        //superList.removeIf(gp-> Math.abs(gp.point.getZ().get()- (int)gp.point.getZ().get())<0.5);
        return superList;
    }
}
