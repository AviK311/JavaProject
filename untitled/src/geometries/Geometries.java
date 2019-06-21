package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> geometriesList = new ArrayList<>();
    private GeometriesBox box;
    private boolean checksBoundary = true;

    /**
     * ctor that defines whether the list has 3D boundaties
     * @param checksBoundary
     */
    public Geometries(boolean checksBoundary) {
        this.checksBoundary = checksBoundary;
    }

    /**
     * get size of list
     * @return
     */
    public int getSize(){
        return geometriesList.size();
    }

    /**
     * Box that contains geometries, to define whether a ray has intersections
     */
    private class GeometriesBox {
        double max_X, min_X, max_Y, min_Y, max_Z, min_Z;

        public GeometriesBox(double max_X,
                             double min_X,
                             double max_Y,
                             double min_Y,
                             double max_Z,
                             double min_Z) {
            this.max_X = max_X;
            this.min_X = min_X;
            this.max_Y = max_Y;
            this.min_Y = min_Y;
            this.max_Z = max_Z;
            this.min_Z = min_Z;
        }

        /**
         * set max x
         *
         * @param x
         */
        public void setMax_X(double x) {
            max_X = Math.max(max_X, x);
        }

        /**
         * set min x
         *
         * @param x
         */
        public void setMin_X(double x) {
            min_X = Math.min(min_X, x);
        }

        /**
         * set max y
         *
         * @param y
         */
        public void setMax_Y(double y) {
            max_Y = Math.max(max_Y, y);
        }

        /**
         * set min y
         *
         * @param y
         */
        public void setMin_Y(double y) {
            min_Y = Math.min(min_Y, y);
        }

        /**
         * set max z
         *
         * @param z
         */
        public void setMax_Z(double z) {
            max_Z = Math.max(max_Z, z);
        }

        /**
         * set min z
         *
         * @param z
         */
        public void setMin_Z(double z) {
            min_Z = Math.min(min_Z, z);
        }

        /**
         * checks whether ray intersects with the box
         *
         * @param ray
         * @return
         */
        public boolean checkBoundaries(Ray ray) {
            //max X plane
            Plane boundaryPlane = new Plane(new Point3D(max_X, max_Y, max_Z), new Vector(1, 0, 0));
            List<Intersectable.GeoPoint> intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList != null) {
                Point3D intersection = intersectionList.get(0).point;
                double y = intersection.getY().get(), z = intersection.getZ().get();
                if (y > min_Y && y < max_Y
                        && z > min_Z && z < max_Z)
                    return true;
            }
            //min X plane
            boundaryPlane = new Plane(new Point3D(min_X, max_Y, max_Z), new Vector(1, 0, 0));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList != null) {
                Point3D intersection = intersectionList.get(0).point;
                double y = intersection.getY().get(), z = intersection.getZ().get();
                if (y > min_Y && y < max_Y
                        && z > min_Z && z < max_Z)
                    return true;
            }
            //max y plane
            boundaryPlane = new Plane(new Point3D(max_X, max_Y, max_Z), new Vector(0, 1, 0));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList != null) {
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), z = intersection.getZ().get();
                if (x > min_X && x < max_X
                        && z > min_Z && z < max_Z)
                    return true;
            }
            //min y plane
            boundaryPlane = new Plane(new Point3D(max_X, min_Y, max_Z), new Vector(0, 1, 0));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList != null) {
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), z = intersection.getZ().get();
                if (x > min_X && x < max_X
                        && z > min_Z && z < max_Z)
                    return true;
            }

            //max z plane
            boundaryPlane = new Plane(new Point3D(max_X, max_Y, max_Z), new Vector(0, 0, 1));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList != null) {
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), y = intersection.getY().get();
                if (x > min_X && x < max_X
                        && y > min_Y && y < max_Y)
                    return true;
            }
            //min z plane
            boundaryPlane = new Plane(new Point3D(max_X, max_Y, min_Z), new Vector(0, 0, 1));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList != null) {
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), y = intersection.getY().get();
                if (x > min_X && x < max_X
                        && y > min_Y && y < max_Y)
                    return true;
            }

            return false;

        }
    }

    /**
     * adds geos to the list, while handling the boundaries of the box containing the geometries
     *
     * @param geos
     */
    public void add(Intersectable... geos) {
        if (!checksBoundary)
            for (Intersectable g : geos)
                geometriesList.add(g);
        else
            for (Intersectable g : geos) {
                geometriesList.add(g);
                if (!(g.getClass().equals(Plane.class))) {
                    if (box == null)
                        box = new GeometriesBox(g.getMaxX(), g.getMinX(),
                                g.getMaxY(), g.getMinY(),
                                g.getMaxZ(), g.getMinZ());
                    else {
                        box.setMax_X(g.getMaxX());
                        box.setMax_Y(g.getMaxY());
                        box.setMax_Z(g.getMaxZ());
                        box.setMin_X(g.getMinX());
                        box.setMin_Y(g.getMinY());
                        box.setMin_Z(g.getMinZ());
                    }
                }

            }
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

    @Override
    public double getMaxX() {
        return box.max_X;
    }

    @Override
    public double getMinX() {
        return box.min_X;
    }

    @Override
    public double getMaxY() {
        return box.max_Y;
    }

    @Override
    public double getMinY() {
        return box.min_Y;
    }

    @Override
    public double getMaxZ() {
        return box.max_Z;
    }

    @Override
    public double getMinZ() {
        return box.min_Z;
    }

    /**
     * check if the ray intersects the group of geometries
     * @param ray
     * @return
     */
    public boolean checkBoundaries(Ray ray) {
        if (geometriesList.size()==0)
            return false;
        if (box == null)
            return true;
        return box.checkBoundaries(ray);
    }
}
