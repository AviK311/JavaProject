package elements;

import primitives.Color;
import primitives.Point3D;

public class AmbientLight extends Light {
    double Ka;

    public AmbientLight(Color color, double Ka) {
        super(color);
        this.Ka = Ka;
    }
    @Override
    public Color getIntensity() {
        return _color.scale(Ka);
    }
}
