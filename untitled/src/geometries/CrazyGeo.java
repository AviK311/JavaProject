package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class CrazyGeo extends Geometry {
    List<Triangle> triangles;

    public CrazyGeo(Color emission, int Shininess, double _Kd, double _Ks, double _Kr, double _Kt, Point3D...points) {
        if (points.length <3)
            throw new IllegalArgumentException("CrazyGeo needs at least 3 points");
        triangles = new ArrayList<>();

        for(int i = 0; i < points.length -2; i++)
            for (int j = i+1; j<points.length-1; j++)
                for (int k = j+1; k<points.length; k++)
                    triangles.add(new Triangle(emission,Shininess,_Kd,_Ks,_Kr,_Kt,points[i], points[j], points[k]));

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
        if (intersectionPoints.isEmpty())
            return null;
        return intersectionPoints;
    }
}
