package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.*;


public class Scene {
    String sceneName;
    Color background;
    public AmbientLight ambientLight;
    Geometries geometries;
    elements.Camera camera;
    double screenDistance;

    public List<LightSource> getLights() {
        return lights;
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
        geometries.add(geos);
    }
    public void addLight(LightSource... light) {
        for(LightSource l: light)
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
