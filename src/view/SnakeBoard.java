package view;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Point;
import model.SnakeFoods;

import java.util.Random;


/**
 * Created by adrian on 07/11/16.
 */
public class SnakeBoard extends Canvas {
    private final static int GRID_WIDTH = 10;
    private final static int GRID_HEIGHT = 10;
    private final static int BORDER_WIDTH = 2;
    private Point boardSize;
    private Color color = Color.GREEN;
    private GraphicsContext graphicsContext = this.getGraphicsContext2D();

    {
        graphicsContext.setFill(color);
        graphicsContext.setStroke(color);

        this.requestFocusOnMouseClick();
    }

    public SnakeBoard(Point point) {
        this.boardSize = point;
        this.setWidthAndHeight();
        this.drawBorder();

    }

    public Point generateRandomPointOnBoard() {
        int boardX = boardSize.getX();
        int boardY = boardSize.getY();
        Random random = new Random();
        return new Point(random.nextInt(boardX), random.nextInt(boardY));
    }

    public boolean isPointOutOfBoard(Point point) {
        if (point.getX() >= this.boardSize.getX()
                || point.getX() < 0
                || point.getY() >= this.boardSize.getY()
                || point.getY() < 0
                )
            return true;
        return false;
    }


    private void setWidthAndHeight() {
        this.setWidth(boardSize.getX() * GRID_WIDTH + BORDER_WIDTH * 2);
        this.setHeight(boardSize.getY() * GRID_HEIGHT + BORDER_WIDTH * 2);
    }

    private void requestFocusOnMouseClick() {
        this.addEventHandler(MouseEvent.MOUSE_PRESSED,
                (EventHandler<MouseEvent>) e ->
                        this.requestFocus()
        );
    }

    public void drawBorder() {
        graphicsContext.setLineWidth(0);
        graphicsContext.setStroke(Color.RED);
        graphicsContext.moveTo(0, 0);
        graphicsContext.lineTo(0, this.getHeight());
        graphicsContext.lineTo(this.getWidth(), this.getHeight());
        graphicsContext.lineTo(this.getWidth(), 0);
        graphicsContext.lineTo(0, 0);
        graphicsContext.stroke();
    }

    public void drawRects(Point... points) {
        for (int i = 0; i < points.length; i++) {
            drawRect(points[i]);
        }
    }

    public void drawRect(Point point) {
        graphicsContext.fillOval(point.getX() * GRID_WIDTH + BORDER_WIDTH, point.getY() * GRID_HEIGHT + BORDER_WIDTH, GRID_WIDTH, GRID_HEIGHT);
    }

    public void clearRects(Point... points) {
        for (int i = 0; i < points.length; i++) {
            clearRect(points[i]);
        }
    }

    public void clearRect(Point point) {
        graphicsContext.clearRect(point.getX() * GRID_WIDTH + BORDER_WIDTH, point.getY() * GRID_HEIGHT + BORDER_WIDTH, GRID_WIDTH, GRID_HEIGHT);
    }

    public void clearBoard() {
        graphicsContext.clearRect(0, 0, (int) this.getWidth(), (int) this.getHeight());
    }

    public Point getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(Point boardSize) {
        this.boardSize = boardSize;
    }

}
