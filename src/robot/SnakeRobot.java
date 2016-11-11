package robot;

import controller.SnakeManager;

import java.awt.*;
import java.awt.event.KeyEvent;

import model.Direction;
import model.Point;

/**
 * Created by adrian on 11/11/16.
 */
public class SnakeRobot {
    private Robot robot;
    private SnakeManager snakeManager;
    private Direction directionX = Direction.EAST;
    private Direction directionY = Direction.SOUTH;
    private Point nearestPoint;

    public SnakeRobot(SnakeManager snakeManager) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        this.snakeManager = snakeManager;
    }

    public void pressNextKey() {
        nearestPoint = getNearestPoint();
        if (moveAlongInAxeX()) {
            setDirectionOnX();
            if (snakeManager.getSnake().canSetNewDirection(directionX)) {
                setDirectionOnXAndPressKey();
            } else {
                setDirectionOnYAndPressKey();
            }
        } else {
            setDirectionOnYAndPressKey();
        }

    }


    public boolean moveAlongInAxeX() {
        return !(nearestPoint.getX() - snakeManager.getSnake().getHeadPoint().getX() == 0);
    }

    public boolean moveAlongInAxeY() {
        return !(nearestPoint.getY() - snakeManager.getSnake().getHeadPoint().getY() == 0);
    }

    public void setDirectionOnXAndPressKey() {
        this.setDirectionOnX();
        switch (directionX) {
            case EAST:
                this.pressAndReleaseKey(KeyEvent.VK_D);
                break;
            case WEST:
                this.pressAndReleaseKey(KeyEvent.VK_A);
                break;
        }
    }

    public void setDirectionOnYAndPressKey() {
        this.setDirectionOnY();
        switch (directionY) {
            case NORTH:
                this.pressAndReleaseKey(KeyEvent.VK_W);
                break;
            case SOUTH:
                this.pressAndReleaseKey(KeyEvent.VK_S);
                break;
        }
    }

    private void setDirectionOnX() {
        if (nearestPoint.getX() - snakeManager.getSnake().getHeadPoint().getX() > 0) {
            directionX = Direction.EAST;
        } else if (nearestPoint.getX() - snakeManager.getSnake().getHeadPoint().getX() < 0) {
            directionX = Direction.WEST;
        }
    }

    private void setDirectionOnY() {
        if (nearestPoint.getY() - snakeManager.getSnake().getHeadPoint().getY() > 0) {
            directionY = Direction.SOUTH;
        } else if (nearestPoint.getY() - snakeManager.getSnake().getHeadPoint().getY() < 0) {
            directionY = Direction.NORTH;
        }
    }


    public Point getNearestPoint() {
        Point[] foodsPoint = snakeManager.getSnakeFoods().toArray();
        Point snakeHeadPosition = snakeManager.getSnake().getHeadPoint();
        Point[] pointsAfterSubtract = new Point[foodsPoint.length];
        for (int i = 0; i < foodsPoint.length; i++) {
            pointsAfterSubtract[i] = Point.subtract(snakeHeadPosition, foodsPoint[i]);
            pointsAfterSubtract[i] = new Point(Math.abs(pointsAfterSubtract[i].getX()), Math.abs(pointsAfterSubtract[i].getY()));
        }
        int[] distances = new int[pointsAfterSubtract.length];
        for (int i = 0; i < pointsAfterSubtract.length; i++) {
            distances[i] = pointsAfterSubtract[i].getX() + pointsAfterSubtract[i].getY();
        }
        int minIndex = 0;
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] < distances[minIndex]) {
                minIndex = i;
            }
        }

        return snakeManager.getSnakeFoods().getFoodPoint(minIndex);
    }

    private void pressAndReleaseKey(int key) {
        robot.keyPress(key);
        robot.keyRelease(key);
    }
}
