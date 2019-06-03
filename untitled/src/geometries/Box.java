package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Box extends Geometry{

    public List<Triangle> triangles = new ArrayList<>();

    public Box(Color emmision, int Shininess, double Kd, double Ks,double Kr, double Kt, Point3D p1, Point3D p2, Point3D p3,
               Point3D p4, Point3D p5, Point3D p6,Point3D p7, Point3D p8) {
        Triangle triangle1=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p1,p2,p3);
        Triangle triangle2=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p2,p3,p4);
        Triangle triangle3=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p3,p4,p5);
        Triangle triangle4=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p4,p5,p6);

        Triangle triangle5=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p5,p6,p7);
        Triangle triangle6=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p6,p7,p8);
        Triangle triangle7=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p7,p8,p1);
        Triangle triangle8=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p8,p1,p2);

        Triangle triangle9=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p1,p7,p3);
        Triangle triangle10=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p2,p8,p4);
        Triangle triangle11=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p3,p7,p5);
        Triangle triangle12=new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p4,p8,p6);
        addGeometry(triangle1,triangle2,triangle3,triangle4,triangle5,triangle6);
        addGeometry(triangle7,triangle8,triangle9,triangle10,triangle11,triangle12);

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
