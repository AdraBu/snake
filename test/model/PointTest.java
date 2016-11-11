package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adrian on 10/11/16.
 */
public class PointTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addPointToPoint() throws Exception {
        Point point = Point.addPointToPoint(new Point(1, 1), new Point(3, 5));
        assertTrue(point.equals(new Point(4, 6)));
    }

    @Test
    public void equals() throws Exception {
        assertTrue(new Point(5, 5).equals(new Point(5, 5)));
    }

}