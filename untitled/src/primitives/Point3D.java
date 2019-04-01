package primitives;

import java.util.Objects;

public class Point3D extends Point2D {
    private Coordinate z;
    public static final Point3D originPoint = new Point3D(0,0,0);

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        this.z = z;
    }
    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = new Coordinate(z);
    }

    public Point3D(Point3D other) {
        super(other);
        this.z = other.z;
    }

    public Coordinate getZ() {
        return z;
    }

    public void setZ(Coordinate z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        Point3D point3D = (Point3D) o;
        return super.equals(o) && Objects.equals(getZ(), point3D.getZ());
    }

    // TODO: 3/28/2019 We need to do proper תיעוד on everything 
    @Override
    public String toString() {
        return "P3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    
    public Point3D add(Vector other) {
        return new Point3D(x.add(other.getHead().getX()),y.add(other.getHead().getY()),z.add(other.getHead().getZ()));
    }

    public Vector subtract(Point3D other) {
        return new Vector(new Point3D(x.subtract(other.getX()),y.subtract(other.getY()),z.subtract(other.getZ())));
    }
    public double distance(Point3D other){
        return Math.sqrt(distanceSquared(other));
    }
    public double distanceSquared(Point3D other){
        double _x=getX().subtract(other.getX()).get();
        double _y=getY().subtract(other.getY()).get();
        double _z=getZ().subtract(other.getZ()).get();
        return _x*_x + _y*_y + _z*_z;
    }

}
