package renderer;

import elements.*;
import scene.Scene;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Color;
import primitives.Vector;

public class RenderTest {
    Camera c = new Camera(Point3D.ORIGIN_POINT, new Vector(0,1,0), new Vector(0,0,-1));


    @Test
    public void printGrid() {
       Scene scene = new Scene("Hello");
       scene.setCamera(c);
        ImageWriter imageWriter = new ImageWriter("printGridTest", 500, 500, 500, 500);

        Render r= new Render(scene, imageWriter);
        r.printGrid(50);
    }

    @Test
    public void p(){
        Sphere sphere = new Sphere(50,new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-150)));
        Point3D P1 = new Point3D(new Coordinate(100),new Coordinate(0),new Coordinate(-149));
        Point3D P2 = new Point3D(new Coordinate(0),new Coordinate(100),new Coordinate(-149));
        Point3D P3 = new Point3D(new Coordinate(100),new Coordinate(100),new Coordinate(-149));
        Point3D P4 = new Point3D(new Coordinate(100),new Coordinate(-100),new Coordinate(-149));
        Point3D P5 = new Point3D(new Coordinate(-100),new Coordinate(0),new Coordinate(-149));
        Point3D P6 = new Point3D(new Coordinate(-100),new Coordinate(100),new Coordinate(-149));
        Point3D P7 = new Point3D(new Coordinate(-100),new Coordinate(-100),new Coordinate(-149));
        Triangle t1 = new Triangle(P1,P2,P3);
        Triangle t2 = new Triangle(P1,P2,P4);
        Triangle t3 = new Triangle(P5,P2,P6);
        Triangle t4 = new Triangle(P5,P2,P7);
        Scene _scene;

    }

    @Test

    public void basicRendering(){

        Scene scene = new Scene("Hello");
        scene.setCamera(c);
        scene.setScreenDistance(50);
        scene.setAmbientLight(new AmbientLight(new Color(100,100,100),0.5));
        Color blue = new Color(0,0,255);
        Color green = new Color(0,255,0);
        Color red = new Color(255,0,0);
        Color orange = new Color(220,100,20);
        Color pink = new Color(200,0,100);


        Triangle triangle = new Triangle(red,1,0.5,0.5, new Point3D( 100, 0, -50),
                new Point3D(  0, 100, -50),
                new Point3D( 100, 100, -50));

        Triangle triangle2 = new Triangle(green ,1,0.5,0.5,new Point3D( 100, 0, -50),
                new Point3D(  0, -100, -50),
                new Point3D( 100,-100, -50));

        Triangle triangle3 = new Triangle(orange,1,0.5,0.5,  new Point3D(-100, 0, -50),
                new Point3D(  0, 100, -50),
                new Point3D(-100, 100, -50));

        Triangle triangle4 = new Triangle(pink,1,0.5,0.5, new Point3D(-100, 0, -50),
                new Point3D(  0,  -100, -50),
                new Point3D(-100, -100, -50));
        Triangle triangle5 = new Triangle(green,1,0.5,0.5, new Point3D(-100, 0, -50),
                new Point3D(0, 0, -50),
                new Point3D(-100, -100, -50));

        scene.addGeometry(triangle, triangle2, triangle3, triangle4, triangle5, new Sphere(blue,1,0.5,0.5, 105, new Point3D(0.0, 0.0, -150)));

            //SpotLight light1 = new SpotLight(new Color(i,i,i), new Point3D(0,100,0), 0.5, 0.5,0.5,new Vector(0,-1,-1) );
            //DirectionalLight light1 = new DirectionalLight(new Color(i, i, i), new Vector(-1, 0, -1));
            //scene.getLights().add(light1);

            ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);

            Render render = new Render(scene, imageWriter);

            render.renderImage();
            render.printGrid(50);
            imageWriter.writeToimage();



    }

    @Test

    public void basicRendering2(){

        Scene scene = new Scene("Hello");
        scene.setCamera(c);
        scene.setScreenDistance(50);
        scene.setAmbientLight(new AmbientLight(new Color(100,100,100),0.5));
        Color blue = new Color(0,0,255);
        Color green = new Color(0,255,0);
        Color red = new Color(255,0,0);
        Color orange = new Color(220,100,20);
        Color pink = new Color(200,0,100);

        scene.addGeometry(new Sphere(blue, 2,0.5,0.2, 140, new Point3D(0, 0.0, -150)));
       // scene.addGeometry(new Sphere(blue, 3,0.5,0.2, 70, new Point3D(-20.0, 0.0, -150)));

        Triangle triangle = new Triangle(red,3,0.3,0.6, new Point3D( 100, 0, -50),
                new Point3D(  0, 100, -50),
                new Point3D( 100, 100, -50));

        Triangle triangle2 = new Triangle(green, 2,0.7,0.6,new Point3D( 100, 0, -50),
                new Point3D(  0, -100, -50),
                new Point3D( 100,-100, -50));

        Triangle triangle3 = new Triangle(orange, 5,0.3,0.9, new Point3D(-100, 0, -50),
                new Point3D(  0, 100, -50),
                new Point3D(-100, 100, -50));

        Triangle triangle4 = new Triangle(pink, 3,0.3,0.6,new Point3D(-100, 0, -50),
                new Point3D(  0,  -100, -50),
                new Point3D(-100, -100, -50));

        //scene.addGeometry(triangle);
        //scene.addGeometry(triangle2);
        //scene.addGeometry(triangle3);
        //scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Render test2", 500, 500, 500, 500);

        PointLight light2=new PointLight(new Color(100,100,100), new Point3D(150,0,-150),0.5,0.3,0.2);
        scene.getLights().add(light2);
        SpotLight light3=new SpotLight(new Color(200,100,20), new Point3D(0,0,0),0.5,0.9,0.4,new Vector(new Point3D(0,0,-1)));
        //scene.getLights().add(light3);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        //render.printGrid(50);
        imageWriter.writeToimage();


    }

}