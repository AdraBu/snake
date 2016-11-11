package model;

/**
 * Created by adrian on 03/11/16.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point addPointToPoint(Point point1, Point point2) {
        return new Point(point1.getX() + point2.getX(), point1.getY() + point2.getY());
    }

    public static Point subtract(Point point1, Point point2) {
        return new Point(point1.getX() - point2.getX(), point1.getY() - point2.getY());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Point point) {
        return (this.getX() == point.getX() && this.getY() == point.getY());
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
