import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static int SCREEN_HEIGHT_MIN = 510;
    private static int SCREEN_WIDTH_MIN = 660;

    public static void main(String[] var) {
        launch(var);
    }

    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Snake bot");
        Parent var2 = FXMLLoader.load(this.getClass().getResource("view/index.fxml"));
        primaryStage.setScene(new Scene(var2, SCREEN_WIDTH_MIN, SCREEN_HEIGHT_MIN));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest((event) -> {
            System.exit(0);
        });
    }
}
