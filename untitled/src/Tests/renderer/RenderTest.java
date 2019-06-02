package renderer;

import elements.*;
import primitives.*;
import scene.Scene;
import geometries.*;
import org.junit.Test;

public class RenderTest {
    Camera c = new Camera(new Point3D(0,0,1000), new Vector(0, -1, 0), new Vector(0, 0, -1));

    @Test
    public void printGrid() {
        Scene scene = new Scene("Hello");
        scene.setCamera(c);
        ImageWriter imageWriter = new ImageWriter("printGridTest", 500, 500, 500, 500);

        Render r = new Render(scene, imageWriter);
        r.printGrid(50);
    }

    @Test
    public void p() {
        Sphere sphere = new Sphere(50, new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-150)));
        Point3D P1 = new Point3D(new Coordinate(100), new Coordinate(0), new Coordinate(-149));
        Point3D P2 = new Point3D(new Coordinate(0), new Coordinate(100), new Coordinate(-149));
        Point3D P3 = new Point3D(new Coordinate(100), new Coordinate(100), new Coordinate(-149));
        Point3D P4 = new Point3D(new Coordinate(100), new Coordinate(-100), new Coordinate(-149));
        Point3D P5 = new Point3D(new Coordinate(-100), new Coordinate(0), new Coordinate(-149));
        Point3D P6 = new Point3D(new Coordinate(-100), new Coordinate(100), new Coordinate(-149));
        Point3D P7 = new Point3D(new Coordinate(-100), new Coordinate(-100), new Coordinate(-149));
        Triangle t1 = new Triangle(P1, P2, P3);
        Triangle t2 = new Triangle(P1, P2, P4);
        Triangle t3 = new Triangle(P5, P2, P6);
        Triangle t4 = new Triangle(P5, P2, P7);
        Scene _scene;

    }

//    @Test

//    public void basicRendering() {
//
//        Scene scene = new Scene("Hello");
//        scene.setCamera(c);
//        scene.setScreenDistance(50);
//        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.5));
//        Color blue = new Color(0, 0, 255);
//        Color green = new Color(0, 255, 0);
//        Color red = new Color(255, 0, 0);
//        Color orange = new Color(220, 100, 20);
//        Color pink = new Color(200, 0, 100);
//
//
//        Triangle triangle = new Triangle(red, 1, 0.5, 0.5, new Point3D(100, 0, -50),
//                new Point3D(0, 100, -50),
//                new Point3D(100, 100, -50));
//
//        Triangle triangle2 = new Triangle(green, 1, 0.5, 0.5, new Point3D(100, 0, -50),
//                new Point3D(0, -100, -50),
//                new Point3D(100, -100, -50));
//
//        Triangle triangle3 = new Triangle(orange, 1, 0.5, 0.5, new Point3D(-100, 0, -50),
//                new Point3D(0, 100, -50),
//                new Point3D(-100, 100, -50));
//
//        Triangle triangle4 = new Triangle(pink, 1, 0.5, 0.5, new Point3D(-100, 0, -50),
//                new Point3D(0, -100, -50),
//                new Point3D(-100, -100, -50));
//        Triangle triangle5 = new Triangle(green, 1, 0.5, 0.5, new Point3D(-100, 0, -50),
//                new Point3D(0, 0, -50),
//                new Point3D(-100, -100, -50));
//
//
//        //DirectionalLight light1 = new DirectionalLight(new Color(i, i, i), new Vector(-1, 0, -1));
//        //SpotLight light1 = new SpotLight(new Color(i,i,i), new Point3D(0,100,0), 0.5, 0.5,0.5,new Vector(0,-1,-1) );
//        //DirectionalLight light1 = new DirectionalLight(new Color(i, i, i), new Vector(-1, 0, -1));
//        //scene.getLights().add(light1);
//
//        ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);
//
//        Render render = new Render(scene, imageWriter);
//
//        render.renderImage();
//        render.printGrid(50);
//        imageWriter.writeToimage();
//
//
//    }


//    public void ball() {
//
//        Scene scene = new Scene("Hello");
//        scene.setCamera(c);
//        scene.setScreenDistance(50);
//        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.5));
//        Color blue = new Color(0, 0, 100);
//        Color red = new Color(20, 0, 0);
//
//
//        scene.addGeometry(new Sphere(blue, 1, 0.5, 0.5, 105, new Point3D(0.0, 0.0, -110)));
//
//        //SpotLight light1 = new SpotLight(new Color(i,i,i), new Point3D(0,100,0), 0.5, 0.5,0.5,new Vector(0,-1,-1) );
//        //DirectionalLight light1 = new DirectionalLight(new Color(i, i, i), new Vector(-1, 0, -1));
//        //scene.getLights().add(light1);
//        PointLight p2 = new PointLight(new Color(1, 1, 1), new Point3D(-30, 0, 0), 0.1, 0.001, 0);
//         DirectionalLight p3 = new DirectionalLight(new Color(50, 50, 50),new Vector(-1,0,-1));
//         for (int i = -10; i<10; i++) {
//             SpotLight p4 = new SpotLight(new Color(1, 1, 1), new Point3D(0, 0,10 ), 0.1, 0.01, 0, new Vector(0, i, -1));
//             PointLight p1 = new PointLight(new Color(1, 1, 1), new Point3D(30, 0, 0), 0.1, 0.001, 0);
//
//            scene.addLight(p4, p1);
//
//
//             ImageWriter imageWriter = new ImageWriter("test" + (i+10), 500, 500, 500, 500);
//
//             Render render = new Render(scene, imageWriter);
//
//             render.renderImage();
//             //render.printGrid(50);
//             imageWriter.writeToimage();
//             scene.getLights().clear();
//         }
//    }


//    @Test

//    public void basicRendering2() {
//
//        Scene scene = new Scene("Hello");
//        scene.setCamera(c);
//        scene.setScreenDistance(50);
//        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.5));
//        Color blue = new Color(0, 0, 255);
//        Color green = new Color(0, 255, 0);
//        Color red = new Color(255, 0, 0);
//        Color orange = new Color(220, 100, 20);
//        Color pink = new Color(200, 0, 100);
//
//        scene.addGeometry(new Sphere(blue, 2, 0.5, 0.2, 140, new Point3D(0, 0.0, -150)));
//        // scene.addGeometry(new Sphere(blue, 3,0.5,0.2, 70, new Point3D(-20.0, 0.0, -150)));
//
//        Triangle triangle = new Triangle(red, 3, 0.3, 0.6, new Point3D(100, 0, -50),
//                new Point3D(0, 100, -50),
//                new Point3D(100, 100, -50));
//
//        Triangle triangle2 = new Triangle(green, 2, 0.7, 0.6, new Point3D(100, 0, -50),
//                new Point3D(0, -100, -50),
//                new Point3D(100, -100, -50));
//
//        Triangle triangle3 = new Triangle(orange, 5, 0.3, 0.9, new Point3D(-100, 0, -50),
//                new Point3D(0, 100, -50),
//                new Point3D(-100, 100, -50));
//
//        Triangle triangle4 = new Triangle(pink, 3, 0.3, 0.6, new Point3D(-100, 0, -50),
//                new Point3D(0, -100, -50),
//                new Point3D(-100, -100, -50));
//
//        //scene.addGeometry(triangle);
//        //scene.addGeometry(triangle2);
//        //scene.addGeometry(triangle3);
//        //scene.addGeometry(triangle4);
//
//        ImageWriter imageWriter = new ImageWriter("Render test2", 500, 500, 500, 500);
//
//        PointLight light2 = new PointLight(new Color(100, 100, 100), new Point3D(150, 0, -150), 0.5, 0.3, 0.2);
//        scene.getLights().add(light2);
//        SpotLight light3 = new SpotLight(new Color(200, 100, 20), new Point3D(0, 0, 0), 0.5, 0.9, 0.4, new Vector(new Point3D(0, 0, -1)));
//        //scene.getLights().add(light3);
//        Render render = new Render(scene, imageWriter);
//
//        render.renderImage();
//        //render.printGrid(50);
//        imageWriter.writeToimage();
//
//
//    }

//    @Test

//    public void triangle() {
//
//        Scene scene = new Scene("Hello");
//        scene.setCamera(c);
//        scene.setScreenDistance(50);
//        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.5));
//        Color blue = new Color(0, 0, 100);
//        Color green = new Color(0, 150, 0);
//        Color red = new Color(150, 0, 0);
//        Color orange = new Color(220, 100, 20);
//        Color pink = new Color(200, 0, 100);
//
//
////        Triangle triangle1 = new Triangle(Color.BLACK, 1, 0.01, 0.5,
////                new Point3D(20000, 20000, -2000),
////                new Point3D(-20000, 20000, -2000),
////                new Point3D(20000, -20000, -2000));
////        Triangle triangle3 = new Triangle(Color.BLACK, 1, 0.01, 0.5,
////                new Point3D(-20000, -20000, -2000),
////                new Point3D(-20000, 20000, -2000),
////                new Point3D(20000, -20000, -2000));
//        Plane plane = new Plane(Color.BLACK, 1, 0.005, 0.1,
//                new Point3D(-20000, -20000, -2000),
//                new Point3D(-20000, 20000, -2000),
//                new Point3D(20000, -20000, -2000));
//        Triangle triangle2 = new Triangle(Color.BLACK, 1, 0.01, 0.5,
//                new Point3D(2000, -2000, -1000),
//                new Point3D(-2000, -2000, -1000),
//                new Point3D(-2000, 2000, -1000));
//        Sphere sphere = new Sphere(Color.BLACK,1, 0.01, 0.5,
//                1500, new Point3D(0,100,-2500));
//
//
//        scene.addGeometry(triangle2, sphere, plane);
//        PointLight pointLight = new PointLight(new Color(50, 40, 0), new Point3D(0,-4000,-1), //right light
//                0, 0.000001, 0.0000005);
//        PointLight pointLight2 = new PointLight(new Color(50, 40, 0), new Point3D(0,-3000,-1), //right light
//                0, 0.000001, 0.0000005);
//        scene.addLight(pointLight,pointLight2);
//        PointLight light1 = new PointLight(new Color(1,1,1), new Point3D(0,0,-1), 0.5, 0,0 );
//        //DirectionalLight light1 = new DirectionalLight(new Color(i, i, i), new Vector(-1, 0, -1));
////        scene.addLight(light1);
//
//        ImageWriter imageWriter = new ImageWriter("triangletest", 500, 500, 500, 500);
//
//        Render render = new Render(scene, imageWriter);
//
//        render.renderImage();
//        imageWriter.writeToimage();
//
//
//    }

    @Test

    public void testPart3_01(){
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("testPart3_01", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);
        Camera c1 = new Camera(new Point3D(0,0,0),
                new Vector(0,-1,0),
                new Vector(0,0,-100));
        scene.setCamera(c1);
        //scene.getCamera().setP0(new Point3D(0,0,0));
        scene.setScreenDistance(150);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Sphere sphere = new Sphere(new Color(0, 0, 100),
                170,0.5,0.5,0.5,0,80,
                new Point3D(0, 0, -250));
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Color(0, 50, 0),100,0,0.5,0.2,0.2,new Point3D(  0, 0, -450), //green triangle
                new Point3D( -2000,  0, -500),
                new Point3D( 0, -4000, -500));
        scene.addGeometry(triangle);

        Triangle triangle1 = new Triangle(new Color(80, 0, 0),100,0.1,0.3,0.5,0.2,new Point3D(  100, 100, -100), //red triangle
                new Point3D( 90,  200, -90),
                new Point3D( -50, 100, -100));
        scene.addGeometry(triangle1);

        Triangle triangle2 = new Triangle(new Color(33, 33, 33),100,0.2,0.5,0.2,0,new Point3D(  -2000, -2000, -2000), //gray triangle
                new Point3D( -2000,  500, -2000),
                new Point3D( 1500, 800, -800));
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(800, 80, 0), new Point3D(150,150,-50),
                 0.000001, 0.0000005,new Point3D(0,0,-100).subtract(new Point3D(50,0,0))));

        // I added this light
        scene.addLight(new SpotLight(new Color(100, 1000, 0), new Point3D(-1000,-600,-50),
                 0.000001, 0.0000005,new Point3D(0,0,-100).subtract(new Point3D(50,0,0))));

        Vector V = new Vector(new Point3D(-0.2,-0.6,-1)).normalize();
        scene.addLight(new SpotLight(new Color(220, 230, 1000), new Point3D(0,0,-350),
                0.00001, 0.00005,V));

        render.renderImage();
        //imageWriter.writeToimage();
        render.get_imageWriter().writeToimage();
    }

//    @Test

//    public void testBalls(){
//        Scene scene = new Scene("Hello");
//        scene.setCamera(c);
//        //scene.getCamera().setP0(new Point3D(0,0,0));
//        scene.setScreenDistance(150);
//        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));
//
//        //Material material = new Material(19, 0.4, 0.2, 0.2,1);
//        Point3D pSphere = new Point3D(-50, -100, -150);
//        Sphere sphere = new Sphere(new Color(10, 100, 20),20, 0, 1,50, pSphere);
//        //sphere.setEmmission(new Color(10, 100, 20));
//        //sphere.setMaterial(material);
//        scene.addGeometry(sphere);
//
//        Point3D pSphere1 = new Point3D(-30, 0, -250);
//        Sphere sphere1 = new Sphere(new Color(110, 20, 10), 20, 0, 1,70,pSphere1);
//        //sphere1.setEmmission(new Color(110, 20, 10));
//        //sphere1.setMaterial(material);
//        scene.addGeometry(sphere1);
//
//        Point3D pSphere2 = new Point3D(-10, 150, -350);
//        Sphere sphere2 = new Sphere(new Color(20, 20, 100), 20, 0, 1,90,pSphere2 );
//        //sphere2.setEmmission(new Color(20, 20, 100));
//        //sphere2.setMaterial(material);
//        scene.addGeometry(sphere2);
//
//
//        Plane plane = new Plane(new Color(133, 133, 133),20, 0.01, 1,new Point3D(-100, 0 , 0),new Vector(1,0,0));
//        //plane.setMaterial(15, 0.1, 0.4, 0.2,1);
//        //plane.setEmmission(new Color(133, 133, 133));
//        scene.addGeometry(plane);
//
//        scene.addLight(new SpotLight(new Color(50, 10, 0), new Point3D(150, 150, -40), //right light
//                 0, 0.00001, 0.000005,pSphere.subtract(new Point3D(300, 0, -250))));
//        scene.addLight(new SpotLight(new Color(50, 10, 0), new Point3D(150, 150, -50), //right light
//                0, 0.00001, 0.000005,pSphere1.subtract(new Point3D(300, 0, -250))));
//        scene.addLight(new SpotLight(new Color(50, 10, 0), new Point3D(150, 150, -60), //right light
//                0, 0.00001, 0.000005,pSphere2.subtract(new Point3D(300, 0, -250))));
//
//
//        ImageWriter imageWriter = new ImageWriter("testPart3_02", 500, 500, 500, 500);
//
//        Render render = new Render(scene,imageWriter);
//
//        render.renderImage();
//        //imageWriter.writeToimage();
//        render.get_imageWriter().writeToimage();
//    }


//    @Test
//    public void test() {
//        Scene scene = new Scene("Hello");
//        //scene.setCamera(c);
//        //scene.getCamera().setP0(new Point3D(0,0,0));
//        scene.setScreenDistance(80);
//        scene.setAmbientLight(new AmbientLight(new Color(1, 1, 1), 0.1));
//
//        Sphere sphere = new Sphere (new Color(0, 0, 100),20,1,1,800,new Point3D(0.0, 0.0, -1000));
//        //Material m=new Material();
//        //m.set_n(20);
//
//        //sphere.setMaterial(m);
//        scene.addGeometry(sphere);
//        scene.addLight(new PointLight(new Color(255,100,100), new Point3D(-200,200,100),  0, 0.00001, 0.000005));
//        //	scene.AddLight(new PointLight(new Color(255,100,100), new PointD3(-200,-200,100),  0, 0.00001, 0.000005));
//
//        ImageWriter imageWriter = new ImageWriter("Point light test 1", 500, 500, 500, 500);
//        Render render = new Render(scene,imageWriter);
//        render.renderImage();
//        imageWriter.writeToimage();
//
//    }


//    @Test
//    public void test2() {
//
//
//        Scene scene = new Scene("Hello");
//        //scene.setCamera(c);
//        scene.setScreenDistance(100);
//        scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 0.1));
//
//        Triangle triangle = new Triangle(new Color(200,0,0),20,1,1,new Point3D(  -1000,  -1000, -1000),
//                new Point3D( -1000, -2000, -1000),
//                new Point3D(2000, -1500, -1200));
//
//        Triangle triangle2 = new Triangle(new Color(200,0,0),20,1,1,new Point3D(   -1000,  -1000, -1000),
//                new Point3D( -600, -700, -1500),
//                new Point3D( 2000, -1500, -1200));
//        Sphere sphere = new Sphere(new Color(0, 0, 100),20,1,1, 700, new Point3D(100, 100, -1200));
//
//
//
//
//        scene.addGeometry(sphere);
//        scene.addGeometry(triangle);
//        scene.addGeometry(triangle2);
//
//        scene.addLight(new PointLight(new Color(50, 50, 50),new Point3D(300, 300, -0),0, 0.0000001, 0.00000005));
//
//
//        Point3D p = new Point3D (0,0,0);
//        Point3D p1 = new Point3D(0,-1,0);
//        Point3D p2 = new Point3D(1,0,0);
//
//        Vector v1 = new Vector(p1);
//        Vector v2 = new Vector(p2);
//
//        Camera camera = new Camera(p, v1, v2);
//
//        //scene.setColorScene(new Color(0, 0, 0));
//
//
//        //scene.setSceenDistance(100);
//        scene.setCamera(camera);
//
//
//        ImageWriter imageWriter = new ImageWriter("picture1 ", 500, 500, 500, 500);
//
//        Render render = new Render(scene,imageWriter);
//
//        render.renderImage();
//        imageWriter.writeToimage();
//    }

    @Test
    public void myTest() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("picture2 ", 200, 200, 500, 500);
        Render render = new Render(scene,imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));

        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4,0.1,0.4, 0.1,
                new Point3D(0, 0 , -200),new Vector(1,0,1));

        Sphere sphere = new Sphere(new Color(0, 0, 100),
                100,0.2,0.3,0.5,0,50,
                new Point3D(0, 0, -100));

        scene.addGeometry(plane, sphere);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0,200,0),
                0.0001, 0.00002);

        scene.addLight(pointLight);

        render.renderImage();
        imageWriter.writeToimage();
    }
    @Test
    public void myBallTest() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("picture3Balls ", 200, 200, 500, 500);
        Render render = new Render(scene,imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));

        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4,0.1,0.4, 0,
                new Point3D(-50, 0 , -500),new Vector(1,0,1));

        Sphere sphere = new Sphere(new Color(100, 0, 0),
                100,0.2,0.3,0.2,0,50,
                new Point3D(0, 100, -500));
        Sphere sphere1 = new Sphere(new Color(0, 0, 100),
                100,0.2,0.3,0.2,0,50,
                new Point3D(0, 0, -500));
        Sphere sphere2 = new Sphere(new Color(0, 100, 0),
                100,0.2,0.3,0.2,0,50,
                new Point3D(0, -100, -500));
        Pyramid pyramid = new Pyramid(Color.BLACK, 100, 0.2, 0.5, 0, 0,
                new Point3D(0,0,-500), new Point3D(0, 200,-700),
                new Point3D(0, -200, -700),
                new Point3D(100,0,-500));
//        scene.addGeometry(pyramid);

       scene.addGeometry(plane, sphere, sphere1, sphere2);

        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(200,200,0),
                0.0001, 0.000005);

        scene.addLight(pointLight);

        render.renderImage();
        imageWriter.writeToimage();
    }




}