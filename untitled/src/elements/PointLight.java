package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    Point3D position;
    double Kc=1,Kl=0,Kq=0;

    /**
     * params ctor
     * @param _color
     * @param position
     * @param Kc
     * @param kl
     * @param kq
     */
    public PointLight(Color _color, Point3D position, double Kc, double kl, double kq) {
        super(_color);
        this.position = position;
        this.Kc = Kc;
        Kl = kl;
        Kq = kq;
    }

    @Override
    public Color getIntensity(Point3D point) {
        double d = position.distance(point);
        Color c=_color.scale(1.0/Kc+Kl*d+Kq*d*d); // I0 / (Kc+Kl*d+Kq*d*d)
        return c;
    }

    @Override
    public Vector getL(Point3D point) {
        return position.subtract(point);
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
