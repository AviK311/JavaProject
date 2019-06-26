import elements.*;
import geometries.*;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.Arrays;
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

    //    public static void randomSpheres() {
//        Scene scene = new Scene("Hello");
//        ImageWriter imageWriter = new ImageWriter("RandomSpheres2", 200, 200, 800, 800);
//        Render render = new Render(scene, imageWriter);
//        scene.setCamera(c);
//        scene.setScreenDistance(1000);
//        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
//
//        double planeKR = 0.3;
//        Color planeColor = new Color(100,80,0);
//        Plane right = new Plane(planeColor,
//                100, 0.3, 0.3, planeKR, 0,
//                new Point3D(0, 200, -1000),
//                new Vector(0, 1, 0));
//        Plane left = new Plane(planeColor,
//                100, 0.3, 0.3, planeKR, 0,
//                new Point3D(0, -200, -1000),
//                new Vector(0, 1, 0));
//        Plane up = new Plane(planeColor,
//                100, 0.3, 0.3, planeKR, 0,
//                new Point3D(200, 0, -1000),
//                new Vector(1, 0, 0));
//        Plane down = new Plane(planeColor,
//                100, 0.3, 0.3, planeKR, 0,
//                new Point3D(-200, 0, -1000),
//                new Vector(1, 0, 0));
//        Plane back = new Plane(planeColor,
//                100, 0.3, 0.3, planeKR, 0,
//                new Point3D(0, 0, -2000),
//                new Vector(0, 0, 1));
//
//        Random randomizer = new Random();
//        Sphere[] spheres = new Sphere[30];
//        double radius, x, y, z, r, g, b, kt, kr, ks, kd;
//        int Shininess;
//        Color color;
//        for (int i = 0; i < 30; i++) {
//
//            radius = 20 + randomizer.nextDouble() * 40;
//            x = -200 + radius + randomizer.nextDouble() * (400 - radius);
//            y = -200 + radius + randomizer.nextDouble() * (400 - radius);
//            z = -1500 + radius + randomizer.nextDouble() * (1200 - radius);
//            Point3D center = new Point3D(x, y, z);
//
//            if (randomizer.nextBoolean() && randomizer.nextBoolean() && randomizer.nextBoolean()){
//                color = Color.BLACK;
//                Shininess = 100;
//                kt = 0.5;
//                kr = 0.3;
//                ks = 0.5;
//                kd = 0.2;
//                r = randomizer.nextDouble() * 10;
//                g = randomizer.nextDouble() * 10;
//                b = randomizer.nextDouble() * 10;
//                PointLight pointLight = new PointLight(
//                        new Color(r, g, b),
//                        center,
//                        0.0001, 0.00002);
//                scene.addLight(pointLight);
//
//            }else {
//                r = randomizer.nextDouble() * 40;
//                g = randomizer.nextDouble() * 40;
//                b = randomizer.nextDouble() * 40;
//                color = new Color(r, g, b);
//                Shininess = 80 +  randomizer.nextInt(120);
//                kt = randomizer.nextDouble();
//                kr = randomizer.nextDouble();
//                ks = randomizer.nextDouble();
//                kd = randomizer.nextDouble();
//            }
//            Sphere sp = new Sphere(color, Shininess, kd, ks, kr, kt, radius, center);
//            spheres[i] = sp;
//            scene.addGeometry(sp);
////            if (true && kt>=0.7) {
////                r = randomizer.nextDouble() * 5;
////                g = randomizer.nextDouble() * 5;
////                b = randomizer.nextDouble() * 5;
////                PointLight pointLight = new PointLight(new Color(r, g, b),
////                        center,
////                        0.0001, 0.00002);
////                scene.addLight(pointLight);
////
////            }
//
//
//        }
//
////        for (int i = 0; i < 3; i++) {
////            Point3D p;
////            boolean goodSpot = false;
////            do {
////                goodSpot = true;
////                double x = -200 + randomizer.nextDouble() * 400;
////                double y = -200 + randomizer.nextDouble() * 400;
////                double z = -2000 + randomizer.nextDouble() * 1800;
////                p = new Point3D(x, y, z);
////                for (Sphere s : spheres) {
////                    if (p.distance(s.getCenter()) < s.getRadius() + 100) {
////                        goodSpot = false;
////                        break;
////                    }
////                }
////            } while (!goodSpot);
////            double r = randomizer.nextDouble() * 100;
////            double g = randomizer.nextDouble() * 100;
////            double b = randomizer.nextDouble() * 100;
////            PointLight pointLight = new PointLight(new Color(r, g, b),
////                    p,
////                    0.0001, 0.00002);
////            scene.addLight(pointLight);
////        }
//
//        PointLight pointLight = new PointLight(new Color(800, 600, 0),
//                new Point3D(0, 0, -1500),
//                0.0001, 0.00002);
////        scene.addLight(pointLight);
//
//        scene.addGeometry(up, down, right, left, back);
//
//        render.renderImage();
//        imageWriter.writeToimage();
//    }
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


    public static void main(String[] args) {
        ThreeBallsOnAPolygon();


    }
}
