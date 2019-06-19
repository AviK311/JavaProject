package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Objects;

public class Camera {
    private Point3D p0;
    private Vector vUp;
    private Vector vRight;
    private Vector vTo;

    /**
     * params ctor. cRight is calculated by vUp and vtoward
     *
     * @param p0
     * @param vup
     * @param vtoward
     */
    public Camera(Point3D p0, Vector vup, Vector vtoward) {
        this.p0 = p0;
        this.vUp = vup.normalize();
        this.vTo = vtoward.normalize();
        if (!Util.isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("Vup and Vto must be orthogonal!");
        this.vRight = vTo.crossProduct(vUp);//(1,0,0)
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
        double Pi = (i - (double) Nx / 2) * Rx + Rx / 2;
        double Pj = (j - (double) Ny / 2) * Ry + Ry / 2;
        Point3D p = pc;
        if (Pi != 0)
            p = p.add(vRight.scale(Pi));
        if (Pj != 0)
            p = p.add(vUp.scale(-Pj));
        return new Ray(p.subtract(p0), p0);
    }

    public ArrayList<Ray> constructRaysThroughAPixel(int Nx, int Ny, double i, double j, double screenDist, double screenWidth, double screenHeight) {
        ArrayList<Ray> rays=new ArrayList<Ray>();
        Ray r1 = returnOneRay(Nx, Ny, i, j, screenDist, screenWidth,screenHeight,0.5,0.5);
        rays.add(r1);
        Ray r2 = returnOneRay(Nx, Ny, i, j, screenDist, screenWidth,screenHeight,0.25,0.25);
        rays.add(r2);
        Ray r3 = returnOneRay(Nx, Ny, i, j, screenDist, screenWidth,screenHeight,0.75,0.75);
        rays.add(r3);
        Ray r4 = returnOneRay(Nx, Ny, i, j, screenDist, screenWidth,screenHeight,0.25,0.75);
        rays.add(r4);
        Ray r5 = returnOneRay(Nx, Ny, i, j, screenDist, screenWidth,screenHeight,0.75,0.25);
        rays.add(r5);
        return rays;
    }

    private Ray returnOneRay(int Nx, int Ny, double i, double j, double screenDist, double screenWidth, double screenHeight, double addX, double addY)
    {
        Point3D pc = p0.add(vTo.scale(screenDist));
        double Rx = screenWidth / Nx;
        double Ry = screenHeight / Ny;
        double Pi = (i - (double) Nx / 2) * Rx + Rx*addX;
        double Pj = (j - (double) Ny / 2) * Ry + Ry*addY;
        Point3D p = pc;
        if (Pi != 0)
            p = p.add(vRight.scale(Pi));
        if (Pj != 0)
            p = p.add(vUp.scale(-Pj));
        return new Ray(p.subtract(p0), p0);
    }

}
