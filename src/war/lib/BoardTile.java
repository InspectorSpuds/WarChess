package war.lib;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import war.controllers.Board;

public class BoardTile extends Rectangle {
    TilePiecesBridge bridge;

    public BoardTile(Group board) {
        bridge.tile = this;

        this.setOnMouseEntered(event -> {
            this.setFill(Color.LIGHTGRAY);
        });
        this.setOnMouseExited(event -> {
            this.setFill(Color.WHITE);
        });
        this.setOnMouseClicked(event -> {
            bridge.pieces.showPieces();
        });

        this.setOnDragEntered(event -> {
            this.setFill(Color.GREEN);
        });
        this.setOnDragExited(event -> {
            this.setFill(Color.WHITE);
        });
        this.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            board.getChildren().add(buildFigure((int) (getX() * 25 + 5), (int) (getY() * 25 + 5), 60, db.getImage()));
        });
    }

    private Rectangle buildFigure(int x, int y, int size, Image image) {
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(size);
        rect.setHeight(size);
        rect.setFill(new ImagePattern(image));
        return rect;
    }
}
