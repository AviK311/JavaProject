package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public interface LightSource {
    /**
     * returns the intensity given out by the light on a specific point
     * @param point
     * @return intensity
     */
    public Color getIntensity(Point3D point);

    /**
     * returns the vector direction of the light to the specific point
     * @param point
     * @return vector
     */
    public Vector getL(Point3D point);
    public Vector getD();
}
