package elements;


import primitives.*;

public class DirectionalLight extends Light implements LightSource {
    Vector _Direction;

    /**
     * params ctor
     * @param _color
     * @param _Direction
     */
    public DirectionalLight(Color _color, Vector _Direction) {
        super(_color);
        this._Direction = _Direction;
    }

    @Override
    public Color getIntensity(Point3D point) {
        return _color;
    }

    @Override
    public Vector getL(Point3D point) {
        return _Direction;
    }


    @Override
    public Vector getD() {
        return null;
    }

    @Override
    public Color getIntensity() {
        return null;
    }
}

