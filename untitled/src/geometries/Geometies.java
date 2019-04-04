package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometies implements IIntersectible{
    List<IIntersectible> geometriesList;
    @Override
    public ArrayList<Point3D> FindIntersections(Ray myRay) {
        if (geometriesList==null) return null;
        ArrayList<Point3D> allIntersections = new ArrayList<>();
        for (IIntersectible geo : geometriesList)
            allIntersections.addAll(geo.FindIntersections(myRay));
        return allIntersections;
    }
}
