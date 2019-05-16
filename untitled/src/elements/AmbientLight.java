package elements;

import primitives.Color;
import primitives.Point3D;

public class AmbientLight extends Light {
    Color color;
    double Ka;

    public AmbientLight(Color color, double Ka) {
        this.color = color;
        this.Ka = Ka;
    }
    @Override
    public Color getIntensity() {
        return color.scale(Ka);
    }
}
