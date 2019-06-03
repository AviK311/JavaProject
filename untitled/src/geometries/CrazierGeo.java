package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class CrazierGeo extends CrazyGeo {
    List<Sphere> spheres = new ArrayList<>();

    public CrazierGeo(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, float radius, Point3D... points) {
        super(emission, Shininess, _Kd, _Ks, _Kr, _Kt, points);
        for(Point3D p:points)
            spheres.add(new Sphere(radius,p));
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        List<GeoPoint> superList =  super.findIntersections(myRay);
        List<GeoPoint> thisList = new ArrayList<>();
        for (Sphere s: spheres){
            List<GeoPoint> sphereList = s.findIntersections(myRay);
            if (sphereList!=null)
                thisList.addAll(sphereList);
        }
        if (superList !=null)
            thisList.addAll(superList);
        if (thisList.isEmpty())
            return null;
        return thisList;

    }
}
