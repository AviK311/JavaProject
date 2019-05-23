package elements;
import primitives.Color;
import primitives.Point3D;

public abstract class Light {
    Color _color;

    /**
     * params ctor
     * @param _color
     */
    public Light(Color _color) {
        this._color = _color;
    }

    public abstract Color getIntensity();




    }
