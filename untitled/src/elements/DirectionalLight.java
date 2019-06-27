package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {
    Vector _Direction;

    /**
     * params ctor
     *
     * @param _color
     * @param _Direction
     */
    public DirectionalLight(Color _color, Vector _Direction) {
        super(_color);
        this._Direction = _Direction.normalize();
    }

    @Override
    public Color getIntensity(Point3D point) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point3D point) {
        return _Direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.MAX_VALUE;
    }
}

