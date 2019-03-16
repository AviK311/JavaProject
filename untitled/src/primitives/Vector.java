package primitives;

import java.util.Objects;

public class Vector {
    Point3D head;

    public Vector(Point3D head) {
        this.head = head;
    }
    public Vector(Vector other) {
        this.head = other.head;
    }

    public Point3D getHead() {
        return head;
    }

    public void setHead(Point3D head) {
        this.head = head;
    }

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
        return "Vector{" +
                "head=" + head +
                '}';
    }
    public void add(Vector vector) {

        this.head.x.add(vector.getHead().getX());
        this.head.y.add(vector.getHead().getY());
        this.head.z.add(vector.getHead().getZ());
    }
    public void subtract (Vector vector ){
        this.head.x.subtract(vector.getHead().getX());
        this.head.y.subtract(vector.getHead().getY());
        this.head.z.subtract(vector.getHead().getZ());
    }

    public void scale(double scalingFacor){
        this.head.x=this.head.x.scale(scalingFacor);
        this.head.y=this.head.y.scale(scalingFacor);
        this.head.z=this.head.z.scale(scalingFacor);
    }

    public double length(){
        return Math.sqrt(Math.pow(head.x.get(),2)+Math.pow(head.y.get(),2)+Math.pow(head.z.get(),2));
    }
    public void normalize(){
        scale(1/length());
    }

    public Vector crossProduct (Vector vector){
        Coordinate i= new Coordinate(head.y.get()*vector.head.z.get()-head.z.get()*vector.head.y.get());
        Coordinate j= new Coordinate(-(head.x.get()*vector.head.z.get()-head.z.get()*vector.head.x.get()));
        Coordinate k= new Coordinate(head.x.get()*vector.head.y.get()-head.y.get()*vector.head.x.get());
        return new Vector(new Point3D(i,j,k));
    }

    public double dotProduct(Vector vector){
        double _x=head.x.get()*vector.head.x.get();
        double _y=head.y.get()*vector.head.y.get();
        double _z=head.z.get()*vector.head.z.get();
        return _x+_y+_z;
    }
}
