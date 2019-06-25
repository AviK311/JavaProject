package primitives;

import java.util.Objects;

public class Vector {
    private Point3D head;

    /**
     * ctor with point param
     *
     * @param head direction of the vector
     */
    public Vector(Point3D head) {
//       if (head.equals(Point3D.ORIGIN_POINT))
//           throw new IllegalArgumentException("Zero vector is not allowed");
        this.head = new Point3D(head);
    }

    /**
     * ctor with double params
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        this.head = new Point3D(x, y, z);
//        if (this.head.equals(Point3D.ORIGIN_POINT))
//            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    /**
     * copy ctor
     *
     * @param other to copy
     */
    public Vector(Vector other) {
        this.head = new Point3D(other.head);
    }

    /**
     * @return point head
     */
    public Point3D getHead() {
        return head;
    }

//    public void setHead(Point3D head) {
//        this.head = head;
//    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * equals override
     *
     * @param o
     * @return if this equals o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }



    @Override
    public String toString() {
        return "V{" +
                "h=" + head +
                '}';
    }

    /**
     * return a new vector, as an addition of two vectors
     *
     * @param vector to add
     * @return
     */
    public Vector add(Vector vector) {
        return new Vector(head.add(vector));
    }

    /**
     * return a new vector, as an addition of this and the negative of another vector
     *
     * @param vector to subtract
     * @return
     */
    public Vector subtract(Vector vector) {
        return new Vector(head.subtract(vector.head).head);
    }

    /**
     * scales a vector
     *
     * @param scalingFacor to scale by
     * @return scaled vector
     */
    public Vector scale(double scalingFacor) {
        Coordinate x_ = this.head.getX().scale(scalingFacor);
        Coordinate y_ = this.head.getY().scale(scalingFacor);
        Coordinate z_ = this.head.getZ().scale(scalingFacor);
        Point3D p = new Point3D(x_, y_, z_);
        return new Vector(p);
    }

    /**
     * return a new vector with the requested length
     * @param scalingFactor
     * @return
     */
    public Vector rescale(double scalingFactor){
        return new Vector(this).rescale(scalingFactor);
    }

    /**
     * @return length of vector
     */
    public double length() {
        return head.distance(Point3D.ORIGIN_POINT);
    }

    /**
     * normalize the vector
     *
     * @return a new normalized vector
     */
    public Vector normalize() {
        return new Vector(scale(1.0 / length()));
    }

    /**
     * cross product between two vectors
     *
     * @param vector to cross with
     * @return the crossproduct vector
     */
    public Vector crossProduct(Vector vector) {
        double i = head.y.get() * vector.head.z.get() - head.z.get() * vector.head.y.get();
        double j = -(head.x.get() * vector.head.z.get() - head.z.get() * vector.head.x.get());
        double k = head.x.get() * vector.head.y.get() - head.y.get() * vector.head.x.get();
        return new Vector(i, j, k);
    }
    /**
     * dot product between two vectors
     *
     * @param vector to multiply with
     * @return a double, multiplication
     */
    public double dotProduct(Vector vector) {
        double _x = head.x.get() * vector.head.x.get();
        double _y = head.y.get() * vector.head.y.get();
        double _z = head.z.get() * vector.head.z.get();
        return _x + _y + _z;
    }


}
