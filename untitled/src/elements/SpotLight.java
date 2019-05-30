package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {
    private Vector direction;

    /**
     * params ctor
     * @param _color
     * @param position
     * @param kl
     * @param kq
     * @param direction
     */
    public SpotLight(Color _color, Point3D position, double kl, double kq, Vector direction) {
        super(_color, position,  kl, kq);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point3D point) {
        double dl = direction.dotProduct(getL(point)); // D*L
        if (dl<0)
            return Color.BLACK;
        Color c = super.getIntensity(point).scale(dl); // [I0(D*L)] / (Kc+Kl*d+Kq*d*d)
        return c;
    }



}
