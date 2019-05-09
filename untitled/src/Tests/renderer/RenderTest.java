package renderer;

import Scene.scene;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Color;

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

    @Test

    public void basicRendering(){

        scene scene = new scene();
        Color blue = new Color(0,0,255);
        Color green = new Color(0,255,0);
        Color red = new Color(255,0,0);
        Color orange = new Color(220,150,0);
        Color pink = new Color(200,0,100);

        scene.addGeometry(new Sphere(blue, 50, new Point3D(0.0, 0.0, -150)));

        Triangle triangle = new Triangle(red,new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(green,new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));

        Triangle triangle3 = new Triangle(orange,new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));

        Triangle triangle4 = new Triangle(pink,new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));

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

}