package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;


public class Box extends Geometry{

    public List<Triangle> triangles = new ArrayList<>();

    /**
     * ctor with params
     * @param emmision
     * @param Shininess
     * @param Kd
     * @param Ks
     * @param Kr
     * @param Kt
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @param p5
     * @param p6
     * @param p7
     * @param p8
     */
    public Box(Color emmision, int Shininess, double Kd, double Ks,double Kr, double Kt, Point3D p1, Point3D p2, Point3D p3,
               Point3D p4, Point3D p5, Point3D p6,Point3D p7, Point3D p8) {
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p1,p2,p3));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p2,p3,p4));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p3,p4,p5));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p4,p5,p6));

        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p5,p6,p7));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p6,p7,p8));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p7,p8,p1));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p8,p1,p2));

        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p1,p7,p3));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p2,p8,p4));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p3,p7,p5));
        triangles.add(new Triangle(emmision, Shininess, Kd, Ks,Kr, Kt,p4,p8,p6));


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
        return intersectionPoints.isEmpty()? null:intersectionPoints;
    }
}
