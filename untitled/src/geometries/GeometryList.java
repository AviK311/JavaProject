package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeometryList implements IIntersectible{
    List<IIntersectible> geometriesList;

    public GeometryList() {
        this.geometriesList = new ArrayList<>();
    }
    public Iterator<IIntersectible> getIterator(){
        return geometriesList.iterator();
    }
    public void add(IIntersectible geo){
        geometriesList.add(geo);
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
