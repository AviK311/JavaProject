package primitives;

public class Ray {
    private Vector direction;
    private Point3D head;

    public Ray(Vector direction, Point3D head) {
        this.direction = direction;
        this.head = head;
    }

    public Ray(Ray other) {
        this.direction = other.direction;
        this.head = other.head;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public Point3D getHead() {
        return head;
    }

    public void setHead(Point3D head) {
        this.head = head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getHead().equals(ray.getHead()) && getDirection().equals(ray.getDirection());
    }

    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }
}
