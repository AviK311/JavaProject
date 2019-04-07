package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements IIntersectible{
    List<IIntersectible> geometriesList;

    public Geometries() {
        this.geometriesList = new ArrayList<>();
    }

    @Override
    public ArrayList<Point3D> FindIntersections(Ray myRay) {
        if (geometriesList==null) return null;
        ArrayList<Point3D> allIntersections = new ArrayList<>();
        for (IIntersectible geo : geometriesList)
            allIntersections.addAll(geo.FindIntersections(myRay));
        return allIntersections;
    }
}
