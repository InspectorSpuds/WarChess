package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public final String MAIN_GUI = "UI/MainContent.fxml"; //Main UI file path
    public final String ENTRY_GUI = "UI/EntryScreen.fxml"; //Entry Screen UI file path
    public final int MIN_HEIGHT = 400; //Min window Height
    public final int MIN_WIDTH = 400; //Min window width

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_GUI));
        primaryStage.setTitle("War Chess");
        primaryStage.setScene(new Scene(root, MIN_WIDTH, MIN_HEIGHT));
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(Main.class, args);
    }

    public void progressFlow() {

    }
}
