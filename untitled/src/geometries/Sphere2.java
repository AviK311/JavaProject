package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Sphere2 extends Geometry {
    Sphere sphere1;
    Sphere sphere2;

    public Sphere2(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, float radius, Point3D center,Vector vector) {
        sphere1 = new Sphere(emission, Shininess, _Kd, _Ks, _Kr, _Kt, radius,center);
        sphere2 = new Sphere(emission, Shininess, _Kd, _Ks, _Kr, _Kt, radius,center.add(vector.normalize().scale(radius/2)));
    }




    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        List<GeoPoint> intersectionPoints1 = sphere1.findIntersections(myRay);
        List<GeoPoint> intersectionPoints2 = sphere2.findIntersections(myRay);
        List<GeoPoint> intersectionPoints3 = new ArrayList<>();
        if (intersectionPoints1!=null&&intersectionPoints2!=null)
            for(GeoPoint g: intersectionPoints1)
                if(!(g.point.subtract(sphere2.getCenter()).length()<sphere1.radius)) {
                    intersectionPoints3.add(g);
//                    g.geo=new Sphere(new Color(0, 100, 0),
//                            100, 0.2, 0.3, 0.1, 0, 50,
//                            new Point3D(0, 0, -100));
 //                  intersectionPoints1.remove(g);
  //                  intersectionPoints2.remove(g);
                }

//        if (intersectionPoints2 !=null)
//            intersectionPoints1.removeAll(intersectionPoints2);
        if (intersectionPoints3!= null)
            return intersectionPoints3;
        return  null;
    }
}
