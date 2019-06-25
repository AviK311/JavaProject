package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> geometriesList = new ArrayList<>();
    private GeometriesBox box;
    private Geometries subGeometries = null;
    private boolean checksBoundary = true;
    private final static double BOX_GROWTH_LIMIT = 100000000;

    /**
     * ctor that defines whether the list has 3D boundaties
     *
     * @param checksBoundary
     */
    public Geometries(boolean checksBoundary) {
        this.checksBoundary = checksBoundary;
    }

    /**
     * get size of list
     *
     * @return
     */
    public int getSize() {
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


        private boolean checkPlane(Ray ray, double x_, double y_, double z_, Vector vector) {
            Plane boundaryPlane = new Plane(new Point3D(x_, y_, z_), vector);
            List<Intersectable.GeoPoint> intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList != null) {
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), y = intersection.getY().get(), z = intersection.getZ().get();
                if (vector.getHead().getX().get() == 1)
                    if (y > min_Y && y < max_Y
                            && z > min_Z && z < max_Z)
                        return true;
                    else if (vector.getHead().getY().get() == 1)
                        if (x > min_X && x < max_X
                                && z > min_Z && z < max_Z)
                            return true;
                        else if (x > min_X && x < max_X
                                && y > min_Y && y < max_Y)
                            return true;
            }
            return false;
        }

        /**
         * checks whether ray intersects with the box
         *
         * @param ray
         * @return
         */
        public boolean checkBoundaries(Ray ray) {
//            return (checkPlane(ray, max_X, max_Y, max_Z, new Vector(1, 0, 0)) ||   //max X plane
//                    checkPlane(ray, min_X, max_Y, max_Z, new Vector(1, 0, 0)) ||   //min X plane
//                    checkPlane(ray, max_X, max_Y, max_Z, new Vector(0, 1, 0)) ||   //max Y plane
//                    checkPlane(ray, max_X, min_Y, max_Z, new Vector(0, 1, 0)) ||   //min Y plane
//                    checkPlane(ray, max_X, max_Y, max_Z, new Vector(0, 0, 1)) ||   //max Z plane
//                    checkPlane(ray, max_X, max_Y, min_Z, new Vector(0, 0, 1)));   //min Z plane
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
     * adding an intersectable to the geometries:
     * if adding it to the son list will expand the son list box too much, it is added to the parent list.
     * if adding it to the son list makes the son box only slightly smaller than the father box,
     * the son list and the parent list are merged into one.
     * if the parent list is empty, g is automatically added to the parent list
     *
     * @param g
     */
    public void add(Intersectable g) {
        if ((g.getClass().equals(Plane.class))) {
            geometriesList.add(g);
            if (subGeometries != null)
                subGeometries.add(g);
            return;
        }
        if (checksBoundary) {
            expandBox(g);
        }
        if (geometriesList.isEmpty()) {//if parent is empty, add to parent
            geometriesList.add(g);
            return;
        } else if (subGeometries == null) {//if son is uninitialized, add to son
            subGeometries = new Geometries(checksBoundary);
            subGeometries.add(g);
        } else if (subGeometries.getBoxSizeDifference(g) > BOX_GROWTH_LIMIT)
            //if the son is initialized, but the box growth is too big
            geometriesList.add(g);
        else {
            subGeometries.add(g);
        }
        if (subGeometries.getBoxSizeDifference(this) < BOX_GROWTH_LIMIT) {
            for (Intersectable i : subGeometries.geometriesList)
                geometriesList.add(i);
            subGeometries = subGeometries.subGeometries;
        }

    }

    /**
     * expands the current box size according to the size of a geometry
     *
     * @param g
     */
    public void expandBox(Intersectable g) {
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


    @Override
    public List<GeoPoint> findIntersections(Ray myRay) {
        if (geometriesList == null) return null;
        List<GeoPoint> allIntersections = new ArrayList<>();
        for (Intersectable geo : geometriesList) {
            List<GeoPoint> i = geo.findIntersections(myRay);
            if (i != null)
                allIntersections.addAll(i);
        }
        if (subGeometries != null && subGeometries.checkBoundaries(myRay))
            allIntersections.addAll(subGeometries.findIntersections(myRay));

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
     *
     * @param ray
     * @return
     */
    public boolean checkBoundaries(Ray ray) {
        if (geometriesList.size() == 0)
            return false;
        if (box == null)
            return true;
        return box.checkBoundaries(ray);
    }

    /**
     * checks what the size difference would be if the intersectable is added
     *
     * @param intersectable
     * @return size difference - double
     */
    private double getBoxSizeDifference(Intersectable intersectable) {
        double maxX = getMaxX(), maxY = getMaxY(), maxZ = getMaxZ(),
                minX = getMinX(), minY = getMinY(), minZ = getMinZ();
        double boxSize = (maxX - minX) * (maxY - minY) * (maxZ - minZ);
        maxX = Double.max(maxX, intersectable.getMaxX());
        maxY = Double.max(maxY, intersectable.getMaxY());
        maxZ = Double.max(maxZ, intersectable.getMaxZ());

        minX = Double.min(minX, intersectable.getMinX());
        minY = Double.min(minY, intersectable.getMinY());
        minZ = Double.min(minZ, intersectable.getMinZ());

        double newBoxSize = (maxX - minX) * (maxY - minY) * (maxZ - minZ);
        return newBoxSize - boxSize;
    }

    /**
     * returns the depth of the tree
     *
     * @return
     */
    public int getDepth() {
        if (subGeometries == null)
            return 1;
        return 1 + subGeometries.getDepth();
    }
}
