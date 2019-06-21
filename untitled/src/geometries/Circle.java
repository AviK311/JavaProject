package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Circle extends Plane {
    double radius;
    /**
     * enclosing square to get max and min x, y, z
     */
    private Square enclosingSquare;

    /**
     * ctor with params that initializes enclosing square
     * @param emission
     * @param Shininess
     * @param _Kd
     * @param _Ks
     * @param _Kr
     * @param _Kt
     * @param p1
     * @param normal
     * @param Radius
     */
    public Circle(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, Point3D p1, Vector normal, double Radius) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, p1, normal);
        radius = Radius;
        Point3D normHead = normal.getHead();
        double x, y, z;
        if (p1.getZ().get()!=0) {
             x = p1.getX().get() + 1;
             y = p1.getY().get() + 1;
             z = p1.getZ().get() - 1 / normHead.getZ().get() * (normHead.getX().get() + normHead.getY().get());
        }
        else if (p1.getY().get()!=0) {
             x = p1.getX().get() + 1;
             y = p1.getY().get() - 1 / normHead.getY().get() * (normHead.getX().get() + normHead.getZ().get());
             z = p1.getZ().get() + 1;
        }
        else{
            x = p1.getX().get() -1 / normHead.getX().get() * (normHead.getZ().get() + normHead.getY().get());
            y = p1.getY().get() + 1;
            z = p1.getZ().get() + 1;
        }
        Vector vecToEdge = new Point3D(x,y,z).subtract(p1).normalize();
        Vector otherVecToEdge = vecToEdge.crossProduct(normal);
        Point3D squarePoint = p1.add(vecToEdge.scale(radius)).add(otherVecToEdge.scale(radius));
        enclosingSquare = new Square(squarePoint, vecToEdge.scale(-1),otherVecToEdge.scale(-1), 2*radius);

    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> superList =  super.findIntersections(ray);
        if (superList == null) return null;
        if (superList.get(0).point.distance(p1) > radius)
            return null;
        return superList;
    }

    @Override
    public double getMaxX() {
        return enclosingSquare.getMaxX();
    }

    @Override
    public double getMinX() {
        return enclosingSquare.getMinX();
    }

    @Override
    public double getMaxY() {
        return enclosingSquare.getMaxY();
    }

    @Override
    public double getMinY() {
        return enclosingSquare.getMinY();
    }

    @Override
    public double getMaxZ() {
        return enclosingSquare.getMaxZ();
    }

    @Override
    public double getMinZ() {
        return enclosingSquare.getMinZ();
    }
}
