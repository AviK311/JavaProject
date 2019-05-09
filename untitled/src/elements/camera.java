package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Objects;

public class camera {
    private Point3D p0;
    private Vector vUp;
    private Vector vRight;
    private  Vector vTo;
    public camera() {
        this.p0 = Point3D.ORIGIN_POINT;
        vUp = new Vector(0,1,0);
        vTo = new Vector(0,0,-1);
        vRight =  vTo.crossProduct(vUp);//(1,0,0)
    }

    public camera(Point3D p0, Vector vup, Vector vtoward) {
        this.p0 = p0;
        this.vUp = vup;
        this.vTo = vtoward;
        vRight =  vTo.crossProduct(vUp);//(1,0,0)
        //this.vRight = vright;
    }
    public camera(camera other) {
        this.p0 = other.getP0();
        this.vUp = other.getvUp();
        this.vRight = other.getvRight();
        this.vTo = other.getvTo();
    }

    public Point3D getP0() {
        return p0;
    }

//    public void setP0(Point3D p0) {
//        this.p0 = p0;
//    }

    public Vector getvUp() {
        return vUp;
    }

//    public void setvUp(Vector vUp) {
//        this.vUp = vUp;
//    }

    public Vector getvRight() {
        return vRight;
    }

//    public void setvRight(Vector vRight) {
//        this.vRight = vRight;
//    }

    public Vector getvTo() {
        return vTo;
    }

//    public void setvTo(Vector vTo) {
//        this.vTo = vTo;
//    }

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

    /**
     * Constructs a ray for future use of getting intersection points
     * @param Nx number of width pixels
     * @param Ny number of height pixels
     * @param i width index
     * @param j height index
     * @param screenDist distance from camera to screen
     * @param screenWidth screen width
     * @param screenHeight screen height
     * @return a ray whose direction is going through the center of the chosen pixel
     */
    public Ray constructRayThroughAPixel(int Nx, int Ny, double i, double j, double screenDist, double screenWidth, double screenHeight	){
        Point3D pc = p0.add(vTo.scale(screenDist));
        double Rx=screenWidth/Nx;
        double Ry=screenHeight/Ny;
        double A = (i-(double)Nx/2)*Rx-Rx/2;
        double B = (j-(double)Ny/2)*Ry-Ry/2;
        Point3D p;
        if (A == 0 && B == 0)
            p = pc;
        else if (A == 0)
            p = pc.add(vUp.scale(-B));
        else if (B == 0)
            p = pc.add(vRight.scale(A));
        else
            p = pc.add(vRight.scale(A).subtract(vUp.scale(B)));
        return new Ray(new Vector(p).normalize(),p0);
    }
}
