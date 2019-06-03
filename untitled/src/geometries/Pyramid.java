package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Pyramid extends Geometry {
    public List<Triangle> triangles = new ArrayList<>();

    public Pyramid(Color emmision, int Shininess, double Kd, double Ks,double Kr, double Kt, Point3D p1, Point3D p2, Point3D p3,
               Point3D p4) {
        Triangle triangle1=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p1,p2,p3);
        Triangle triangle2=new Triangle(emmision.scale(2), Shininess, Kd, Ks,Kr, Kt,p1,p2,p4);
        Triangle triangle3=new Triangle(emmision.add(new Color(20,0,0)), Shininess, Kd, Ks,Kr, Kt,p1,p3,p4);
        Triangle triangle4=new Triangle(emmision.scale(0.5), Shininess, Kd, Ks,Kr, Kt,p2,p3,p4);

        addGeometry(triangle1,triangle2,triangle3,triangle4);
    }

    public void addGeometry(Triangle... tr) {
        for(Triangle t: tr)
            triangles.add(t);
    }



    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        List<GeoPoint> intersectionPoints = new ArrayList<>();
        for(Triangle t: triangles)
            if(t.findIntersections(myRay)!=null)
                intersectionPoints.addAll(t.findIntersections(myRay));
        return intersectionPoints;
    }
}
