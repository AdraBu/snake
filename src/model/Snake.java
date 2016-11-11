package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by adrian on 03/11/16.
 */
public class Snake {
    ArrayList<Point> blocks = new ArrayList<>();
    private Direction currentDirection = Direction.EAST;
    private Direction nextDirection = currentDirection;

    public void addNewBlock(Point point) {
        blocks.add(point);
    }

    public Point getHeadPoint() {
        return getBlockPoint(0);
    }

    public Point getBlockPoint(int index) {
        return blocks.get(index);
    }

    public boolean checkCollisionWithoutHead(Point point) {
        boolean isCollision = false;
        for (int i = 1; i < blocks.size(); i++) {
            if (blocks.get(i).equals(point)) {
                isCollision = true;
                break;
            }
        }
        return isCollision;
    }

    public boolean checkCollisionWithSnake(Point point) {
        boolean isCollision = false;
        for (Point blockPoint : blocks) {
            if (blockPoint.equals(point)) {
                isCollision = true;
                break;
            }
        }
        return isCollision;
    }

    public void goToNextPoint() {
        currentDirection = nextDirection;
        switch (currentDirection) {
            case NORTH:
                moveToDirection(new Point(0, -1));
                break;
            case SOUTH:
                moveToDirection(new Point(0, 1));
                break;
            case EAST:
                moveToDirection(new Point(1, 0));
                break;
            case WEST:
                moveToDirection(new Point(-1, 0));
                break;
            default:
                throw new IllegalStateException("Unknown direction");
        }
    }

    public void setNextDirectionToTheNorth() {
        nextDirection = Direction.NORTH;
    }

    public void setNextDirectionToTheSouth() {
        nextDirection = Direction.SOUTH;
    }

    public void setNextDirectionToTheEast() {
        nextDirection = Direction.EAST;
    }

    public void setNextDirectionToTheWest() {
        nextDirection = Direction.WEST;
    }

    public boolean canSetNewDirection(Direction direction) {
        boolean canSet = true;
        switch (direction) {
            case NORTH:
                if (currentDirection == Direction.SOUTH)
                    canSet = false;
                break;
            case SOUTH:
                if (currentDirection == Direction.NORTH)
                    canSet = false;
                break;
            case EAST:
                if (currentDirection == Direction.WEST)
                    canSet = false;
                break;
            case WEST:
                if (currentDirection == Direction.EAST)
                    canSet = false;
                break;
            default:
                throw new IllegalStateException("Unknown direction");
        }
        return canSet;
    }

    public void clearSnake() {
        blocks.clear();
    }

    private void moveToDirection(Point point) {
        blocks.add(0, Point.addPointToPoint(blocks.get(0), point));
        blocks.remove(blocks.size() - 1);
    }

    public int getSnakeLength() {
        return blocks.size();
    }

    public void resetCurrentDirection() {
        currentDirection = Direction.EAST;
        nextDirection = currentDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public String toString() {
        return blocks.toString();
    }
}