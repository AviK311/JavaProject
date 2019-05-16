package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class pointLight extends Light implements LightSource{
    Point3D position;
    @Override
    public Color getIntensity(Point3D point) {
        return null;
    }

    @Override
    public Vector getL(Point3D point) {
        return null;
    }
}
