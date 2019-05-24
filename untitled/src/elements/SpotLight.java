package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {
    Vector direction;

    /**
     * params ctor
     * @param _color
     * @param position
     * @param _Kc
     * @param kl
     * @param kq
     * @param direction
     */
    public SpotLight(Color _color, Point3D position, double _Kc, double kl, double kq, Vector direction) {
        super(_color, position, _Kc, kl, kq);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point3D point) {
        double dl = getD().normalize().dotProduct(getL(point).normalize()); // D*L
        if (dl<0)
            dl=0;
        Color c = super.getIntensity(point).scale( dl); // [I0(D*L)] / (Kc+Kl*d+Kq*d*d)
        return c;
    }

    @Override
    public Vector getD() {
        return direction;
    }
}
