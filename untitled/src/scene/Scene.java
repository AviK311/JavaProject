package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Plane;
import primitives.Color;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    String sceneName;
    Color background;
    public AmbientLight ambientLight;
    Geometries nonPlaneGeometries = new Geometries(true);
    Geometries planes = new Geometries(false);
    elements.Camera camera;
    double screenDistance;

    /**
     * get list of lights
     *
     * @return list
     */
    public List<LightSource> getLights() {
        return lights;
    }

    /**
     * checks whether the ray intersects with the scene
     *
     * @param ray
     * @return
     */
    public boolean checkBoundaries(Ray ray) {
        return nonPlaneGeometries.checkBoundaries(ray);
    }

    List<LightSource> lights = new LinkedList<>();

    /**
     * ctor with name
     *
     * @param name
     */
    public Scene(String name) {
        this.sceneName = name;
        this.background = Color.BLACK;
    }

    /**
     * return name
     *
     * @return
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * return background color
     *
     * @return
     */
    public Color getBackground() {
        return background;
    }

    /**
     * get ambient light
     *
     * @return
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * set ambient light
     *
     * @param ambientLight
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    /**
     * functio
     *
     * @param type if 0, returns the nonPlane list. else, returns the plane list.
     * @return
     */
    public Geometries getGeometries(int type) {
        if (type == 0)
            return nonPlaneGeometries;
        return planes;
    }

    /**
     * returns the camera
     *
     * @return
     */
    public elements.Camera getCamera() {
        return camera;
    }

    /**
     * sets the camera
     *
     * @param camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * gets the distance of the camera from the screen
     *
     * @return
     */
    public double getScreenDistance() {
        return screenDistance;
    }

    /**
     * sets the distance of the camera from the screen
     *
     * @param screenDistance
     */
    public void setScreenDistance(double screenDistance) {
        this.screenDistance = screenDistance;
    }

    /**
     * add geometry or geometries to respective lists
     *
     * @param geos
     */
    public void addGeometry(Intersectable... geos) {
        for (Intersectable g : geos) {
            if ((g.getClass().equals(Plane.class)))
                planes.add(g);
            nonPlaneGeometries.add(g);
        }
    }

    /**
     * add light or lights to the scene
     *
     * @param light
     */
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
                ", geoList=" + nonPlaneGeometries +
                ", camera=" + camera +
                ", scrnDist=" + screenDistance +
                '}';
    }
}
