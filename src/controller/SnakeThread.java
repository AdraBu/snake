package controller;

/**
 * Created by adrian on 08/11/16.
 */
public class SnakeThread extends Thread {
    private SnakeManager snakeManager;
    private int speedGame = 50;
    private int timeToNextStep = 20;

    public SnakeThread(SnakeManager snakeManager) {
        this.snakeManager = snakeManager;
    }

    @Override
    public void run() {
        timeToNextStep = timeToNextStep();
        while (!snakeManager.isGameOver()) {
            snakeManager.drawSnakeToNextPoint();
            snakeManager.pickUpFoods();
            try {
                Thread.sleep(timeToNextStep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private int timeToNextStep() {
        return 1000 / speedGame;
    }

    public int getSpeedGame() {
        return speedGame;
    }

    public void setSpeedGame(int speedGame) {
        this.speedGame = speedGame;
    }
}
