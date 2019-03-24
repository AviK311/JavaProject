package primitives;

public class Point3D extends Point2D {
    private Coordinate z;

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

    public Point3D add(Vector other) {
        return new Point3D(x.add(other.getHead().getX()),y.add(other.getHead().getY()),z.add(other.getHead().getY()));
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
