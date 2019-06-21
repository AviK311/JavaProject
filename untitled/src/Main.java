import elements.*;
import geometries.Box;
import geometries.Plane;
import geometries.Sphere;
import geometries.Square;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class Main {
    static Camera c = new Camera(new Point3D(0, 0, 1000), new Vector(0, -1, 0), new Vector(0, 0, -1));

    public static void ThreeBallsOnASquare() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("three balls on a square ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Square(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.5, 0,
                new Point3D(-100, 100, -500),new Vector(1,0,-1),new Vector(0,-2,0),
                200);



        Sphere sphere = new Sphere(new Color(100, 0, 0),
                100, 0.2, 0.3, 0.5, 0.5, 50,
                new Point3D(0, 100, -500));
        Sphere sphere1 = new Sphere(new Color(0, 0, 100),
                100, 0.2, 0.3, 0.5, 0.5, 50,
                new Point3D(0, 0, -500));
        Sphere sphere2 = new Sphere(new Color(0, 100, 0),
                100, 0.2, 0.3, 0.5, 0.5, 50,
                new Point3D(0, -100, -500));
        Sphere sphere3 = new Sphere(new Color(0, 0, 100),
                100, 0.2, 0.3, 0.5, 0, 25,
                new Point3D(0, 100, -500));
        Sphere sphere4 = new Sphere(new Color(0, 100, 0),
                100, 0.2, 0.3, 0.5, 0, 25,
                new Point3D(0, 0, -500));
        Sphere sphere5 = new Sphere(new Color(100, 0, 0),
                100, 0.2, 0.3, 0.5, 0, 25,
                new Point3D(0, -100, -500));

        scene.addGeometry(plane);
        scene.addGeometry(sphere, sphere1, sphere2);
        scene.addGeometry(sphere3, sphere4, sphere5);
//       scene.addGeometry(plane, sphere1, sphere4);

        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(200, 200, 0),
                0.0001, 0.000005);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(-200, 200, 0),
                0.0001, 0.000005);

        scene.addLight(pointLight, pointLight2);

        render.renderImage();
        imageWriter.writeToimage();
    }



    public static void main(String[] args) {
        ThreeBallsOnASquare();


    }
}
