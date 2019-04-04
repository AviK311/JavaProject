package primitives;
import java.util.Objects;

public class Vector {
    private Point3D head;

    /**
     * ctor with point param
     * @param head direction of the vector
     */
    public Vector(Point3D head) {
        this.head = new Point3D(head);
    }

    /**
     * copy ctor
     * @param other to copy
     */
    public Vector(Vector other) {
        this.head = other.getHead();
    }

    /**
     *
     * @return point head
     */
    public Point3D getHead() {
        return head;
    }

//    public void setHead(Point3D head) {
//        this.head = head;
//    }

    /**
     * equals override
     * @param o
     * @return if this equals o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return getHead().equals(vector.getHead());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHead());
    }

    @Override
    public String toString() {
        return "V{" +
                "h=" + head +
                '}';
    }

    /**
     * return a new vector, as an addition of two vectors
     * @param vector to add
     * @return
     */
    public Vector add(Vector vector) {
        return new Vector(head.add(vector));
    }

    /**
     * return a new vector, as an addition of this and the negative of another vector
     * @param vector to subtract
     * @return
     */
    public Vector subtract (Vector vector ){
        return new Vector(head.subtract(vector.getHead()).getHead());
    }

    /**
     * scales a vector
     * @param scalingFacor to scale by
     * @return scaled vector
     */
    public Vector scale(double scalingFacor){
        Coordinate x_ = this.head.getX().scale(scalingFacor);
        Coordinate y_ = this.head.getY().scale(scalingFacor);
        Coordinate z_ = this.head.getZ().scale(scalingFacor);
        return new Vector(new Point3D(x_,y_,z_));
    }

    /**
     *
     * @return length of vector
     */
    public double length(){
        return head.distance(Point3D.originPoint);
    }

    /**
     * normalize the vector
     * @return a normalized vector
     */
    public Vector normalize(){
        return new Vector(scale(1.0/length()));
    }

    /**
     * cross product between two vectors
     * @param vector to cross with
     * @return the crossproduct vector
     */
    public Vector crossProduct (Vector vector){
        Coordinate i= new Coordinate(head.getY().get()*vector.getHead().getZ().get()-head.getZ().get()*vector.getHead().y.get());
        Coordinate j= new Coordinate(-(head.getX().get()*vector.getHead().getZ().get()-head.getZ().get()*vector.getHead().getX().get()));
        Coordinate k= new Coordinate(head.getX().get()*vector.getHead().y.get()-head.getY().get()*vector.getHead().getX().get());
        return new Vector(new Point3D(i,j,k));
    }

    /**
     * dot product between two vectors
     * @param vector to multiply with
     * @return a double, multiplication
     */
    public double dotProduct(Vector vector){
        double _x=head.getX().get()*vector.getHead().getX().get();
        double _y=head.getY().get()*vector.getHead().getY().get();
        double _z=head.getZ().get()*vector.getHead().getZ().get();
        return _x+_y+_z;
    }
}
