package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Pyramid extends Geometry {
    Point3D p1,
    p2,
    p3,
    p4;
    private Triangle[]triangles = new Triangle[4];


    public Pyramid(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, Point3D p1, Point3D p2, Point3D p3, Point3D p4) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        triangles[0] = new Triangle(emission, Shininess, _Kd, _Ks ,_Kr, _Kt,p1,p2,p3);
        triangles[1] = new Triangle(emission, Shininess, _Kd, _Ks ,_Kr, _Kt,p1,p2,p4);
        triangles[2] = new Triangle(emission, Shininess, _Kd, _Ks ,_Kr, _Kt,p2,p3, p4);
        triangles[3] = new Triangle(emission, Shininess, _Kd, _Ks ,_Kr, _Kt,p1,p3, p4);
    }

    @Override
    public Vector getNormal(Point3D p) {
        for(Triangle t:triangles){
            Vector v = p.subtract(t.p1);
            if (v.dotProduct(t.normal)==0)
                return t.normal;
        }
        return null;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        List<GeoPoint> allList = new ArrayList<>();
        List<GeoPoint> eachList = new ArrayList<>();
        for (Triangle t:triangles){
             eachList = t.findIntersections(myRay);
            if (eachList!=null)
                allList.addAll(eachList);
        }
        if (allList.isEmpty())
            return null;
        return allList;
    }
}
