package elements;

import primitives.Color;

public class AmbientLight {
    Color color;
    double Ka;

    public AmbientLight(Color color, double Ka) {
        this.color = color;
        this.Ka = Ka;
    }


    public Color getIntensity(){

        return color.scale(Ka);
    }
}
