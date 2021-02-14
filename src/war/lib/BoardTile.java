package war.lib;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardTile extends Rectangle {
    TilePiecesBridge bridge;
    private int row;
    private int col;

    public BoardTile(int row, int col) {
        bridge.boardTile = this;
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
        this.row = row;
        this.col = col;
        this.resetColor();

        this.setOnMouseEntered(event -> {
            this.setFill(Color.LIGHTGRAY);
        });
        this.setOnMouseExited(event -> {
            this.resetColor();
        });
        this.setOnMouseClicked(event -> {
            bridge.pieces.showPieces();
        });
    }

    public void resetColor() {
        if ((this.row + this.col) % 2 == 1) {
            this.setFill(Color.GRAY);
        } else {
            this.setFill(Color.WHITE);
        }
    }
}
