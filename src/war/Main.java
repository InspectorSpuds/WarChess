package war;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent initial = FXMLLoader.load(Main.class.getResource("UI/Splash.fxml"));

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WarChess");
        this.primaryStage.centerOnScreen();
        this.primaryStage.setScene(new Scene(initial));
        this.primaryStage.show();

        changeScene("UI/Entry.fxml");
    }

    public static void changeScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxml));
        primaryStage.getScene().setRoot(root);
    }
}
