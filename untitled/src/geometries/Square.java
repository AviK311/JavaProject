package geometries;

import primitives.*;

import java.util.List;
import java.util.concurrent.TransferQueue;

public class Square extends Plane {
    Point3D  p2, p3, p4;
    Triangle T1;
    Triangle T2;

    public Square(Color emission, int Shininess,
                  double _Kd, double _Ks, double _Kr, double _Kt,
                  Point3D P1, Vector v1,
                  Vector v2, double length) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, P1, v1.crossProduct(v2));
        if (v1.dotProduct(v2)!=0)
            throw new IllegalArgumentException("the vectors are not orthogonal");
        v1 = v1.normalize().scale(length);
        v2 = v2.normalize().scale(length);
        p2 = p1.add(v1);
        p4 = p1.add(v2);
        p3 = p4.add(v1);
        T1 = new Triangle(emission,get_material(),p1,p2,p3);
        T2 = new Triangle(emission,get_material(), p1,p4,p3);



    }
    public Square(Color emission, Material material,
                  Point3D P1, Vector v1,
                  Vector v2, double length) {
        super(emission,material, P1, v1.crossProduct(v2));
        if (v1.dotProduct(v2)!=0)
            throw new IllegalArgumentException("the vectors are not orthogonal");
        v1 = v1.normalize().scale(length);
        v2 = v2.normalize().scale(length);
        p2 = p1.add(v1);
        p4 = p1.add(v2);
        p3 = p4.add(v1);
        T1 = new Triangle(emission,get_material(),p1,p2,p3);
        T2 = new Triangle(emission,get_material(), p1,p4,p3);
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> superList = T1.findIntersections(ray);
        if (superList!=null) {
            superList.get(0).geo = this;
            return superList;
        }
        superList = T2.findIntersections(ray);
        if (superList!=null) {
            superList.get(0).geo = this;
            return superList;
        }

        return null;
    }
}
