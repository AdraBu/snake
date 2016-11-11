package view;

import model.Point;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adrian on 11/11/16.
 */
public class SnakeBoardTest {
    private SnakeBoard snakeBoard;
    private Point boardSize = new Point(10, 10);

    @Before
    public void setUp() throws Exception {
        snakeBoard = new SnakeBoard(boardSize);
    }

    @Test
    public void generateRandomPointOnBoard() throws Exception {
        for (int i = 0; i < 80; i++) {
            Point randomPoint = snakeBoard.generateRandomPointOnBoard();
            assertFalse(snakeBoard.isPointOutOfBoard(randomPoint));
        }
    }

    @Test
    public void isPointOutOfBoard() throws Exception {
        Point point = Point.addPointToPoint(boardSize, new Point(1, 1));
        assertTrue(snakeBoard.isPointOutOfBoard(point));
    }

}