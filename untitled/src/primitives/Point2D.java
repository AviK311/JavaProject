package primitives;

import java.util.Objects;

public class Point2D {
    protected Coordinate x;
    protected Coordinate y;

    public Point2D(Coordinate x, Coordinate y) {
        this.x = x;
        this.y = y;
    }
    public Point2D(double x, double y) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
    }
    public Point2D(Point2D other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Coordinate getX() {
        return x;
    }

    public void setX(Coordinate x) {
        this.x = x;
    }

    public Coordinate getY() {
        return y;
    }

    public void setY(Coordinate y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2D)) return false;
        Point2D point2D = (Point2D) o;
        return Objects.equals(getX(), point2D.getX()) &&
                Objects.equals(getY(), point2D.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "P2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
