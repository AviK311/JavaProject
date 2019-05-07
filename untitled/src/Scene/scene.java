package Scene;
import elements.AmbientLight;
import elements.camera;
import geometries.GeometryList;
import geometries.Geometry;
import geometries.IIntersectable;
import primitives.Color;
import java.awt.*;
import java.util.*;

public class scene {
    String sceneName;
    Color background;
    AmbientLight ambientLight;
    GeometryList geometries;
    camera Camera;
    double screenDistance;

    public scene(){
        this.sceneName = "New Scene";
        this.background = new Color(255,255,255);
        this.ambientLight = new AmbientLight();
        this.geometries = new GeometryList();
        this.Camera = new camera();
        this.screenDistance = 50;
    }

    public scene(String sceneName, Color background, AmbientLight ambientLight, camera Camera, double screenDistance) {
        this.sceneName = sceneName;
        this.background = background;
        this.ambientLight = ambientLight;
        this.geometries = new GeometryList();
        this.Camera = Camera;
        this.screenDistance = screenDistance;
    }

    public scene(scene other) {
        this.sceneName = other.getSceneName() ;
        this.background = other.getBackground();
        this.ambientLight = other.getAmbientLight();
        this.geometries = other.getGeometries();
        this.Camera = other.getCamera();
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

    public camera getCamera() {
        return Camera;
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
                ", camera=" + Camera +
                ", scrnDist=" + screenDistance +
                '}';
    }


    public void addGeometry(Geometry geometry){
        geometries.add(geometry);
    }
    public Iterator<IIntersectable> getGeoIterator(){
        return geometries.getIterator();
    }
}
