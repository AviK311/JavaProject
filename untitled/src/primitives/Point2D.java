package primitives;

import java.util.Objects;

public class Point2D {
    protected Coordinate x;
    protected Coordinate y;

    /**
     * ctor with coordinate params
     * @param x
     * @param y
     */
    public Point2D(Coordinate x, Coordinate y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ctor with double params
     * @param x
     * @param y
     */
    public Point2D(double x, double y) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
    }

    /**
     * copy ctor
     * @param other
     */
    public Point2D(Point2D other) {
        this.x = new Coordinate(other.x);
        this.y = new Coordinate(other.y);
    }

    /**
     *
     * @return coordinate x
     */
    public Coordinate getX() {
        return x;
    }

//    public void setX(Coordinate x) {
//        this.x = x;
//    }

    /**
     *
     * @return coordinate y
     */
    public Coordinate getY() {
        return y;
    }

//    public void setY(Coordinate y) {
//        this.y = y;
//    }



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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Objects.equals(x, point2D.x) &&
                Objects.equals(y, point2D.y);
    }
}
