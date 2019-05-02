package renderer;

import org.junit.Test;

import static org.junit.Assert.*;

public class RenderTest {

    @Test
    public void printGrid() {
        Render r=new Render();
        r.printGrid(50);
    }
}