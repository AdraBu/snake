package model;

import java.util.ArrayList;

/**
 * Created by adrian on 10/11/16.
 */
public class SnakeFoods {
    private ArrayList<Point> foodsPoint = new ArrayList<>();

    public void addFood(Point point) throws IllegalArgumentException {
        for (Point foodPoint : foodsPoint) {
            if (foodPoint.equals(point)) {
                throw new IllegalArgumentException("Current point is occupied");
            }
        }
        foodsPoint.add(point);
    }

    public void setFoodPoint(int index, Point point) {
        foodsPoint.set(index, point);
    }

    public boolean isFoodInPoint(Point point) {
        boolean isFound = false;
        for (Point foodPoint : foodsPoint) {
            if (foodPoint.equals(point)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public Point[] toArray() {
        return foodsPoint.toArray(new Point[foodsPoint.size()]);

    }

    public Point getFoodPoint(int index) {
        return foodsPoint.get(index);
    }

    public int getAmountOfFood() {
        return foodsPoint.size();
    }

    public void clearAll(){
        foodsPoint.clear();
    }

    @Override
    public String toString() {
        return foodsPoint.toString();
    }
}
