package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Direction;
import model.Snake;

/**
 * Created by adrian on 08/11/16.
 */
public class SnakeKeyHandler implements EventHandler<KeyEvent> {
    private Snake snake;

    public SnakeKeyHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                if (snake.canSetNewDirection(Direction.WEST))
                    snake.setNextDirectionToTheWest();
                break;
            case D:
                if (snake.canSetNewDirection(Direction.EAST))
                    snake.setNextDirectionToTheEast();
                break;
            case S:
                if (snake.canSetNewDirection(Direction.SOUTH))
                    snake.setNextDirectionToTheSouth();
                break;
            case W:
                if (snake.canSetNewDirection(Direction.NORTH))
                    snake.setNextDirectionToTheNorth();
                break;
        }
    }
}
