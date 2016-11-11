package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import model.Point;
import model.Snake;
import view.SnakeBoard;

import java.net.URL;
import java.util.ResourceBundle;


public class MainGUI {
    private SnakeManager snakeManager;
    private int initSnakeLength = 3;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedLabel;
    @FXML
    private Label foodLabel;
    @FXML
    private Slider foodSlider;
    @FXML
    private CheckBox bootCheckBox;

    @FXML
    void startGame(ActionEvent event) {
        resetSnake();
        snakeManager.startGame(((int) speedSlider.getValue()), ((int) foodSlider.getValue()), bootCheckBox.isSelected());
    }

    @FXML
    void initialize() {
        snakeManager = new SnakeManager(new Snake(), new SnakeBoard(new Point(50, 50)));
        borderPane.setCenter(snakeManager.getSnakeBoard());

        initFoodSlider();
        initSpeedSlider();
        resetSnake();
    }

    private void resetSnake() {
        this.clearAll();

        for (int i = initSnakeLength; i > 0; i--) {
            snakeManager.getSnake().addNewBlock(new Point(2 + i, 5));
        }
        snakeManager.getSnake().resetCurrentDirection();
        snakeManager.drawSnake();
    }

    private void initFoodSlider() {
        foodLabel.setText("Food in board: " + ((int) foodSlider.getValue()) + "");
        foodSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                foodLabel.setText("Food in board: " + newValue.intValue() + "")
        );
    }

    private void initSpeedSlider() {
        speedLabel.setText("Speed " + ((int) speedSlider.getValue()) + " [step/s]");
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                speedLabel.setText("Speed " + newValue.intValue() + " [step/s]")
        );

    }

    private void clearAll() {
        snakeManager.getSnake().clearSnake();
        snakeManager.clearFood();
        snakeManager.getSnakeBoard().clearBoard();
        snakeManager.getSnakeBoard().drawBorder();
    }

}
