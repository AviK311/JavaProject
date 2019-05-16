package elements;


import primitives.*;

public class DirectionalLight extends Light implements LightSource {
    Vector _Direction;
    @Override
    public Color getIntensity(Point3D point) {
        return null;
    }
}
