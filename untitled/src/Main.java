import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.Random;

public class Main {
    static Camera c = new Camera(new Point3D(0, 0, 1000), new Vector(0, -1, 0), new Vector(0, 0, -1));

    public static void ThreeBallsOnAPolygon() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("three balls on a polygon", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new RegularPolygon(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.9, 0,
                new Point3D(0, 0, -600), new Vector(2, 0, 1).scale(-1),
                150, 8);
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
//        scene.addGeometry(sphere3, sphere4, sphere5);
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

    public static void randomSpheres() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("RandomSpheres7", 200, 200, 800, 800);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.3, 0.3, 0.5, 0.3,
                new Point3D(-80, 0, -500),
                new Vector(0.5, 0, 1));
        Random randomizer = new Random();
        for (int i = 0; i < 30; i++) {
            double radius = randomizer.nextDouble() * 80;
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
            int type = randomizer.nextInt(4);
            Sphere sp = null;
            double vX, vY, vZ, distance;
            switch (type) {
                case 1:
                    vX = 0.001 + randomizer.nextDouble();
                    vY = 0.001 + randomizer.nextDouble();
                    vZ = 0.001 + randomizer.nextDouble();
                    distance = 2 * radius * randomizer.nextDouble();
                    sp = new HalfSphere(color, Shininess, kd, ks, kr, kt, radius, new Vector(vX, vY, vZ), center, distance);
                    break;
                case 2:
                    vX = 0.001 + randomizer.nextDouble();
                    vY = 0.001 + randomizer.nextDouble();
                    vZ = 0.001 + randomizer.nextDouble();
                    distance = Math.sqrt(radius * radius * 2) * randomizer.nextDouble();
                    sp = new Halo(color, Shininess, kd, ks, kr, kt, radius, new Vector(vX, vY, vZ), center, distance);
                    break;
                case 3:
                    vX = 0.001 + randomizer.nextDouble();
                    vY = 0.001 + randomizer.nextDouble();
                    vZ = 0.001 + randomizer.nextDouble();
                    double A = radius / Math.sqrt(2);
                    double B = radius - A;
                    distance = Math.sqrt(A * A + B * B) * randomizer.nextDouble();
                    double vZ2 = -(vX + vY) / vZ;
                    System.out.println(vX + vY + vZ * vZ2);
                    sp = new HolyBall(color, Shininess, kd, ks, kr, kt, radius, new Vector(vX, vY, vZ), new Vector(1, 1, vZ2), center, distance);
                    break;
                case 0:
                default:
                    sp = new Sphere(color, Shininess, kd, ks, kr, kt, radius, center);
                    break;
            }
            scene.addGeometry(sp);
            if (randomizer.nextBoolean() && randomizer.nextBoolean()) {
                r = randomizer.nextDouble() * 100;
                g = randomizer.nextDouble() * 100;
                b = randomizer.nextDouble() * 100;
                PointLight pointLight = new PointLight(new Color(r, g, b),
                        center,
                        0.0001, 0.00002);
                scene.addLight(pointLight);
            }
        }
        PointLight pointLight = new PointLight(new Color(200, 100, 0),
                new Point3D(0, 250, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight);
        scene.addGeometry(plane);
        render.renderImage();
        imageWriter.writeToimage();
    }

    public static void ThreeBallsOnASquare() {
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

    public static void test_parabulas_with_quadrangles_wall() {
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
//                Vector v1=p1.subtract(new Point3D(200 + j * 400 + magnify, -520 + i * 400 + magnify, -5200 - dis));
//                Vector v2=p1.subtract(new Point3D(-200 + j * 400 + magnify, -120 + i * 400 + magnify, -5200 - dis));
                Vector v1 = new Vector(-1, 0, 0);
                Vector v2 = new Vector(0, -1, 0);
                double length = 400;
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
                    Sphere sphere = new Sphere(new Color(24 + i * 5, 24 + i * 5, 30), 20, 0.5, 0.5, 0.5, 0.5, 65, new Point3D(height - (Math.pow(j, 2)) + 200, -34 + 25 * j, distance));
                    scene.addGeometry(sphere);
                }
            }
        }
//
//
        scene.addLight(new SpotLight(new Color(222, 222, 222), new Point3D(0, 1600, -2600), 0.000001, 0.0000005, new Vector(-2, -2, -3)));
//
//
//
//
        Plane plane = new Plane(new Color(31, 31, 31), 15, 0.5, 0, 0.65, 0.5, new Point3D(-270, -270, 0), new Vector(1, 0, 0));
        scene.addGeometry(plane);
//
//
        scene.addLight(new SpotLight(new Color(222, 222, 222), new Point3D(0, 1600, -2600), 0.000001, 0.0000005, new Vector(-2, -2, -3)));
        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10, 230, 0),
                0.0001, 0.00002);
        scene.addLight(pointLight2);
        render.renderImage();
        imageWriter.writeToimage();
    }

    public static void main(String[] args) {
        test_parabulas_with_quadrangles_wall();
    }
}
