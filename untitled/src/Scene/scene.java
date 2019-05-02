package Scene;
import elements.AmbientLight;
import geometries.GeometryList;
import geometries.Geometry;
import geometries.IIntersectible;
import javafx.scene.Camera;

import java.awt.*;
import java.util.*;

public class scene {
    String sceneName;
    Color background;
    AmbientLight ambientLight;
    GeometryList geometries;
    Camera camera;
    double screenDistance;

    public scene(String sceneName, Color background, AmbientLight ambientLight, Camera camera, double screenDistance) {
        this.sceneName = sceneName;
        this.background = background;
        this.ambientLight = ambientLight;
        this.geometries = new GeometryList();
        this.camera = camera;
        this.screenDistance = screenDistance;
    }

    public scene(scene other) {
        this.sceneName = other.getSceneName() ;
        this.background = other.getBackground();
        this.ambientLight = other.getAmbientLight();
        this.geometries = other.getGeometries();
        this.camera = other.getCamera();
        this.screenDistance = other.getScreenDistance();
    }

    public String getSceneName() {
        return sceneName;
    }

//    public void setSceneName(String sceneName) {
//        this.sceneName = sceneName;
//    }

    public Color getBackground() {
        return background;
    }

//    public void setBackground(Color background) {
//        this.background = background;
//    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    public GeometryList getGeometries() {
        return geometries;
    }

//    public void setGeometries(List<Geometry> geometries) {
//        this.geometries = geometries;
//    }

    public Camera getCamera() {
        return camera;
    }

//    public void setCamera(Camera camera) {
//        this.camera = camera;
//    }

    public double getScreenDistance() {
        return screenDistance;
    }

//    public void setScreenDistance(double screenDistance) {
//        this.screenDistance = screenDistance;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof scene)) return false;
        scene scene = (Scene.scene) o;
        return Double.compare(scene.getScreenDistance(), getScreenDistance()) == 0 &&
                getSceneName().equals(scene.getSceneName()) &&
                getBackground().equals(scene.getBackground()) &&
                getAmbientLight().equals(scene.getAmbientLight()) &&
                getGeometries().equals(scene.getGeometries()) &&
                getCamera().equals(scene.getCamera());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSceneName(), getBackground(), getAmbientLight(), getGeometries(), getCamera(), getScreenDistance());
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


    public void addGeometry(Geometry geometry){
        geometries.add(geometry);
    }
    public Iterator<IIntersectible> getGeoIterator(){
        return geometries.getIterator();
    }
}
