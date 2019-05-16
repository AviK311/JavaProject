package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeometryList implements IIntersectable {
    List<IIntersectable> geometriesList;

    public GeometryList() {
        this.geometriesList = new ArrayList<>();
    }
    public Iterator<IIntersectable> getIterator(){
        return geometriesList.iterator();
    }
    public void add(IIntersectable geo){
        geometriesList.add(geo);
    }

    @Override
    public ArrayList<GeoPoint> FindIntersections(Ray myRay) {
        if (geometriesList==null) return null;
        ArrayList<GeoPoint> allIntersections = new ArrayList<>();
        for (IIntersectable geo : geometriesList)
            allIntersections.addAll(geo.FindIntersections(myRay));
        return allIntersections;
    }
}
