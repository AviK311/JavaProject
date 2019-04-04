package primitives;

public class Ray {
    private Vector direction;
    private Point3D head;

    /**
     * ctor with params
     * @param direction
     * @param head
     */
    public Ray(Vector direction, Point3D head) {
        this.direction = direction.normalize();
        this.head = head;
    }

    /**
     * copy ctor
     * @param other
     */
    public Ray(Ray other) {
        this.direction = other.getDirection();
        this.head = other.getHead();
    }

    /**
     *
     * @return direction vector
     */
    public Vector getDirection() {
        return direction;
    }

//    public void setDirection(Vector direction) {
//        this.direction = direction;
//    }

    /**
     * @return head point
     */
    public Point3D getHead() {
        return head;
    }

//    public void setHead(Point3D head) {
//        this.head = head;
//    }

    /**
     * equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getHead().equals(ray.getHead()) && getDirection().equals(ray.getDirection());
    }

    @Override
    public String toString() {
        return "R{" +
                "h=" + head +
                ", dir=" + direction +
                '}';
    }
}
