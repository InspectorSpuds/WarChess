package war.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import war.Figure;
import war.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class Board implements Initializable {
    private int size = 12;
    private int cellSize = 40;
    private int figureSize = 30;

    @FXML
    private Group board;

    private Rectangle buildCell(int x, int y, int size) {
        Rectangle rect = new Rectangle();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setWidth(size);
        rect.setHeight(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    private Rectangle buildFigure(int x, int y, int size, String image) {
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(size);
        rect.setWidth(size);

        Image img = new Image(Main.getResource(image));
        rect.setFill(new ImagePattern(img));

        final Rectangle tmp = new Rectangle(x, y);
        rect.setOnDragDetected(event -> {
            tmp.setX(event.getX());
            tmp.setY(event.getY());
        });
        rect.setOnMouseDragged(event -> {
            rect.setX(event.getX() - size / 2);
            rect.setY(event.getY() - size / 2);
        });
        rect.setOnMouseReleased(event -> {
            // implement distinct logic here...
            rect.setX((event.getX() / cellSize) * cellSize + 5);
            rect.setY((event.getY() / cellSize) * cellSize + 5);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int y = 0; y != size; y++) {
            for(int x = 0; x != size; x++) {
                board.getChildren().add(buildCell(x, y, cellSize));
            }
        }
    }

    /* mockup */
    private void add(Figure figure, Group grid) {
        Cell position = figure.getPosition();
        grid.getChildren().add(
                buildFigure(position.getX() * cellSize + 5, position.getY() * cellSize + 5, figureSize, figure.icon())
        );
    }
}
