package primitives;
import static primitives.Util.*;

public final class Coordinate {
    //private static final double EPSILON = 0.0000001;
    protected double _coord;

    public static Coordinate ZERO = new Coordinate(0.0);

    /********** Constructors ***********/
    /**
     * ctor with double
     * @param coord
     */
    public Coordinate(double coord) {
        // if it too close to zero make it zero
        _coord = alignZero(coord);
    }

    /**
     * copy ctor
     * @param other
     */
    public Coordinate(Coordinate other) {
        _coord = other._coord;
    }

    /************** Getters/Setters *******/
    /**
     *
     * @return coordinate
     */
    public double get() {
        return _coord;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
        return usubtract(_coord, ((Coordinate)obj)._coord) == 0.0;
    }

    @Override
    public String toString() {
        return "" + _coord;
    }

    /************** Operations ***************/
    /**
     * subtract other coordinate from this
     * @param other
     * @return return new coordinate
     */
    public Coordinate subtract(Coordinate other) {
        return new Coordinate(usubtract(_coord, other._coord));
    }

    /**
     * add two coordinates
     * @param other to add to this
     * @return new coordinate
     */
    public Coordinate add(Coordinate other) {
        return new Coordinate(uadd(_coord, other._coord));
    }

    /**
     * scales a coordinate
     * @param num value to scale by
     * @return scaled coordinate
     */
    public Coordinate scale(double num) {
        return new Coordinate(uscale(_coord, num));
    }

    public Coordinate multiply(Coordinate other) {
        return new Coordinate(uscale(_coord, other._coord));
    }

}
