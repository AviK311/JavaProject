package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Objects;

public class Camera {
    private Point3D p0;
    private Vector vUp;
    private Vector vRight;
    private Vector vTo;

    /**
     * default ctor - the camera we often use
     */
    public Camera() {
        this.p0 = Point3D.ORIGIN_POINT;
        vUp = new Vector(0, 1, 0);
        vTo = new Vector(0, 0, -1);
        vRight = vTo.crossProduct(vUp);//(1,0,0)
    }

    /**
     * params ctor. cRight is calculated by vUp and vtoward
     *
     * @param p0
     * @param vup
     * @param vtoward
     */
    public Camera(Point3D p0, Vector vup, Vector vtoward) {
        this.p0 = p0;
        this.vRight = vup;
        this.vTo = vtoward;
        vUp = vTo.crossProduct(vRight);//(1,0,0)
        //this.vRight = vright;
    }

    /**
     * copy Ctor
     *
     * @param other
     */
    public Camera(Camera other) {
        this.p0 = other.getP0();
        this.vUp = other.getvUp();
        this.vRight = other.getvRight();
        this.vTo = other.getvTo();
    }

    /**
     * returns the origin point of the camera
     *
     * @return p0
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * returns the up vector of the camera
     *
     * @return vUp
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * returns the right vector of the camera
     *
     * @return vright
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * returns the vector towards the scene
     *
     * @return vTo
     */
    public Vector getvTo() {
        return vTo;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "p0=" + p0 +
                ", vUp=" + vUp +
                ", vRight=" + vRight +
                ", vTo=" + vTo +
                '}';
    }

    /**
     * Constructs a ray for future use of getting intersection points
     *
     * @param Nx           number of width pixels
     * @param Ny           number of height pixels
     * @param i            width index
     * @param j            height index
     * @param screenDist   distance from Camera to screen
     * @param screenWidth  screen width
     * @param screenHeight screen height
     * @return a ray whose direction is going through the center of the chosen pixel
     */
    public Ray constructRayThroughAPixel(int Nx, int Ny, double i, double j, double screenDist, double screenWidth, double screenHeight) {
        Point3D pc = p0.add(vTo.scale(screenDist));
        double Rx = screenWidth / Nx;
        double Ry = screenHeight / Ny;
        double A = (i - (double) Nx / 2) * Rx - Rx / 2;
        double B = (j - (double) Ny / 2) * Ry - Ry / 2;
        Point3D p;
        if (A == 0 && B == 0)
            p = pc;
        else if (A == 0)
            p = pc.add(vUp.scale(-B));
        else if (B == 0)
            p = pc.add(vRight.scale(A));
        else
            p = pc.add(vRight.scale(A).subtract(vUp.scale(B)));
        return new Ray(new Vector(p).normalize(), p0);
    }

    public void setP0(Point3D p0) {
        this.p0 = p0;
    }

    public void setvUp(Vector vUp) {
        this.vUp = vUp;
    }

    public void setvRight(Vector vRight) {
        this.vRight = vRight;
    }

    public void setvTo(Vector vTo) {
        this.vTo = vTo;
    }
}
