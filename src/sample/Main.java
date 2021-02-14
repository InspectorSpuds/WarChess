package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final String MAINUI_PATH = "UI/MainUI.fxml";
    private final String ENTRYUI_PATH = "UI/EntryScreen.fxml";
    private final int MIN_HEIGHT = 600;
    private final int MIN_WIDTH = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(MAINUI_PATH));
        primaryStage.setTitle("WarChess");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(Main.class, args);
    }
}
