package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adrian on 04/11/16.
 */
public class SnakeTest {
    private Snake snake;

    @Before
    public void before() throws Exception {
        snake = new Snake();
    }

    @Test
    public void addNewBlock() {
        snake.addNewBlock(new Point(5, 5));
        assertEquals(1, snake.getSnakeLength());
    }

    @Test
    public void getHeadPoint() throws Exception {
        Point point = new Point(5, 3);
        snake.addNewBlock(point);
        snake.addNewBlock(new Point(8, 3));
        snake.addNewBlock(new Point(45, 2));
        assertTrue(point.equals(snake.getHeadPoint()));
    }

    @Test
    public void getBlockPoint() throws Exception {
        Point point = new Point(5, 3);
        snake.addNewBlock(new Point(7, 21));
        snake.addNewBlock(point);
        assertTrue(snake.getBlockPoint(1).equals(point));
    }

    @Test
    public void checkCollisionWithoutHead() throws Exception {

    }

    @Test
    public void checkCollisionWithSnake() throws Exception {
        snake.addNewBlock(new Point(5, 4));
        snake.addNewBlock(new Point(4, 7));
        snake.addNewBlock(new Point(8, 1));
        assertTrue(snake.checkCollisionWithSnake(new Point(4, 7)));
    }

    @Test
    public void setDirectionToTheNorth() throws Exception {
        snake.addNewBlock(new Point(4, 4));
        snake.setNextDirectionToTheNorth();
        snake.goToNextPoint();
        assertTrue(snake.getBlockPoint(0).equals(new Point(4, 3)));
    }

    @Test
    public void setDirectionToTheSouth() throws Exception {
        snake.addNewBlock(new Point(4, 4));
        snake.setNextDirectionToTheSouth();
        snake.goToNextPoint();
        assertTrue(snake.getBlockPoint(0).equals(new Point(4, 5)));
    }

    @Test
    public void setDirectionToTheEast() throws Exception {
        snake.addNewBlock(new Point(4, 4));
        snake.setNextDirectionToTheEast();
        snake.goToNextPoint();
        assertTrue(snake.getBlockPoint(0).equals(new Point(5, 4)));
    }

    @Test
    public void setDirectionToTheWest() throws Exception {
        snake.addNewBlock(new Point(4, 4));
        snake.setNextDirectionToTheWest();
        snake.goToNextPoint();
        assertTrue(snake.getBlockPoint(0).equals(new Point(3, 4)));
    }

    @Test
    public void canSetNewDirection(){
        snake.addNewBlock(new Point(5, 5));
        snake.setNextDirectionToTheSouth();
        snake.goToNextPoint();
        snake.goToNextPoint();
        assertFalse(snake.canSetNewDirection(Direction.NORTH));
    }

    @Test
    public void getSnakeLength() throws Exception {
        snake.addNewBlock(new Point(4, 5));
        snake.addNewBlock(new Point(4, 7));
        snake.addNewBlock(new Point(4, 6));
        assertEquals(3, snake.getSnakeLength());
    }

}