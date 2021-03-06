package renderer;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RenderTest {
    Camera c = new Camera(new Point3D(0, 0, 1000), new Vector(0, -1, 0), new Vector(0, 0, -1));

    /**
     * This test is meant to show that
     * 1. intersectables that are small will go to the son lists
     * 2. intersectables that are large will join the parent list
     */
    @Test
    public void hierarchy() {
        Scene scene = new Scene("hierarchy");
        scene.addGeometry(new Sphere(1500, new Point3D(0, 0, 0)));
        assertEquals("fail1", 1, scene.getGeometries(0).getDepth());
        scene.addGeometry(new Sphere(1200, new Point3D(0, 0, 0)));
        assertEquals("fail2", 2, scene.getGeometries(0).getDepth());
        scene.addGeometry(new Sphere(700, new Point3D(0, 0, 0)));
        assertEquals("fail3", 3, scene.getGeometries(0).getDepth());
        scene.addGeometry(new Sphere(300, new Point3D(0, 0, 0)));
        assertEquals("fail4", 4, scene.getGeometries(0).getDepth());
        scene.addGeometry(new Sphere(230, new Point3D(1000, 0, 0)));
        assertEquals("fail5", 5, scene.getGeometries(0).getDepth());
        //This sphere will become the son of level 5, then join with level 5, and because it is big enough,
        //it will join with level 4
        scene.addGeometry(new Sphere(275, new Point3D(0, 0, 0)));
        assertEquals("fail6", 4, scene.getGeometries(0).getDepth());
        scene = new Scene("hierarchy2");
        scene.addGeometry(new Sphere(300, new Point3D(0, 0, 0)));
        assertEquals("fail7", 1, scene.getGeometries(0).getDepth());
        scene.addGeometry(new Sphere(500, new Point3D(0, 0, 0)));
        assertEquals("fail8", 1, scene.getGeometries(0).getDepth());
        scene.addGeometry(new Sphere(700, new Point3D(0, 0, -0)));
        assertEquals("fail9", 1, scene.getGeometries(0).getDepth());
        scene.addGeometry(new Sphere(900, new Point3D(0, 0, -0)));
        assertEquals("fail10", 1, scene.getGeometries(0).getDepth());
    }

    @Test
    public void printGrid() {
        Scene scene = new Scene("Hello");
        scene.setCamera(c);
        ImageWriter imageWriter = new ImageWriter("printGridTest", 500, 500, 500, 500);
        Render r = new Render(scene, imageWriter);
        r.printGrid(50);
    }

    @Test
    public void basicRendering() {
        Scene scene = new Scene("Hello");
        scene.setCamera(c);
        scene.setScreenDistance(800);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.5));
        Color blue = new Color(0, 0, 255);
        Color green = new Color(0, 255, 0);
        Color red = new Color(255, 0, 0);
        Color orange = new Color(220, 100, 20);
        Color pink = new Color(200, 0, 100);
        Triangle triangle = new Triangle(red,
                1, 0.5, 0.5, 0, 0,
                new Point3D(100, 0, -50),
                new Point3D(0, 100, -50),
                new Point3D(100, 100, -50));
        Triangle triangle2 = new Triangle(green,
                1, 0.5, 0.5, 0, 0,
                new Point3D(100, 0, -50),
                new Point3D(0, -100, -50),
                new Point3D(100, -100, -50));
        Triangle triangle3 = new Triangle(orange,
                1, 0.5, 0.5, 0, 0,
                new Point3D(-100, 0, -50),
                new Point3D(0, 100, -50),
                new Point3D(-100, 100, -50));
        Triangle triangle4 = new Triangle(pink,
                1, 0.5, 0.5, 0, 0,
                new Point3D(-100, 0, -50),
                new Point3D(0, -100, -50),
                new Point3D(-100, -100, -50));
        Sphere sphere = new Sphere(blue,
                1, 0.5, 0.5, 0, 0,
                50, new Point3D(0, 0, -50));
        Plane poly = new RegularPolygon(new Color(40, 40, 40),
                100, 0, 0, 0, 0,
                new Point3D(0, 0, -1000),
                new Vector(2, 0, 1),
                500, 10);
        scene.addGeometry(triangle, triangle2, triangle3, triangle4, sphere);
//        scene.addGeometry(poly);
        ImageWriter imageWriter = new ImageWriter("4 triangles and sphere", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);
        render.renderImage();
        render.printGrid(50);
        imageWriter.writeToimage();
    }

    @Test
    public void HolyBallandTriangles() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("HolyBall and 3 triangles", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);
        Camera c1 = new Camera(new Point3D(0, 0, 0),
                new Vector(0, -1, 0),
                new Vector(0, 0, -100));
        scene.setCamera(c1);
        //scene.getCamera().setP0(new Point3D(0,0,0));
        scene.setScreenDistance(150);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        Sphere sphere = new HolyBall(new Color(0, 0, 100),
                170, 0.5, 0.5, 0.5, 0.1, 80,
                new Vector(1, 1, 0.75),
                new Vector(0.5, -2, 2),
                new Point3D(0, 0, -250),
                40);
//        Sphere sphere = new Sphere(new Color(0, 0, 100),
//                170, 0.5, 0.5, 0.5, 0.1, 80,
//                new Point3D(0, 0, -250));
        scene.addGeometry(sphere);
        Triangle triangle = new Triangle(new Color(0, 50, 0),
                100, 0.2, 0.5, 0.2, 0.2,
                new Point3D(0, 0, -450), //green triangle
                new Point3D(-2000, 0, -500),
                new Point3D(0, -4000, -500));
        scene.addGeometry(triangle);
        Triangle triangle1 = new Triangle(new Color(80, 0, 0),
                100, 0.1, 0.3, 0.5, 0.2,
                new Point3D(100, 100, -100), //red triangle
                new Point3D(90, 200, -90),
                new Point3D(-50, 100, -100));
        scene.addGeometry(triangle1);
        Triangle triangle2 = new Triangle(new Color(33, 33, 33),
                100, 0.2, 0.5, 0.2, 0,
                new Point3D(-2000, -2000, -2000), //gray triangle
                new Point3D(-2000, 500, -2000),
                new Point3D(1500, 800, -800));
        scene.addGeometry(triangle2);
        scene.addLight(new SpotLight(new Color(800, 80, 0), new Point3D(150, 150, -50),
                0.000001, 0.0000005, new Point3D(0, 0, -100).subtract(new Point3D(50, 0, 0))));
        // I added this light
        scene.addLight(new SpotLight(new Color(100, 1000, 0), new Point3D(-1000, -600, -50),
                0.000001, 0.0000005, new Point3D(0, 0, -100).subtract(new Point3D(50, 0, 0))));
        Vector V = new Vector(new Point3D(-0.2, -0.6, -1)).normalize();
        scene.addLight(new SpotLight(new Color(220, 230, 1000), new Point3D(0, 0, -350),
                0.00001, 0.00005, V));
        render.renderImage();
        //imageWriter.writeToimage();
        render.getImageWriter().writeToimage();
    }

    @Test
    public void Ball() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("Ball ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(0, 0, -200), new Vector(1, 0, 1));
        Sphere sphere = new Sphere(new Color(0, 0, 100),
                100, 0.2, 0.3, 0.1, 0, 50,
                new Point3D(0, 0, -100));
        scene.addGeometry(plane, sphere);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 200, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void ThreeBallsOnASquare() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("three balls on a square ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Square(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.5, 0,
                new Point3D(-100, 100, -500), new Vector(1, 0, -1), new Vector(0, -2, 0),
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

    @Test
    public void ThreeBallsOnACircle() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("three balls on a circle", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Circle(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.9, 0,
                new Point3D(-50, 0, -500), new Vector(1, 0, 1).scale(-1),
                150);
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
//        scene.addGeometry(sphere1);
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

    @Test
    public void ThreeBallsOnAPolygon() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("three balls on a polygon", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new RegularPolygon(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.9, 0,
                new Point3D(0, 0, -600), new Vector(1, 0, 1).scale(-1),
                150, 15);
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
//        scene.addGeometry(sphere, sphere1, sphere2);
        scene.addGeometry(sphere3, sphere4, sphere5);
//        scene.addGeometry(sphere1);
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

    @Test
    public void TrafficLight() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("TrafficLight ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(700);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0,
                new Point3D(0, -600, -600), new Vector(1, 0, 1));
        Sphere sphere = new Sphere(new Color(100, 0, 0),
                300, 0.2, 0.3, 0, 0, 25,
                new Point3D(100, 0, -500));
        Sphere sphere1 = new Sphere(new Color(100, 50, 10),
                100, 0.2, 0.3, 0, 0, 25,
                new Point3D(50, 0, -500));
        Sphere sphere2 = new Sphere(new Color(0, 100, 0),
                100, 0.2, 0.3, 0, 0, 25,
                new Point3D(0, 0, -500));
        Triangle triangle1 = new Triangle(Color.BLACK, 10, 0, 0.1, 0.2, 0, new Point3D(140, -35, -500), new Point3D(140, 35, -500), new Point3D(-50, -35, -500));
        Triangle triangle2 = new Triangle(Color.BLACK, 10, 0, 0.1, 0.2, 0, new Point3D(-50, 35, -500), new Point3D(140, 35, -500), new Point3D(-50, -35, -500));
        Triangle triangle3 = new Triangle(Color.BLACK, 10, 0, 0.1, 0.2, 0, new Point3D(-30, -7, -500), new Point3D(-30, 7, -500), new Point3D(-7000, -7, -500));
        Triangle triangle4 = new Triangle(Color.BLACK, 10, 0, 0.1, 0.2, 0, new Point3D(-7000, 7, -500), new Point3D(-30, 7, -500), new Point3D(-7000, -7, -500));
        Pyramid pyramid = new Pyramid(Color.BLACK, 100, 0.2, 0.5, 0, 0,
                new Point3D(0, 0, -500), new Point3D(0, 200, -700),
                new Point3D(0, -200, -700),
                new Point3D(100, 0, -500));
//        scene.addGeometry(pyramid);
        scene.addGeometry(plane, sphere, sphere1, sphere2, triangle1, triangle2, triangle3, triangle4);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(200, 200, 0),
                0.0001, 0.000005);
        scene.addLight(pointLight);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void moon() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("picturemoon ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(500);
        scene.setAmbientLight(new AmbientLight(new Color(10, 10, 10), 0.1));
        Plane plane = new Plane(new Color(10, 20, 50),
                100, 0.4, 0.1, 0.4, 0,
                new Point3D(-100, -100, 0), new Vector(1, 0, 0));
        Sphere sphere = new Sphere(new Color(10, 10, 10),
                100, 0, 0.01, 0.2, 0, 50,
                new Point3D(140, -180, -500));
        Sphere sphere1 = new Sphere(new Color(0, 0, 100),
                100, 0.2, 0.3, 0.2, 0, 50,
                new Point3D(0, 0, -500));
        Sphere sphere2 = new Sphere(new Color(0, 100, 0),
                100, 0.2, 0.3, 0.2, 0, 50,
                new Point3D(0, -100, -500));
        Pyramid pyramid = new Pyramid(Color.BLACK, 100, 0.2, 0.5, 0, 0,
                new Point3D(0, 0, -500), new Point3D(0, 200, -700),
                new Point3D(0, -200, -700),
                new Point3D(100, 0, -500));
//        scene.addGeometry(pyramid);
        scene.addGeometry(plane, sphere);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(190, 200, -500),
                0.0001, 0.000005);
        //scene.addLight(pointLight);
        scene.addLight(new SpotLight(new Color(150, 220, 150), new Point3D(170, 180, -500),
                0.0001, 0.000005, new Point3D(160, -180, -500).subtract(new Point3D(170, 180, -500))));
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void NestedBalls() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("3NestedBalls ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0,
                new Point3D(-50, 0, -500), new Vector(1, 0, 1));
        Sphere sphere3 = new Sphere(new Color(100, 0, 0),
                100, 0.2, 0.3, 0.9, 0.3, 30,
                new Point3D(0, 0, -500));
        Sphere sphere1 = new Sphere(new Color(0, 0, 100),
                100, 0.2, 0.3, 0.7, 0.3, 100,
                new Point3D(0, 0, -500));
        Sphere sphere2 = new Sphere(new Color(0, 100, 0),
                100, 0.2, 0.3, 0.5, 0.4, 60,
                new Point3D(0, 0, -500));
        scene.addGeometry(plane, sphere1, sphere2, sphere3);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(200, 200, 0),
                0.0001, 0.000005);
        scene.addLight(pointLight);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void triangles_ball() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("triangles_ball ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(800);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0,
                new Point3D(0, 0, 0), new Vector(125, 20, 7));
        Triangle triangle1 = new Triangle(new Color(100, 40, 0), 10, 0, 0.6, 0.2, 0.5, new Point3D(40, 0, -200), new Point3D(0, 30, 600), new Point3D(0, -40, 400));
        Triangle triangle2 = new Triangle(new Color(0, 100, 0), 10, 0, 0.1, 0.2, 0, new Point3D(40, 0, -200), new Point3D(-15, 30, 600), new Point3D(15, -40, 400));
        Triangle triangle3 = new Triangle(new Color(20, 100, 80), 10, 0, 0.6, 0.4, 0, new Point3D(40, 0, -200), new Point3D(-30, 30, 600), new Point3D(30, -40, 400));
        Triangle triangle4 = new Triangle(new Color(0, 0, 100), 10, 0.2, 0.1, 0.2, 0.6, new Point3D(40, 0, -200), new Point3D(-45, 30, 600), new Point3D(45, -40, 400));
        Sphere sphere1 = new Sphere(new Color(0, 0, 100),
                100, 0.2, 0.3, 0.2, 0, 20,
                new Point3D(10, -60, 400));
        scene.addGeometry(plane, triangle1, triangle2, triangle3, triangle4, sphere1);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(200, 100, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void box() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("box ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(800);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0,
                new Point3D(0, 0, 0), new Vector(125, 20, 7));
        Plane plane1 = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0,
                new Point3D(0, 0, -300), new Vector(0, 1, 4));
        Sphere sphere1 = new Sphere(new Color(0, 0, 100),
                100, 0, 1, 0, 0, 20,
                new Point3D(50, 0, 0));
        Box box = new Box(new Color(70, 0, 20),
                100, 0.01, 0.01, 0.7, 1,
                new Point3D(0, 50, 0),
                new Vector(-0.1, 0, 1),
                new Vector(0, -1, 0), 100);
//        Square box = new Square(new Color(70, 0, 20),
//                100, 0.5, 0.5, 1, 0,
//                new Point3D(0, 50, 50) ,
//                new Vector(1,0,0),
//                new Vector(0,-1,0), 100);
        scene.addGeometry(plane, box, sphere1);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(90, -100, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(200, 100, 200), new Vector(-1, 1, -1));
        //scene.addLight(light1);
        SpotLight light3 = new SpotLight(new Color(200, 100, 20), new Point3D(200, -200, 200), 0.5, 0.9, new Vector(new Point3D(-1, 1, -1)));
        scene.getLights().add(light3);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(80, -80, 200),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void pyramid() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("pyramid ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(-10, -40, -100), new Vector(10, 0, 1));
        Pyramid pyramid = new Pyramid(new Color(30, 70, 80),
                100, 0.2, 0.3, 0.4, 0.2, new Point3D(0, -10, 200),
                new Point3D(-10, -40, -100), new Point3D(-10, 60, -100), new Point3D(60, 10, 0));
        Sphere sphere = new Sphere(new Color(0, 0, 100),
                100, 0.2, 0.3, 0.1, 0, 10,
                new Point3D(10, 10, 150));
        scene.addGeometry(plane, pyramid, sphere);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(10, -60, -100),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 1, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(50, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void shadowTest() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("shadowBalls ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.9, 0,
                new Point3D(-50, 0, -500), new Vector(1, 0, 1));
        Sphere sphere = new Sphere(new Color(100, 0, 0),
                100, 0.3, 0.8, 0.1, 0.1, 50,
                new Point3D(0, 100, -500));
        Sphere sphere2 = new Sphere(new Color(0, 100, 0),
                100, 0.3, 0.8, 0.1, 0.1, 50,
                new Point3D(0, -100, -500));
        scene.addGeometry(plane, sphere, sphere2);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 0, -500),
                0.0001, 0.000005);
        scene.addLight(pointLight);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void word() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("word ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(700);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(-50, 0, 0), new Vector(1, 0.2, 0.2));
        Triangle triangle1 = new Triangle(new Color(100, 100, 50), 10, 0.2, 0.3, 0.2, 0.5, new Point3D(50, -60, 0), new Point3D(-30, -90, 0), new Point3D(-30, -30, 0));
        Triangle triangle2 = new Triangle(new Color(40, 40, 40).scale(2), 10, 0.2, 0.3, 0.2, 0, new Point3D(20, -60, 1), new Point3D(-30, -80, 1), new Point3D(-30, -40, 1));
        Triangle triangle3 = new Triangle(new Color(100, 100, 50).scale(2), 10, 0.2, 0.3, 0.2, 0, new Point3D(20, -60, 2), new Point3D(-20, -76, 2), new Point3D(-20, -44, 2));
        Triangle triangle4 = new Triangle(new Color(40, 40, 40), 10, 0.2, 0.3, 0.2, 0.5, new Point3D(20, -60, 3), new Point3D(-10, -72, 3), new Point3D(-10, -48, 3));
        Triangle triangle5 = new Triangle(new Color(100, 100, 50), 10, 0.2, 0.3, 0.2, 0.5, new Point3D(50, -40, 0), new Point3D(-30, -10, 0), new Point3D(50, 20, 0));
        Triangle triangle6 = new Triangle(new Color(40, 40, 40), 10, 0.2, 0.3, 0.2, 0.5, new Point3D(50, -30, 1), new Point3D(0, -10, 1), new Point3D(50, 10, 1));
        Triangle triangle7 = new Triangle(new Color(100, 100, 50), 10, 0.2, 0.3, 0.2, 0.5, new Point3D(-30, 50, 0), new Point3D(-30, 30, 0), new Point3D(35, 30, 0));
        Triangle triangle8 = new Triangle(new Color(100, 100, 50), 10, 0.2, 0.3, 0.2, 0.5, new Point3D(35, 50, 0), new Point3D(-30, 50, 0), new Point3D(35, 30, 0));
        Sphere sphere = new Sphere(new Color(70, 80, 50).scale(2),
                100, 0.2, 0.4, 0.3, 0.2, 11,
                new Point3D(50, 40, 0));
        scene.addGeometry(plane, triangle1, triangle2, triangle3, triangle4, triangle5, triangle6, triangle7, triangle8, sphere);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 200, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void pyramid2() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("pyramid2 ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(-50, 0, 0), new Vector(1, 0.2, 0.2));
        Pyramid pyramid = new Pyramid(new Color(30, 70, 80),
                100, 0.2, 0.3, 0.4, 0.2, new Point3D(0, -70, 0),
                new Point3D(0, 0, 30), new Point3D(30, -80, -100), new Point3D(30, 40, -100), new Point3D(60, -45, -50));
        Sphere sphere = new Sphere(new Color(70, 80, 50).scale(2),
                100, 0.2, 0.4, 0.3, 0.2, 11,
                new Point3D(50, 40, 0));
        scene.addGeometry(plane, sphere, pyramid);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 200, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        PointLight pointLight3 = new PointLight(new Color(800, 600, 0),
                new Point3D(60, -90, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight3);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void HolyBallTest() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("Holy Ball and more ", 200, 200, 750, 750);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Circle(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(0, 0, -220), new Vector(1, 0, 1), 1000);
        HalfSphere halfSphere = new HalfSphere(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                , new Vector(-1, -1, -0.5).scale(-1), new Point3D(0, 0, -100), 70);
        HalfSphere halfSphere2 = new HalfSphere(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                , new Vector(-1, -1, -0.5), new Point3D(0, 0, -100), 80);
        Halo halo = new Halo(new Color(100, 0, 0),
                100, 0.2, 0.3, 0.7, 0.2, 60,
                new Vector(1, -1, -0.5).scale(-1), new Point3D(0, 0, -100), 70);
        HolyBall holyball = new HolyBall(new Color(50, 0, 100),
                100, 0.2, 0.3, 0.5, 0.2, 80,
                new Vector(1, 1, 0.75),
                new Vector(0.5, -2, 2), new Point3D(0, 0, -100), 50);
        Sphere sphere2 = new Sphere(new Color(0, 0, 50),
                100, 0.2, 0.3, 0.1, 0.3, 30,
                new Point3D(-20, 0, -100));
        SphereTriangle st = new SphereTriangle(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                , new Vector(-1, 1, 1), new Point3D(0, 0, -100), 80);
        SphereTriangle st2 = new SphereTriangle(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                , new Vector(-1, 1, 1), new Point3D(0, 50, -100), 80);
        //scene.addGeometry(plane, halfSphere, halo, sphere2);
        scene.addGeometry(plane, holyball, halfSphere, halfSphere2, halo, sphere2);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 200, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void myTest3() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("picture8 ", 200, 200, 750, 750);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(0, 0, -220), new Vector(1, 0, 1));
//        Plane2 plane2 = new Plane2(new Color(40, 40, 40),
//                100, 0.4, 0.1, 0.4, 0.1,
//                new Point3D(0, 0, -100), new Vector(1, 0, 1));
        Plane2 plane2 = new Plane2(new Color(0, 60, 0),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(0, 0, -100), new Vector(1, 1, -1));
        scene.addGeometry(plane2);
        //scene.addGeometry(plane,holyball);
        //scene.addGeometry(plane,st, st2);
//       scene.addGeometry(plane,holyball);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 200, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void BallInHalfball() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("Ball in halfball ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(0, 0, -220), new Vector(1, 0, 1));
        HalfSphere halfSphere = new HalfSphere(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                , new Vector(-1, 1, 0.5), new Point3D(0, 0, -100), 30);
        HalfSphere halfSphere2 = new HalfSphere(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                , new Vector(-1, -1, -0.5), new Point3D(0, 0, -100), 80);
        Halo halo = new Halo(new Color(100, 0, 0),
                100, 0.2, 0.3, 0.7, 0.2, 60,
                new Vector(1, -1, -0.5).scale(-1), new Point3D(0, 0, -100), 70);
        HolyBall holyball = new HolyBall(new Color(50, 0, 100),
                100, 0.2, 0.3, 0.5, 0.2, 80,
                new Vector(1, 1, 0.75),
                new Vector(0.5, -2, 2), new Point3D(0, 0, -100), 50);
        Sphere sphere2 = new Sphere(new Color(0, 0, 50),
                100, 0.2, 0.3, 0.1, 0.3, 30,
                new Point3D(-20, 0, -100));
        SphereTriangle st = new SphereTriangle(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                , new Vector(-1, 1, 1), new Point3D(0, 0, -100), 80);
        SphereTriangle st2 = new SphereTriangle(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                , new Vector(-1, 1, 1), new Point3D(0, 50, -100), 80);
        scene.addGeometry(plane, halfSphere, sphere2);
        //scene.addGeometry(plane,holyball);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 200, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void squareCircleTest() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("Square.Circle", 200, 200, 750, 750);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
//        Square plane = new Square(new Color(50, 50, 50),
//                100, 0, 0, 1, 0,
//                new Point3D(0, 0, -100),
//                new Vector(1, 0, 0),
//                new Vector(0,-1,-1),50);
        Plane plane = new Square(new Color(10, 10, 10),
                100, 0.3, 0.3, 0.5, 0.3,
                new Point3D(0, 0, -150),
                new Vector(1, 0, 0),
                new Vector(0, -1, 1)
                , 50);
        Square plane4 = new Square(new Color(10, 10, 10),
                100, 0, 0, 1, 0,
                new Point3D(0, 0, -100).add(new Vector(50, 0, 0)),
                new Vector(0, 1, 1),
                new Vector(0, 1, -1), 50);
        Plane plane3 = new Plane(new Color(10, 10, 10),
                100, 0.3, 0.3, 0.7, 0,
                new Point3D(0, 0, -150),
                new Vector(1, 0, 0).crossProduct(
                        new Vector(0, -1, -1)).scale(-1));
        HolyBall holyball = new HolyBall(new Color(50, 0, 100),
                100, 0.2, 0.3, 0.5, 0.2, 30,
                new Vector(1, 1, 0.75),
                new Vector(0.5, -2, 2), new Point3D(-10, 0, -100), 15);
        Plane plane2 = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0,
                new Point3D(-5, 0, 0), new Vector(1, 0, 0.1));
        scene.addGeometry(plane, plane2, plane3, holyball, plane4);
//       scene.addGeometry(plane,holyball);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 0, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 3, -1));
        scene.addLight(light1);
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }
//    @Test
//    public void PolygonTest() {
//        Scene scene = new Scene("Hello");
//        ImageWriter imageWriter = new ImageWriter("Square.Circle", 200, 200, 750, 750);
//        Render render = new Render(scene, imageWriter);
//        scene.setCamera(c);
//        scene.setScreenDistance(1000);
//        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
//
////        Square plane = new Square(new Color(50, 50, 50),
////                100, 0, 0, 1, 0,
////                new Point3D(0, 0, -100),
////                new Vector(1, 0, 0),
////                new Vector(0,-1,-1),50);
//        Plane plane = new Square(new Color(10, 10, 10),
//                100, 0.3, 0.3, 0.5, 0.3,
//                new Point3D(0, 0, -150),
//                new Vector(1, 0, 0),
//                new Vector(0,-1,1)
//                ,50);
//        Square plane4 = new Square(new Color(10, 10, 10),
//                100, 0, 0, 1, 0,
//                new Point3D(0, 0, -100).add(new Vector(50,0,0)),
//                new Vector(0,1,1),
//                new Vector(0,1,-1),50);
//        Plane plane3 = new Plane(new Color(10, 10, 10),
//                100, 0.3, 0.3, 0.7, 0,
//                new Point3D(0, 0, -150),
//                new Vector(1, 0, 0).crossProduct(
//                        new Vector(0,-1,-1)).scale(-1));
//        HolyBall holyball = new HolyBall(new Color(50, 0, 100),
//                100, 0.2, 0.3, 0.5, 0.2, 30,
//                new Vector(1,1,0.75),
//                new Vector(0.5,-2,2),new Point3D(-10, 0, -100), 15);
//        Plane plane2 = new Plane(new Color(40, 40, 40),
//                100, 0.4, 0.1, 0.4, 0,
//                new Point3D(-5, 0, 0), new Vector(1, 0, 0.1));
//
//
//
//        scene.addGeometry(plane,plane2, plane3, holyball, plane4);
////       scene.addGeometry(plane,holyball);
//
//        PointLight pointLight = new PointLight(new Color(800, 600, 0),
//                new Point3D(0, 0, 0),
//                0.0001, 0.00002);
//
//        scene.addLight(pointLight);
//        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 3, -1));
//        scene.addLight(light1);
//
//        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
//                new Point3D(10, 230, 0),
//                0.0001, 0.00002);
//        scene.addLight(pointLight2);
//
//
//        render.renderImage();
//        imageWriter.writeToimage();
//    }

    @Test
    public void randomSpheres() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("RandomSpheres", 200, 200, 750, 750);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(10, 10, 10),
                100, 0.3, 0.3, 0.5, 0.3,
                new Point3D(-80, 0, -500),
                new Vector(1, 0, 1));
        Random randomizer = new Random();
        for (int i = 0; i < 20; i++) {
            double radius = randomizer.nextDouble() * 100;
            double x = -200 + radius + randomizer.nextDouble() * (400 - radius);
            double y = -200 + radius + randomizer.nextDouble() * (400 - radius);
            double z = -1500 + radius + randomizer.nextDouble() * (1200 - radius);
            Point3D center = new Point3D(x, y, z);
            double r = randomizer.nextDouble() * 255;
            double g = randomizer.nextDouble() * 255;
            double b = randomizer.nextDouble() * 255;
            Color color = new Color(r, g, b);
            int Shininess = randomizer.nextInt(200);
            double kt = randomizer.nextDouble(), kr = randomizer.nextDouble(),
                    ks = randomizer.nextDouble(), kd = randomizer.nextDouble();
            Sphere sp = new Sphere(color, Shininess, kd, ks, kr, kt, radius, center);
            scene.addGeometry(sp);
            if (randomizer.nextBoolean()) {
                r = randomizer.nextDouble() * 400;
                g = randomizer.nextDouble() * 400;
                b = randomizer.nextDouble() * 400;
                PointLight pointLight = new PointLight(new Color(r, g, b),
                        center,
                        0.0001, 0.00002);
                scene.addLight(pointLight);
            }
        }
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0, 250, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        scene.addGeometry(plane);
        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void test_parabulas_with_quadrangles_wall() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("wall", 200, 200, 750, 750);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(400);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        int dis = 0;
        int magnify = 0;
        Color color;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((j + i) % 2 == 0) {
                    magnify = 0;
                    dis = 0;
                    color = new Color(100, 30, 70);
                } else {
                    magnify = 5;
                    dis = 100;
                    color = new Color(70, 30, 100);
                }
                Point3D p1 = new Point3D(-200 + j * 400 + magnify, -520 + i * 400 + magnify, -5200 - dis);
                Vector v1 = p1.subtract(new Point3D(200 + j * 400 + magnify, -520 + i * 400 + magnify, -5200 - dis));
                Vector v2 = p1.subtract(new Point3D(-200 + j * 400 + magnify, -120 + i * 400 + magnify, -5200 - dis));
                double length = v2.length();
                Plane quadrangle = new Square(new Color(10 + i * 15, 20 + j * 15, 30), 20, 0.5, 0.5, 0.5, 0.5, p1, v1, v2, length);
                //new Point3D(-1430 + j * 400 + magnify, -460 + i * 400 + magnify, -5200 - dis), new Point3D(-1030 + j * 400 + magnify, -460 + i * 400 + magnify, -5200 - dis), new Point3D(-1030 + j * 400 + magnify, -60 + i * 400 + magnify, -5200 - dis), new Point3D(-1430 + j * 400 + magnify, -60 + i * 400 + magnify, -5200 - dis));
                scene.addGeometry(quadrangle);
            }
        }
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 7; j++) {
//                if ((j + i) % 2 == 0) {
//                    magnify = 0;
//                    dis = 0;
//                   // color = new Color(100,30,70);
//                } else {
//                    magnify = 5;
//                    dis = 100;
//                   // color = new Color(70,30,100);
//                }
//
//                Triangle triangle = new Triangle(new Color(10+i*15,20+j*15,30),20,0.5,0.5,0.5,0.5,new Point3D(-1430 + j * 400 + magnify, -460 + i * 400 + magnify, -5200 - dis), new Point3D(-1030 + j * 400 + magnify, -460 + i * 400 + magnify, -5200 - dis), new Point3D(-1030 + j * 400 + magnify, -60 + i * 400 + magnify, -5200 - dis));
//                //Triangle triangle2 = new Triangle(new Color(10+j*15,20+i*15,30),20,0.5,0.5,0.5,0.5,new Point3D(-1430 + j * 400 + magnify, -60 + i * 400 + magnify, -5200 - dis), new Point3D(-1430 + j * 400 + magnify, -460 + i * 400 + magnify, -5200 - dis), new Point3D(-1030 + j * 400 + magnify, -60 + i * 400 + magnify, -5200 - dis));
//                //new Point3D(-1430 + j * 400 + magnify, -460 + i * 400 + magnify, -5200 - dis), new Point3D(-1030 + j * 400 + magnify, -460 + i * 400 + magnify, -5200 - dis), new Point3D(-1030 + j * 400 + magnify, -60 + i * 400 + magnify, -5200 - dis), new Point3D(-1430 + j * 400 + magnify, -60 + i * 400 + magnify, -5200 - dis));
//                scene.addGeometry(triangle);
//            }
//        }
        int distance = -2000;
        int height = 120;
        for (int i = -24; i < 24; i = i + 2) {
            distance -= 500;
            height -= 75;
            if (Math.abs(i) > 12) {
                for (int j = i; j < -1 * (i + 1); j++) {
                    Sphere sphere = new Sphere(new Color(15, 20, 50), 20, 0.5, 0.5, 0.5, 0.5, 65, new Point3D(height - (Math.pow(j, 2)) + 200, -34 + 25 * j, distance));
                    scene.addGeometry(sphere);
                }
            }
        }
//
//
//        scene.addLight(new SpotLight(new Color(222, 222, 222), new Point3D(0, 1600, -2600), new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));
//
//
//
//
        Plane plane = new Plane(new Color(31, 31, 31), 15, 0.5, 0, 0.65, 0.5, new Point3D(-270, -270, 0), new Vector(1, 0, 0));
        scene.addGeometry(plane);
//
//
//        scene.addLight(new SpotLight(new Color(222, 222, 222), new Point3D(0, 1600, -2600), new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }
}