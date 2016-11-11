package controller;


import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Point;
import model.Snake;
import model.SnakeFoods;
import robot.SnakeRobot;
import view.SnakeBoard;


/**
 * Created by adrian on 08/11/16.
 */
public class SnakeManager {
    private Snake snake;
    private SnakeBoard snakeBoard;
    private SnakeThread snakeThread;
    private SnakeFoods snakeFoods = new SnakeFoods();
    private SnakeRobot snakeRobot = new SnakeRobot(this);
    private boolean runBoot = false;

    public SnakeManager(Snake snake, SnakeBoard snakeBoard) {
        this.snake = snake;
        this.snakeBoard = snakeBoard;

        this.setKeyHandler(new SnakeKeyHandler(snake));
    }


    public void startGame(int speedGame, int foodsNumber, boolean runBoot) {
        this.runBoot = runBoot;
        initialDrawFoods(foodsNumber);
        snakeBoard.requestFocus();
        snakeThread = new SnakeThread(this);
        snakeThread.setSpeedGame(speedGame);
        snakeThread.start();

    }

    public void drawSnake() {
        for (int i = 0; i < snake.getSnakeLength(); i++) {
            snakeBoard.drawRect(snake.getBlockPoint(i));
        }
    }

    public void drawSnakeToNextPoint() {
        if (runBoot)
            snakeRobot.pressNextKey();

        snakeBoard.clearRect(snake.getBlockPoint(snake.getSnakeLength() - 1));
        snake.goToNextPoint();
        snakeBoard.drawRect(snake.getHeadPoint());
    }

    public boolean isGameOver() {
        boolean isOver = false;
        isOver = snakeBoard.isPointOutOfBoard(snake.getHeadPoint())
                || snake.checkCollisionWithoutHead(snake.getHeadPoint());

        return isOver;
    }

    public void pickUpFoods() {
        for (int i = 0; i < snakeFoods.getAmountOfFood(); i++) {
            if (snake.getHeadPoint().equals(snakeFoods.getFoodPoint(i))) {
                snake.addNewBlock(snake.getBlockPoint(snake.getSnakeLength() - 1));
                snakeFoods.setFoodPoint(i, this.generatePointWithoutConflict());
                this.drawFood(i);
            }
        }
    }

    private void initialDrawFoods(int count) {
        for (int i = 0; i < count; i++) {
            snakeFoods.addFood(generatePointWithoutConflict());
        }
        drawFoods();
    }

    private Point generatePointWithoutConflict() {
        Point generatedPoint = snakeBoard.generateRandomPointOnBoard();
        while (snakeFoods.isFoodInPoint(generatedPoint)) {
            generatedPoint = snakeBoard.generateRandomPointOnBoard();
        }
        return generatedPoint;
    }

    private void drawFoods() {
        snakeBoard.drawRects(snakeFoods.toArray());
    }

    private void drawFood(int index) {
        snakeBoard.drawRect(snakeFoods.getFoodPoint(index));
    }

    private void setKeyHandler(EventHandler<KeyEvent> handler) {
        snakeBoard.setOnKeyPressed(handler);
    }

    public void clearFood() {
        snakeFoods.clearAll();
    }

    public Snake getSnake() {
        return snake;
    }

    public SnakeFoods getSnakeFoods() {
        return snakeFoods;
    }

    public SnakeBoard getSnakeBoard() {
        return snakeBoard;
    }


}
