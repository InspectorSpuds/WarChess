package com.almasb.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class TicTacToeApp extends Application {

    // flag so that you can't insert the same letter 2 times
    // in a row
    private boolean turnX = true;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);
            }
        }

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    // tictactoe has a 3x3 grid and each square is a "tile"
    private class Tile extends StackPane {
        private Text text = new Text();

        public Tile() {
            // create border
            Rectangle border = new Rectangle(200, 200);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            text.setFont(Font.font(72));
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (!turnX) {

                        return; // defensive programming style
                    }
                    drawX();
                    // change values
                    turnX = false; // now not turn X

                } else if (event.getButton() == MouseButton.SECONDARY) {

                    if (turnX) {

                        return;
                    }
                    drawO();
                    turnX = true;
                }
            });
        }

        private void drawX() {
            text.setText("X");
        }

        private void drawO() {
            text.setText("O");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
