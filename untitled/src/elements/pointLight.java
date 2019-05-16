package elements;

import primitives.Color;
import primitives.Point3D;

public class pointLight extends Light implements LightSource{
    Point3D position;
    @Override
    public Color getIntensity(Point3D point) {
        return null;
    }
}
