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

    public void add(Point3D other) {
        super.add(other);          //to check
        this.z.add(other.z);
    }

    public void subtract(Point3D other) {
        super.subtract(other);      //to check
        this.z.subtract(other.z);
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
