package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public interface LightSource {
    /**
     * returns the intensity given out by the light on a specific point
     *
     * @param point
     * @return intensity
     */
    Color getIntensity(Point3D point);

    /**
     * returns the vector direction of the light to the specific point
     *
     * @param point
     * @return vector
     */
    Vector getL(Point3D point);

    /**
     * gets distance between light and point
     * @param point
     * @return
     */
    double getDistance(Point3D point);


}
