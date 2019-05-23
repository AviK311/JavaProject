package geometries;

import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> geometriesList = new ArrayList<>();

    /**
     * adds geos to the list
     * @param geos
     */
    public void add(Intersectable... geos) {
        for  (Intersectable geo : geos)
            geometriesList.add(geo);
    }

    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        if (geometriesList == null) return null;
        List<GeoPoint> allIntersections = new ArrayList<>();
        for (Intersectable geo : geometriesList) {
            List<GeoPoint> i = geo.findIntersections(myRay);
            if (i != null)
                allIntersections.addAll(i);
        }
        return allIntersections;
    }
}
