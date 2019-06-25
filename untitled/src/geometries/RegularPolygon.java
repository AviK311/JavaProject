package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegularPolygon extends Plane {
    private Point3D[] points;
    private Triangle[] triangles;
    private double cos, sin;
    private double maxX, minX, maxY, minY, maxZ, minZ;


    public RegularPolygon(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, Point3D p1, Vector normal, double radius, int numOfPoints) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, p1, normal);
        if (numOfPoints <=2)
            throw new IllegalArgumentException("The polygon must have at least 3 edges!");
        points = new Point3D[numOfPoints];
        triangles = new Triangle[numOfPoints];
        Point3D normHead = normal.getHead();
        double x, y, z;
        if (p1.getZ().get() != 0) {
            x = p1.getX().get() + 1;
            y = p1.getY().get() + 1;
            z = p1.getZ().get() - 1 / normHead.getZ().get() * (normHead.getX().get() + normHead.getY().get());
        } else if (p1.getY().get() != 0) {
            x = p1.getX().get() + 1;
            y = p1.getY().get() - 1 / normHead.getY().get() * (normHead.getX().get() + normHead.getZ().get());
            z = p1.getZ().get() + 1;
        } else {
            x = p1.getX().get() - 1 / normHead.getX().get() * (normHead.getZ().get() + normHead.getY().get());
            y = p1.getY().get() + 1;
            z = p1.getZ().get() + 1;
        }


        Vector vecToEdge = new Point3D(x, y, z).subtract(p1).normalize().scale(radius);
        double angle = Math.toRadians(360/numOfPoints);
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        for (int i = 0; i < points.length; i++) {
            System.out.println(vecToEdge.dotProduct(normal));
            points[i] = p1.add(vecToEdge);
            if (i == 0) {
                maxX = minX = points[0].getX().get();
                maxY = minY = points[0].getY().get();
                maxZ = minZ = points[0].getZ().get();
            }
            else {
//                triangles[i] = new Triangle(getEmission(),get_material(),p1, points[i], points[i-1]);
                maxX = Double.max(maxX, points[i].getX().get());
                minX = Double.min(minX, points[i].getX().get());

                maxY = Double.max(maxY, points[i].getY().get());
                minY = Double.min(minY, points[i].getY().get());

                maxZ = Double.max(maxZ, points[i].getZ().get());
                minZ = Double.min(minZ, points[i].getZ().get());
            }
            vecToEdge =  rodriguesRotate(normal, vecToEdge, angle).normalize().scale(radius);
        }
        for (int i = 0; i<triangles.length; i++)
            triangles[i] = new Triangle(getEmission(),get_material(),p1, points[(i+1)%points.length], points[i]);


    }
    public List<GeoPoint> ll(Ray ray) {
        ArrayList<GeoPoint> list = new ArrayList<>();
        Sphere[] spheres = new Sphere[points.length];
        for(int i = 0; i<points.length; i++){
            spheres[i] = new Sphere(new Color(40,40,40),
                    0,0,0,0,0,
                    5, points[i]);
        }
        for(Sphere s: spheres){
            List<GeoPoint> l = s.findIntersections(ray);
            if (l!=null)
                list.addAll(l);
        }
        return list.isEmpty()?null:list;


    }
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionPoints;
        for (Triangle t:triangles){
            if (t==null)continue;
            intersectionPoints = t.findIntersections(ray);
            if (intersectionPoints!=null)
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
     * @param angle
     * @return
     */
    private static Vector rodriguesRotate(Vector normal, Vector v, double angle) {
        return v.scale(Math.cos(angle)).add(normal.crossProduct(v).scale(Math.sin(angle))).add(normal.scale(normal.dotProduct(v) * (1 - Math.cos(angle))));
    }


}
