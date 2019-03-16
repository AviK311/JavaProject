package primitives;

public class testing {

    public static void main(String[] args)
    {
        Coordinate c1 = new Coordinate(1);
        Coordinate c2 = new Coordinate(2);
        Coordinate c3 = new Coordinate(3);
        Coordinate c4 = new Coordinate(4);
        Coordinate c5 = new Coordinate(5);
        Coordinate c6 = new Coordinate(6);
        Point3D p1 = new Point3D(c1,c2,c3);
        Point3D p2 = new Point3D(c4,c5,c6);
        Vector v1=new Vector(p1);
        Vector v2=new Vector(p2);
        System.out.println(v1.dotProduct(v2));
        System.out.println(v1.crossProduct(v2));

    }
}
