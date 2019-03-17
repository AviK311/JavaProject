package primitives;

public class Point3D extends Point2D {
    Coordinate z;

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        this.z = z;
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
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Point3D add(Point3D other) {
        return new Point3D(x.add(other.x),y.add(other.y),z.add(other.z));
    }

    public Point3D subtract(Point3D other) {
        return new Point3D(x.subtract(other.x),y.subtract(other.y),z.subtract(other.z));
    }
    public double distance(Point3D other){
        return Math.sqrt(distanceSquared(other));
    }
    public double distanceSquared(Point3D other){
        double _x=getX().subtract(other.x).get();
        double _y=getY().subtract(other.y).get();
        double _z=getZ().subtract(other.z).get();
        return _x*_x + _y*_y + _z*_z;
    }

}
