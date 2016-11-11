package robot;

import controller.SnakeManager;
import model.Point;
import model.Snake;
import org.junit.Before;
import org.junit.Test;
import view.SnakeBoard;

import static org.junit.Assert.*;


/**
 * Created by adrian on 11/11/16.
 */
public class SnakeRobotTest {
    private SnakeRobot snakeRobot;
    private SnakeManager snakeManager;

    @Before
    public void setUp() throws Exception {
        snakeManager = new SnakeManager(new Snake(), new SnakeBoard(new Point(50, 50)));
        snakeManager.getSnake().addNewBlock(new Point(10, 10));

        snakeManager.getSnakeFoods().addFood(new Point(5, 4));
        snakeManager.getSnakeFoods().addFood(new Point(11, 11));
        snakeManager.getSnakeFoods().addFood(new Point(9, 9));
        snakeManager.getSnakeFoods().addFood(new Point(25, 34));

        snakeRobot = new SnakeRobot(snakeManager);
    }

    @Test
    public void getNearestPoint() {
        Point point = snakeRobot.getNearestPoint();
        assertTrue(point.equals(new Point(11, 11)));
    }


}