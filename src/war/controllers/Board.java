package war.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Board implements Initializable {
    private int size = 20;

    @FXML
    private Group board;

    private Rectangle buildCell(int x, int y, int size, boolean whiteCell) {
        Rectangle rect = new Rectangle();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setWidth(size);
        rect.setHeight(size);
        if(whiteCell) {
            rect.setFill(Color.WHITE);
        } else {
            rect.setFill(Color.GRAY);
        }
        rect.setStroke(Color.BLACK);
        return rect;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int y = 0; y != size; y++) {
            for(int x = 0; x != size; x++) {
                board.getChildren().add(buildCell(x, y, 40, (x + y) % 2 == 0));
            }
        }
    }
}
