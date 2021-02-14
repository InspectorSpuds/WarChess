package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
  private final static String MAINUI_PATH = "UI/MainUI.fxml";
  private final static String ENTRYUI_PATH = "UI/EntryScreen.fxml";
  private static final int MIN_HEIGHT = 600;
  private static final int MIN_WIDTH = 600;
  private static Stage primary;
  public static Stage popup = new Stage();

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setOnCloseRequest(t-> popup.close());
    URL url = Main.class.getResource(ENTRYUI_PATH);
    Parent root = FXMLLoader.load(url);
    primaryStage.setTitle("War Chess");
    primaryStage.setScene(new Scene(root, 600, 600));
    primaryStage.setMinHeight(MIN_HEIGHT);
    primaryStage.setMinWidth(MIN_WIDTH);
    primary = primaryStage;
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(Main.class, args);
  }

  public static void resetStage(String message) throws Exception {
    primary.close();
    Label str = new Label(message);
    str.setFont(Font.font(20));
    BorderPane root = new BorderPane();
    root.setCenter(str);
    startScreen();
    popup.setTitle("War Chess");
    popup.setScene(new Scene(root, 600, 600));
    popup.setMinHeight(MIN_HEIGHT);
    popup.setMinWidth(MIN_WIDTH);
    popup.show();
  }

  public static void start() throws IOException {
    URL url = Main.class.getResource(MAINUI_PATH);
    Parent root = FXMLLoader.load(url);
    primary.setTitle("War Chess");
    primary.setScene(new Scene(root, 600, 600));
    primary.setMinHeight(MIN_HEIGHT);
    primary.setMinWidth(MIN_WIDTH);
    primary.show();
  }

  public static void startScreen() throws Exception {
    URL url = Main.class.getResource(ENTRYUI_PATH);
    Parent root = FXMLLoader.load(url);
    primary.setTitle("War Chess");
    primary.setScene(new Scene(root, 600, 600));
    primary.setMinHeight(MIN_HEIGHT);
    primary.setMinWidth(MIN_WIDTH);
    primary.show();
  }
}
