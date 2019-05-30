package elements;
import primitives.Color;
import primitives.Point3D;

public abstract class Light {
    private Color _color;

    /**
     * params ctor
     * @param _color
     */
    public Light(Color _color) {
        this._color = _color;
    }

    public  Color getIntensity(){
        return _color;
    };




    }
