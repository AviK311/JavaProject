package primitives;
import java.util.Objects;

public class Vector {
    private Point3D head;

    public Vector(Point3D head) {
        this.head = new Point3D(head);
    }
    public Vector(Vector other) {
        this.head = other.getHead();
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
        return "V{" +
                "h=" + head +
                '}';
    }
    public Vector add(Vector vector) {
        return new Vector(head.add(vector));
    }
    public Vector subtract (Vector vector ){
        return new Vector(head.subtract(vector.getHead()).getHead());
    }

    public Vector scale(double scalingFacor){
        Coordinate x_ = this.head.getX().scale(scalingFacor);
        Coordinate y_ = this.head.getY().scale(scalingFacor);
        Coordinate z_ = this.head.getZ().scale(scalingFacor);
        return new Vector(new Point3D(x_,y_,z_));
    }

    public double length(){
        return Math.sqrt(head.getX().get()*head.getX().get()+head.getY().get()*head.getY().get()+head.getZ().get()*head.getZ().get());
    }
    public Vector normalize(){
        return new Vector(scale(1/length()));
    }

    public Vector crossProduct (Vector vector){
        Coordinate i= new Coordinate(head.getY().get()*vector.getHead().getZ().get()-head.getZ().get()*vector.getHead().y.get());
        Coordinate j= new Coordinate(-(head.getX().get()*vector.getHead().getZ().get()-head.getZ().get()*vector.getHead().getX().get()));
        Coordinate k= new Coordinate(head.getX().get()*vector.getHead().y.get()-head.getY().get()*vector.getHead().getX().get());
        return new Vector(new Point3D(i,j,k));
    }

    public double dotProduct(Vector vector){
        double _x=head.getX().get()*vector.getHead().getX().get();
        double _y=head.getY().get()*vector.getHead().getY().get();
        double _z=head.getZ().get()*vector.getHead().getZ().get();
        return _x+_y+_z;
    }
}
