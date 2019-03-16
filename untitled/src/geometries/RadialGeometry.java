package geometries;

public class RadialGeometry {
    float radius;

    public RadialGeometry(float radius) {
        this.radius = radius;
    }
    public RadialGeometry(RadialGeometry other) {
        this.radius = other.radius;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
