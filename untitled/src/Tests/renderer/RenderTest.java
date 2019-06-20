package renderer;

import elements.*;
import primitives.*;
import scene.Scene;
import geometries.*;
import org.junit.Test;

public class RenderTest {
    Camera c = new Camera(new Point3D(0, 0, 1000), new Vector(0, -1, 0), new Vector(0, 0, -1));

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
                1, 0.5, 0.5, 0,0,
                new Point3D(100, 0, -50),
                new Point3D(0, 100, -50),
                new Point3D(100, 100, -50));

        Triangle triangle2 = new Triangle(green,
                1, 0.5, 0.5,0,0,
                new Point3D(100, 0, -50),
                new Point3D(0, -100, -50),
                new Point3D(100, -100, -50));

        Triangle triangle3 = new Triangle(orange,
                1, 0.5, 0.5,0,0,
                new Point3D(-100, 0, -50),
                new Point3D(0, 100, -50),
                new Point3D(-100, 100, -50));

        Triangle triangle4 = new Triangle(pink,
                1, 0.5, 0.5,0,0,
                new Point3D(-100, 0, -50),
                new Point3D(0, -100, -50),
                new Point3D(-100, -100, -50));
        Sphere sphere = new Sphere(blue,
                1,0.5,0.5,0,0,
                50,new Point3D(0,0,-50));
        scene.addGeometry(triangle,triangle2,triangle3,triangle4,sphere);

        ImageWriter imageWriter = new ImageWriter("4 triangles and sphere", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.printGrid(50);
        imageWriter.writeToimage();

    }








    @Test

    public void testPart3_01() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("testPart3_01", 500, 500, 500, 500);

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
                new Vector(1,1,0.75),
                new Vector(0.5,-2,2),
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
        render.get_imageWriter().writeToimage();
    }



    @Test
    public void myTest() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("picture2 ", 200, 200, 500, 500);
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
    public void myBallTest() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("three balls on a circle ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));

        Plane plane = new Circle(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.9, 0,
                new Point3D(-50, 0, -500), new Vector(1, 0, 1),
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
        Pyramid pyramid = new Pyramid(Color.BLACK, 100, 0.2, 0.5, 0, 0,
                new Point3D(0, 0, -500), new Point3D(0, 200, -700),
                new Point3D(0, -200, -700),
                new Point3D(100, 0, -500));
//        scene.addGeometry(pyramid);

        scene.addGeometry(plane, sphere, sphere1, sphere2);
        scene.addGeometry(sphere3, sphere4, sphere5);

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
    public void myBallTest2() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("pictureBalls ", 200, 200, 500, 500);
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
    public void myBallTest3() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("picture3Balls3 ", 200, 200, 500, 500);
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


//    @Test
//    public void box() {
//        Scene scene = new Scene("Hello");
//        ImageWriter imageWriter = new ImageWriter("box ", 200, 200, 500, 500);
//        Render render = new Render(scene, imageWriter);
//        scene.setCamera(c);
//        scene.setScreenDistance(800);
//        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));
//
//        Plane plane = new Plane(new Color(40, 40, 40),
//                100, 0.4, 0.1, 0.4, 0,
//                new Point3D(0, 0, 0), new Vector(125, 20, 7));
//
//        Sphere sphere1 = new Sphere(new Color(0, 0, 100),
//                100, 0, 1, 1, 0, 20,
//                new Point3D(45, 0, 0));
//
//
//
//        Box box = new Box(new Color(70, 0, 20),
//                100, 0.5, 0.5, 0.5, 0,
//                new Point3D(0, 0, 100) ,
//                new Vector(1,1,0),
//                new Vector(1,-1,2), 50);
//
//
//        scene.addGeometry(plane, box);
//        PointLight pointLight = new PointLight(new Color(800, 600, 0),
//                new Point3D(90, -100, 0),
//                0.0001, 0.00002);
//
//        scene.addLight(pointLight);
//        DirectionalLight light1 = new DirectionalLight(new Color(200, 100, 200), new Vector(-1, 1, -1));
//        //scene.addLight(light1);
//
//        SpotLight light3 = new SpotLight(new Color(200, 100, 20), new Point3D(200, -200, 200), 0.5, 0.9, new Vector(new Point3D(-1, 1, -1)));
//        scene.getLights().add(light3);
//
//
//        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
//                new Point3D(80, -80, 200),
//                0.0001, 0.00002);
//
//        scene.addLight(pointLight2);
//
//        render.renderImage();
//        imageWriter.writeToimage();
//    }

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


        scene.addGeometry(plane, sphere,  sphere2);

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
        ImageWriter imageWriter = new ImageWriter("p111 ", 200, 200, 500, 500);
        Render render = new Render(scene,imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(700);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));

        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4,0.1,0.4, 0.1,
                new Point3D(-50, 0 , 0),new Vector(1,0.2,0.2));


        Triangle triangle1=new Triangle(new Color(100,100,50),10,0.2,0.3,0.2,0.5,new Point3D(50,-60,0),new Point3D(-30,-90,0),new Point3D(-30,-30,0));
        Triangle triangle2=new Triangle(new Color(40, 40, 40).scale(2),10,0.2,0.3,0.2,0,new Point3D(20,-60,1),new Point3D(-30,-80,1),new Point3D(-30,-40,1));
        Triangle triangle3=new Triangle(new Color(100,100,50).scale(2),10,0.2,0.3,0.2,0,new Point3D(20,-60,2),new Point3D(-20,-76,2),new Point3D(-20,-44,2));
        Triangle triangle4=new Triangle(new Color(40, 40, 40),10,0.2,0.3,0.2,0.5,new Point3D(20,-60,3),new Point3D(-10,-72,3),new Point3D(-10,-48,3));

        Triangle triangle5=new Triangle(new Color(100,100,50),10,0.2,0.3,0.2,0.5,new Point3D(50,-40,0),new Point3D(-30,-10,0),new Point3D(50,20,0));
        Triangle triangle6=new Triangle(new Color(40,40,40),10,0.2,0.3,0.2,0.5,new Point3D(50,-30,1),new Point3D(0,-10,1),new Point3D(50,10,1));
        Triangle triangle7=new Triangle(new Color(100,100,50),10,0.2,0.3,0.2,0.5,new Point3D(-30,50,0),new Point3D(-30,30,0),new Point3D(35,30,0));
        Triangle triangle8=new Triangle(new Color(100,100,50),10,0.2,0.3,0.2,0.5,new Point3D(35,50,0),new Point3D(-30,50,0),new Point3D(35,30,0));

        Sphere sphere = new Sphere(new Color(70, 80, 50).scale(2),
                100,0.2,0.4,0.3,0.2,11,
                new Point3D(50, 40, 0));

        scene.addGeometry(plane, triangle1,triangle2,triangle3,triangle4,triangle5,triangle6,triangle7,triangle8, sphere);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0,200,0),
                0.0001, 0.00002);

        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);

        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10,230,0),
                0.0001, 0.00002);

        scene.addLight(pointLight2);



        render.renderImage();
        imageWriter.writeToimage();
    }

    @Test
    public void pyramid2() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("pyramid2 ", 200, 200, 500, 500);
        Render render = new Render(scene,imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));

        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4,0.1,0.4, 0.1,
                new Point3D(-50, 0 , 0),new Vector(1,0.2,0.2));

        Pyramid pyramid = new Pyramid(new Color(30, 70, 80),
                100, 0.2, 0.3, 0.4, 0.2, new Point3D(0, -70, 0),
                new Point3D(0, 0, 30), new Point3D(30, -80, -100), new Point3D(30, 40, -100), new Point3D(60,-45,-50));

        Sphere sphere = new Sphere(new Color(70, 80, 50).scale(2),
                100,0.2,0.4,0.3,0.2,11,
                new Point3D(50, 40, 0));

        scene.addGeometry(plane,sphere,pyramid);
        PointLight pointLight = new PointLight(new Color(800, 600, 0),
                new Point3D(0,200,0),
                0.0001, 0.00002);

        scene.addLight(pointLight);
        DirectionalLight light1 = new DirectionalLight(new Color(50, 100, 50), new Vector(-1, 0, -1));
        scene.addLight(light1);

        PointLight pointLight2 = new PointLight(new Color(800, 600, 0),
                new Point3D(10,230,0),
                0.0001, 0.00002);

        scene.addLight(pointLight2);

        PointLight pointLight3 = new PointLight(new Color(800, 600, 0),
                new Point3D(60,-90,0),
                0.0001, 0.00002);

        scene.addLight(pointLight3);



        render.renderImage();
        imageWriter.writeToimage();
    }



    @Test
    public void myTest2() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("picture7_ ", 200, 200, 750, 750);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));

        Plane plane = new Circle(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(0, 0, -220), new Vector(1, 0, 1),1000);

        HalfSphere halfSphere = new HalfSphere(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                ,new Vector(-1,-1,-0.5).scale(-1),new Point3D(0, 0, -100),70);
        HalfSphere halfSphere2 = new HalfSphere(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                ,new Vector(-1,-1,-0.5),new Point3D(0, 0, -100),80);

        Halo halo = new Halo(new Color(100, 0, 0),
                100, 0.2, 0.3, 0.7, 0.2, 60,
                new Vector(1,-1,-0.5).scale(-1),new Point3D(0, 0, -100), 70);
        HolyBall holyball = new HolyBall(new Color(50, 0, 100),
                100, 0.2, 0.3, 0.5, 0.2, 80,
                new Vector(1,1,0.75),
                new Vector(0.5,-2,2),new Point3D(0, 0, -100), 50);
        Sphere sphere2 = new Sphere(new Color(0, 0, 50),
                100, 0.2, 0.3, 0.1, 0.3, 30,
                new Point3D(-20, 0, -100));

        SphereTriangle st = new SphereTriangle(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                ,new Vector(-1,1,1),new Point3D(0, 0, -100),80);
        SphereTriangle st2 = new SphereTriangle(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                ,new Vector(-1,1,1),new Point3D(0, 50, -100),80);

       //scene.addGeometry(plane, halfSphere, halo, sphere2);
       scene.addGeometry(plane,holyball);

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



        scene.addGeometry( plane2);
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
    public void myTest4() {
        Scene scene = new Scene("Hello");
        ImageWriter imageWriter = new ImageWriter("picture9 ", 200, 200, 500, 500);
        Render render = new Render(scene, imageWriter);
        scene.setCamera(c);
        scene.setScreenDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(100, 100, 100), 0.1));

        Plane plane = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0.1,
                new Point3D(0, 0, -220), new Vector(1, 0, 1));

        HalfSphere halfSphere = new HalfSphere(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                ,new Vector(-1,1,0.5),new Point3D(0, 0, -100),30);
        HalfSphere halfSphere2 = new HalfSphere(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                ,new Vector(-1,-1,-0.5),new Point3D(0, 0, -100),80);

        Halo halo = new Halo(new Color(100, 0, 0),
                100, 0.2, 0.3, 0.7, 0.2, 60,
                new Vector(1,-1,-0.5).scale(-1),new Point3D(0, 0, -100), 70);
        HolyBall holyball = new HolyBall(new Color(50, 0, 100),
                100, 0.2, 0.3, 0.5, 0.2, 80,
                new Vector(1,1,0.75),
                new Vector(0.5,-2,2),new Point3D(0, 0, -100), 50);
        Sphere sphere2 = new Sphere(new Color(0, 0, 50),
                100, 0.2, 0.3, 0.1, 0.3, 30,
                new Point3D(-20, 0, -100));

        SphereTriangle st = new SphereTriangle(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                ,new Vector(-1,1,1),new Point3D(0, 0, -100),80);
        SphereTriangle st2 = new SphereTriangle(new Color(0, 60, 0),
                100, 0.2, 0.3, 0.1, 0.3, 50
                ,new Vector(-1,1,1),new Point3D(0, 50, -100),80);

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
                new Vector(0,-1,1)
                ,50);
        Square plane4 = new Square(new Color(10, 10, 10),
                100, 0, 0, 1, 0,
                new Point3D(0, 0, -100).add(new Vector(50,0,0)),
                new Vector(0,1,1),
                new Vector(0,1,-1),50);
        Plane plane3 = new Plane(new Color(10, 10, 10),
                100, 0.3, 0.3, 0.7, 0,
                new Point3D(0, 0, -150),
                new Vector(1, 0, 0).crossProduct(
                        new Vector(0,-1,-1)));
        HolyBall holyball = new HolyBall(new Color(50, 0, 100),
                100, 0.2, 0.3, 0.5, 0.2, 30,
                new Vector(1,1,0.75),
                new Vector(0.5,-2,2),new Point3D(-10, 0, -100), 15);
        Plane plane2 = new Plane(new Color(40, 40, 40),
                100, 0.4, 0.1, 0.4, 0,
                new Point3D(-5, 0, 0), new Vector(1, 0, 0.1));



        scene.addGeometry(plane,plane2, plane3, holyball, plane4);
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







}