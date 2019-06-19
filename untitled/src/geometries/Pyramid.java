package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Pyramid extends Geometry {
    public List<Triangle> triangles = new ArrayList<>();

    public Pyramid(Color emission, int Shininess, double Kd, double Ks,double Kr, double Kt, Point3D p1, Point3D p2, Point3D p3,
               Point3D p4) {
        Triangle triangle1=new Triangle(emission, Shininess, Kd, Ks,Kr, Kt,p1,p2,p3);
        Triangle triangle2=new Triangle(emission.scale(2), Shininess, Kd, Ks,Kr, Kt,p1,p2,p4);
        Triangle triangle3=new Triangle(emission.add(new Color(20,0,0)), Shininess, Kd, Ks,Kr, Kt,p1,p3,p4);
        Triangle triangle4=new Triangle(emission.scale(0.5), Shininess, Kd, Ks,Kr, Kt,p2,p3,p4);

        addGeometry(triangle1,triangle2,triangle3,triangle4);
    }

    public Pyramid(Color emission, int Shininess, double Kd, double Ks,double Kr, double Kt, Point3D p1, Point3D p2, Point3D p3,
                   Point3D p4,Point3D p5) {
        Triangle triangle1=new Triangle(emission, Shininess, Kd, Ks,Kr, Kt,p1,p2,p3);
        Triangle triangle2=new Triangle(emission, Shininess, Kd, Ks,Kr, Kt,p1,p2,p4);
        Triangle triangle3=new Triangle(emission.add(new Color(20,0,0)), Shininess, Kd, Ks,Kr, Kt,p1,p2,p5);
        Triangle triangle4=new Triangle(emission.scale(0.9), Shininess, Kd, Ks,Kr, Kt,p2,p3,p5);
        Triangle triangle5=new Triangle(emission.scale(1.7), Shininess, Kd, Ks,Kr, Kt,p4,p3,p5);
        Triangle triangle6=new Triangle(emission.scale(1.2), Shininess, Kd, Ks,Kr, Kt,p1,p4,p5);

        addGeometry(triangle1,triangle2,triangle3,triangle4,triangle5,triangle6);
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

    @Override
    public double getMaxX() {
        double max = triangles.get(0).getMaxX();
        for (Triangle t: triangles)
            max = Double.max(max, t.getMaxX());
        return max;
    }

    @Override
    public double getMinX() {
        double min = triangles.get(0).getMinX();
        for (Triangle t: triangles)
            min = Double.max(min, t.getMinX());
        return min;
    }

    @Override
    public double getMaxY() {
        double max = triangles.get(0).getMaxY();
        for (Triangle t: triangles)
            max = Double.max(max, t.getMaxY());
        return max;
    }

    @Override
    public double getMinY() {
        double min = triangles.get(0).getMinY();
        for (Triangle t: triangles)
            min = Double.max(min, t.getMinY());
        return min;
    }

    @Override
    public double getMaxZ() {
        double max = triangles.get(0).getMaxZ();
        for (Triangle t: triangles)
            max = Double.max(max, t.getMaxZ());
        return max;
    }

    @Override
    public double getMinZ() {
        double min = triangles.get(0).getMinZ();
        for (Triangle t: triangles)
            min = Double.max(min, t.getMinZ());
        return min;
    }
}
