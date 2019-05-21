package renderer;

import Scene.scene;
import elements.pointLight;
import elements.spotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Color;
import primitives.Vector;

import java.awt.*;

import static org.junit.Assert.*;

public class RenderTest {

    @Test
    public void printGrid() {
        Render r=new Render();
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
        scene _scene;

    }

    //@Test

    public void basicRendering(){

        scene scene = new scene();
        Color blue = new Color(0,0,255);
        Color green = new Color(0,255,0);
        Color red = new Color(255,0,0);
        Color orange = new Color(220,100,20);
        Color pink = new Color(200,0,100);

        scene.addGeometry(new Sphere(blue, 105, new Point3D(0.0, 0.0, -150)));

        Triangle triangle = new Triangle(red, new Point3D( 100, 0, -50),
                new Point3D(  0, 100, -50),
                new Point3D( 100, 100, -50));

        Triangle triangle2 = new Triangle(green ,new Point3D( 100, 0, -50),
                new Point3D(  0, -100, -50),
                new Point3D( 100,-100, -50));

        Triangle triangle3 = new Triangle(orange,  new Point3D(-100, 0, -50),
                new Point3D(  0, 100, -50),
                new Point3D(-100, 100, -50));

        Triangle triangle4 = new Triangle(pink, new Point3D(-100, 0, -50),
                new Point3D(  0,  -100, -50),
                new Point3D(-100, -100, -50));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.printGrid(50);
        imageWriter.writeToimage();


    }

    @Test

    public void basicRendering2(){

        scene scene = new scene();
        Color blue = new Color(0,0,255);
        Color green = new Color(0,255,0);
        Color red = new Color(255,0,0);
        Color orange = new Color(220,100,20);
        Color pink = new Color(200,0,100);

        scene.addGeometry(new Sphere(blue, 2,0.5,0.2, 140, new Point3D(30, 0.0, -150)));
        scene.addGeometry(new Sphere(blue, 3,0.5,0.2, 70, new Point3D(-20.0, 0.0, -150)));

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

        pointLight light2=new pointLight(new Color(0,100,20), new Point3D(190,150,80),0.5,0.3,0.4);
        scene.getLights().add(light2);
        spotLight light3=new spotLight(new Color(200,100,20), new Point3D(170,130,80),0.5,0.9,0.4,new Vector(new Point3D(1,2,3)));
        scene.getLights().add(light3);
        Render render = new Render(scene, imageWriter);

        render.renderImage();
        //render.printGrid(50);
        imageWriter.writeToimage();


    }

}