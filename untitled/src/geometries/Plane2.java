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
        List<GeoPoint> list2 = new ArrayList<>();
        for (GeoPoint g: superList) {
            Point3D gp =g.point;
            Point3D point = gp.add(new Vector(10,0,0));
            if (g.point.getZ().get()%1==0||g.point.getY().get()%1==0) {
                Circle sphere = new Circle(new Color(0,0,60),g.geo.getnShininess(),g.geo._material.getKd(),
                        g.geo._material.getKs(), g.geo._material.getKr(), g.geo._material.getKt(), g.point.add(new Vector(0,0,1)) ,g.geo.getNormal(g.point),3);
                list.addAll(sphere.findIntersections(ray));
                //list2.add(g);
            }
        }
        //superList.removeAll(list2);
  //      superList.removeIf(gp-> Math.abs(gp.point.getZ().get()- (int)gp.point.getZ().get())<0.5);

        superList.addAll(list);
        //superList.removeIf(gp-> Math.abs(gp.point.getZ().get()- (int)gp.point.getZ().get())<0.5);
        return superList;
    }
}
