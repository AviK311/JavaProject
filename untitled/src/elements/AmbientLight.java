package elements;

import primitives.Color;
import primitives.Point3D;

public class AmbientLight extends Light {
    /**
     * ctor with params
     * @param color
     * @param Ka
     */
    public AmbientLight(Color color, double Ka) {
        super(color.scale(Ka));
    }

}
