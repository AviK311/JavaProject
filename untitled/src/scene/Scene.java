package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.*;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;


public class Scene {
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

        public void setMax_X(double x) {
            max_X = Math.max(max_X, x);
        }

        public void setMin_X(double x) {
            min_X = Math.min(min_X, x);
        }

        public void setMax_Y(double y) {
            max_Y = Math.max(max_Y, y);
        }

        public void setMin_Y(double y) {
            min_Y = Math.min(min_Y, y);
        }

        public void setMax_Z(double z) {
            max_Z = Math.max(max_Z, z);
        }

        public void setMin_Z(double z) {
            min_Z = Math.min(min_Z, z);
        }

        public boolean checkBoundaries(Ray ray) {
            //max X plane
            Plane boundaryPlane = new Plane(new Point3D(max_X, max_Y, max_Z), new Vector(1,0,0));
            List<Intersectable.GeoPoint> intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList!=null){
                Point3D intersection = intersectionList.get(0).point;
                double y = intersection.getY().get(), z = intersection.getZ().get();
                if (y > min_Y && y < max_Y
                && z > min_Z && z < max_Z)
                    return true;
            }
            //min X plane
            boundaryPlane = new Plane(new Point3D(min_X, max_Y, max_Z), new Vector(1,0,0));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList!=null){
                Point3D intersection = intersectionList.get(0).point;
                double y = intersection.getY().get(), z = intersection.getZ().get();
                if (y > min_Y && y < max_Y
                        && z > min_Z && z < max_Z)
                    return true;
            }
            //max y plane
            boundaryPlane = new Plane(new Point3D(max_X, max_Y, max_Z), new Vector(0,1,0));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList!=null){
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), z = intersection.getZ().get();
                if (x > min_X && x < max_X
                        && z > min_Z && z < max_Z)
                    return true;
            }
            //min y plane
            boundaryPlane = new Plane(new Point3D(max_X, min_Y, max_Z), new Vector(0,1,0));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList!=null){
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), z = intersection.getZ().get();
                if (x > min_X && x < max_X
                        && z > min_Z && z < max_Z)
                    return true;
            }

            //max z plane
            boundaryPlane = new Plane(new Point3D(max_X, max_Y, max_Z), new Vector(0,0,1));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList!=null){
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), y = intersection.getY().get();
                if (x > min_X && x < max_X
                        && y > min_Y && y < max_Y)
                    return true;
            }
            //min z plane
            boundaryPlane = new Plane(new Point3D(max_X, max_Y, min_Z), new Vector(0,0,1));
            intersectionList = boundaryPlane.findIntersections(ray);
            if (intersectionList!=null){
                Point3D intersection = intersectionList.get(0).point;
                double x = intersection.getX().get(), y = intersection.getY().get();
                if (x > min_X && x < max_X
                        && y > min_Y && y < max_Y)
                    return true;
            }

            return false;

        }
    }

    String sceneName;
    Color background;
    public AmbientLight ambientLight;
    Geometries geometries;
    elements.Camera camera;
    double screenDistance;
    GeometriesBox box = null;
    boolean checkBox = false;

    public List<LightSource> getLights() {
        return lights;
    }
    public boolean checkBoundaries(Ray ray){
        if (checkBox)
            return true;
        return box.checkBoundaries(ray);
    }
    List<LightSource> lights = new LinkedList<>();

    public Scene(String name) {
        this.sceneName = name;
        this.background = Color.BLACK;
        this.geometries = new Geometries();
    }

    public String getSceneName() {
        return sceneName;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public elements.Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public double getScreenDistance() {
        return screenDistance;
    }

    public void setScreenDistance(double screenDistance) {
        this.screenDistance = screenDistance;
    }

    public void addGeometry(Intersectable... geos) {
        for (Intersectable g:geos){
            if (!checkBox) {
                double minX, maxX, minY, maxY, minZ, maxZ;
                minX = maxX = minY = maxY = minZ = maxZ = 0;
                if (g instanceof Plane) {
                    if (camera.getvTo().dotProduct(((Plane) g).getNormal()) != 0)
                        checkBox = true;
                }
                if (g instanceof Triangle) {
                    Triangle t = (Triangle) g;
                    Point3D P1 = t.getP1(), P2 = t.getP2(), P3 = t.getP3();
                    minX = Double.min(Double.min(P1.getX().get(), P2.getX().get()), P3.getX().get());
                    maxX = Double.max(Double.max(P1.getX().get(), P2.getX().get()), P3.getX().get());
                    minY = Double.min(Double.min(P1.getY().get(), P2.getY().get()), P3.getY().get());
                    maxY = Double.max(Double.max(P1.getY().get(), P2.getY().get()), P3.getY().get());
                    minZ = Double.min(Double.min(P1.getZ().get(), P2.getZ().get()), P3.getZ().get());
                    maxZ = Double.max(Double.max(P1.getZ().get(), P2.getZ().get()), P3.getZ().get());
                }
                if (g instanceof Sphere) {
                    Sphere s = (Sphere) g;
                    Point3D center = s.getCenter();
                    minX = center.add(new Vector(1, 0, 0).scale(-s.getRadius())).getX().get();
                    maxX = center.add(new Vector(1, 0, 0).scale(s.getRadius())).getX().get();
                    minY = center.add(new Vector(0, 1, 0).scale(-s.getRadius())).getY().get();
                    maxY = center.add(new Vector(0, 1, 0).scale(s.getRadius())).getY().get();
                    minZ = center.add(new Vector(0, 0, 1).scale(-s.getRadius())).getZ().get();
                    maxZ = center.add(new Vector(0, 0, 1).scale(s.getRadius())).getZ().get();
                }
                if (box == null)
                    box = new GeometriesBox(maxX, minX, maxY, minY, maxZ, minZ);
                else {
                    box.setMax_X(maxX);
                    box.setMax_Y(maxY);
                    box.setMax_Z(maxZ);
                    box.setMin_X(minX);
                    box.setMin_Y(minY);
                    box.setMin_Z(minZ);
                }
            }
            geometries.add(g);
            }

        }



    public void addLight(LightSource... light) {
        for (LightSource l : light)
            lights.add(l);
    }

    @Override
    public String toString() {
        return "scene{" +
                "Name='" + sceneName + '\'' +
                ", background=" + background +
                ", ambLight=" + ambientLight +
                ", geoList=" + geometries +
                ", camera=" + camera +
                ", scrnDist=" + screenDistance +
                '}';
    }


}
