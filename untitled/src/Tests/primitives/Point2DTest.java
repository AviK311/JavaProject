package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

public class Point2DTest {

    @Test
    public void getX() throws Exception {
        assertEquals(new Coordinate(1),(new Point2D(new Coordinate(1),new Coordinate(2))).getX());
    }

    @Test
    public void setX() {
        Point2D point = new Point2D(new Coordinate(1),new Coordinate(2));
        point.setX(new Coordinate(4));
        assertEquals(new Coordinate(2), point.getX());
    }

}