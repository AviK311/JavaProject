package elements;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Objects;

public class camera {
    Point3D p0;
    Vector vUp;
    Vector vRight;
    Vector vTo;
    public camera() {
        this.p0 = new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));
        vUp = new Vector(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0)));
        vTo = new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1)));
        vRight = vTo.crossProduct(vUp);//(1,0,0)
    }

    public camera(Point3D p0, Vector vup, Vector vright, Vector vtoward) {
        p0 = p0;
        vUp = vup;
        vRight = vright;
        vTo = vtoward;
    }
    public camera(camera other) {
        p0 = other.getP0();
        vUp = other.getvUp();
        vRight = other.getvRight();
        vTo = other.getvTo();
    }

    public Point3D getP0() {
        return p0;
    }

    public void setP0(Point3D p0) {
        this.p0 = p0;
    }

    public Vector getvUp() {
        return vUp;
    }

    public void setvUp(Vector vUp) {
        this.vUp = vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

    public void setvRight(Vector vRight) {
        this.vRight = vRight;
    }

    public Vector getvTo() {
        return vTo;
    }

    public void setvTo(Vector vTo) {
        this.vTo = vTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof camera)) return false;
        camera camera = (camera) o;
        return getP0().equals(camera.getP0()) &&
                getvUp().equals(camera.getvUp()) &&
                getvRight().equals(camera.getvRight()) &&
                getvTo().equals(camera.getvTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getP0(), getvUp(), getvRight(), getvTo());
    }

    @Override
    public String toString() {
        return "camera{" +
                "p0=" + p0 +
                ", vUp=" + vUp +
                ", vRight=" + vRight +
                ", vTo=" + vTo +
                '}';
    }
    public Ray constructRayThrowAPixel(int Nx,int Ny,double i,double j,double screenDist,double screenWidth,double screenHeight	){
        Point3D pc= p0.add(vTo.scale(screenDist));
        double Rx=screenWidth/Nx;
        double Ry=screenHeight/Ny;
        return new Ray(vRight,p0);
    }
}
