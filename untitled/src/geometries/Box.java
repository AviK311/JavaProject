package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Box extends Geometry{

    private List<Square> squares = new ArrayList<>();


    public Box(Color emmision, int Shininess, double Kd, double Ks,double Kr, double Kt,
               Point3D p1, Vector v1, Vector v2, double length){
        super(emmision,Shininess, Kd, Ks, Kr, Kt);
        if (v1.dotProduct(v2)!=0)
            throw new IllegalArgumentException("Vectors must be orthogonal");
        v1 = v1.normalize();
        v2 = v2.normalize();
        Vector v3 = v1.crossProduct(v2);
        squares.add(new Square(emmision, get_material(), p1, v1,v2, length));
        squares.add(new Square(emmision, get_material(), p1, v1,v3, length));
        squares.add(new Square(emmision, get_material(), p1, v2,v3, length));
        Point3D p2 = p1.add(v1.scale(length));
        squares.add(new Square(emmision, get_material(), p2, v2,v3, length));
        p2 = p1.add(v2.scale(length));
        squares.add(new Square(emmision, get_material(), p2, v1,v3, length));
        p2 = p1.add(v3.scale(length));
        squares.add(new Square(emmision, get_material(), p2, v1,v2, length));


    }






    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        List<GeoPoint> intersectionPoints = new ArrayList<>();
        for(Square s: squares) {
            List<GeoPoint> tempList = s.findIntersections(myRay);
            if (tempList != null)
                intersectionPoints.addAll(tempList);
        }
        return intersectionPoints.isEmpty()? null:intersectionPoints;
    }

    @Override
    public double getMaxX() {
        double max = squares.get(0).getMaxX();
        for (Square s: squares)
            max = Double.max(max, s.getMaxX());
        return max;
    }

    @Override
    public double getMinX() {
        double min = squares.get(0).getMinX();
        for (Square s: squares)
            min = Double.min(min, s.getMinX());
        return min;
    }

    @Override
    public double getMaxY() {
        double max = squares.get(0).getMaxY();
        for (Square s: squares)
            max = Double.max(max, s.getMaxY());
        return max;
    }

    @Override
    public double getMinY() {
        double min = squares.get(0).getMinY();
        for (Square s: squares)
            min = Double.min(min, s.getMinY());
        return min;
    }

    @Override
    public double getMaxZ() {
        double max = squares.get(0).getMaxZ();
        for (Square s: squares)
            max = Double.max(max, s.getMaxZ());
        return max;
    }

    @Override
    public double getMinZ() {
        double min = squares.get(0).getMinZ();
        for (Square s: squares)
            min = Double.min(min, s.getMinZ());
        return min;
    }
}
