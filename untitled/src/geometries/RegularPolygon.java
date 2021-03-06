package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class RegularPolygon extends Plane {
    private Point3D[] points;
    private Triangle[] triangles;
    private double maxX, minX, maxY, minY, maxZ, minZ;

    public RegularPolygon(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, Point3D p1, Vector normal, double radius, int numOfPoints) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, p1, normal);
        if (numOfPoints <= 2)
            throw new IllegalArgumentException("The polygon must have at least 3 edges!");
        points = new Point3D[numOfPoints];
        triangles = new Triangle[numOfPoints - 1];
        Point3D normHead = normal.getHead();
        double x, y, z;
        if (!Util.equals(p1.getZ().get(), 0)) {
            x = p1.getX().get() + 1;
            y = p1.getY().get() + 1;
            z = p1.getZ().get() - (1 / normHead.getZ().get() * (normHead.getX().get() + normHead.getY().get()));
        } else if (!Util.equals(p1.getY().get(), 0)) {
            x = p1.getX().get() + 1;
            y = p1.getY().get() - (1 / normHead.getY().get() * (normHead.getX().get() + normHead.getZ().get()));
            z = p1.getZ().get() + 1;
        } else {
            x = p1.getX().get() - 1 / normHead.getX().get() * (normHead.getZ().get() + normHead.getY().get());
            y = p1.getY().get() + 1;
            z = p1.getZ().get() + 1;
        }
        Vector vecToEdge = new Point3D(x, y, z).subtract(p1).rescale(radius);
        double angle = Math.toRadians(360 / (double) numOfPoints);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        for (int i = 0; i < points.length; i++) {
            points[i] = p1.add(vecToEdge);
            if (i == 0) {
                maxX = minX = points[0].getX().get();
                maxY = minY = points[0].getY().get();
                maxZ = minZ = points[0].getZ().get();
            } else {
                maxX = Double.max(maxX, points[i].getX().get());
                minX = Double.min(minX, points[i].getX().get());
                maxY = Double.max(maxY, points[i].getY().get());
                minY = Double.min(minY, points[i].getY().get());
                maxZ = Double.max(maxZ, points[i].getZ().get());
                minZ = Double.min(minZ, points[i].getZ().get());
            }
            Vector other = rodriguesRotate(normal, vecToEdge, cos, sin).rescale(radius);
            double degrees = Math.toDegrees(other.getAngle(vecToEdge));

        }
        for (int i = 0; i < triangles.length; i++) {
            triangles[i] = new Triangle(getEmission(), get_material(), p1, points[(i + 1) % points.length], points[i]);
//            System.out.println(points[(i + 1) % points.length].distance(points[i]));
        }
    }



    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionPoints;
        for (Triangle t : triangles) {
            if (t == null) continue;
            intersectionPoints = t.findIntersections(ray);
            if (intersectionPoints != null)
                return intersectionPoints;
        }
        return null;
    }
//    @Override
//    public List<GeoPoint> findIntersections(Ray ray) {
//        List<GeoPoint> intersectionPoints = super.findIntersections(ray);
//        if (intersectionPoints == null || intersectionPoints.isEmpty())
//            return null;
//        Vector[] vectors = new Vector[points.length], crossVectors = new Vector[points.length];
//        Boolean[] signs = new Boolean[points.length];
//        Point3D rayHead = ray.getHead();
//        Vector v0 = intersectionPoints.get(0).point.subtract(rayHead);
//        for (int i = 0; i < points.length; i++) {
//            vectors[i] = points[i].subtract(rayHead);
//            if (vectors[i].equals(ray.getDirection()))
//                return null;
//        }
//        for (int i = 0; i < points.length; i++) {
//            crossVectors[i] = vectors[(i + 1) % points.length].crossProduct(vectors[i]).normalize();
//            signs[i] = v0.dotProduct(crossVectors[i]) > 0;
//        }
//        boolean sign = signs[0];
//        if (Arrays.stream(signs).allMatch(val -> val == sign)){
//            List<GeoPoint> l = ll(ray);
//            if (l!=null)
//                intersectionPoints.addAll(l);
//            return intersectionPoints;
//
//        }
//        return null;
//
//
//    }

    @Override
    public double getMaxX() {
        return maxX;
    }

    @Override
    public double getMinX() {
        return minX;
    }

    @Override
    public double getMaxY() {
        return maxY;
    }

    @Override
    public double getMinY() {
        return minY;
    }

    @Override
    public double getMaxZ() {
        return maxZ;
    }

    @Override
    public double getMinZ() {
        return minZ;
    }

    /**
     * returns a rotated vector
     * @param normal
     * @param v
     * @param cos
     * @param sin
     * @return
     */
    private static Vector rodriguesRotate(Vector normal, Vector v, double cos, double sin) {
        if (Util.isZero(cos))
            return normal.crossProduct(v).scale(sin);
        else if (Util.isZero(sin))
            return v.scale(cos);
        else
            return v.scale(cos).add(normal.crossProduct(v).scale(sin));
    }
}
