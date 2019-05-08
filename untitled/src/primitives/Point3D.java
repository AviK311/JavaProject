package primitives;

import java.util.Objects;

public class Point3D extends Point2D {
    protected Coordinate z;
    public static final Point3D ORIGIN_POINT = new Point3D(0, 0, 0);

    /**
     * ctor with coordinate params
     *
     * @param x
     * @param y
     * @param z
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        this.z = new Coordinate(z);
    }

    /**
     * ctor with double params
     *
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = new Coordinate(z);
    }

    /**
     * copy ctor
     *
     * @param other
     */
    public Point3D(Point3D other) {
        super(other);
        this.z = new Coordinate(other.z);
    }

    /**
     * @return coordinate z
     */
    public Coordinate getZ() {
        return z;
    }

//    public void setZ(Coordinate z) {
//        this.z = z;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return getZ().equals(point3D.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getZ());
    }

    /**
     * equals override
     *
     * @param o
     * @return if this equals o
     */


    // TODO: 3/28/2019 We need to do proper תיעוד on everything 
    @Override
    public String toString() {
        return "P3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * adds a vector to a point
     *
     * @param other vector to add
     * @return the point at the other end
     */
    public Point3D add(Vector other) {
        return new Point3D(x.add(other.getHead().x), y.add(other.getHead().y), z.add(other.getHead().z));
    }

    /**
     * @param other
     * @return
     */
    public Vector subtract(Point3D other) {
        return new Vector(new Point3D(x.subtract(other.x), y.subtract(other.y), z.subtract(other.z)));
    }

    /**
     * gets the distance between this point and other point
     *
     * @param other
     * @return distance
     */
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * gets the squared distance between this point and other point
     *
     * @param other
     * @return squared distance
     */
    public double distanceSquared(Point3D other) {
        double _x = x.subtract(other.x).get();
        double _y = y.subtract(other.y).get();
        double _z = z.subtract(other.z).get();
        return _x * _x + _y * _y + _z * _z;
    }

}
