package elements;

import primitives.Color;

public class AmbientLight extends Light {
    /**
     * ctor with params
     *
     * @param color
     * @param Ka
     */
    public AmbientLight(Color color, double Ka) {
        super(color.scale(Ka));
    }
}
