package war.lib;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class BoardPiece extends ImageView {
    TilePiecesBridge bridge;

    public BoardPiece() {
        bridge.piece = this;

        this.setOnDragDetected(event -> {
            System.out.println("drag detected");
            Dragboard db = this.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putImage(this.getImage());
            db.setContent(content);

            event.consume();
        });

        this.setOnDragDone(event -> {
            System.out.println("drag done");
            bridge.pieces.hidePieces();
            event.consume();
        });
    }
}
