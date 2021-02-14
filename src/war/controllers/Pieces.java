package war.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import war.Main;
import war.lib.BoardPiece;
import war.lib.TilePiecesBridge;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class Pieces implements Initializable {
    @FXML
    private ScrollPane sp;

    @FXML
    private VBox vb;

    TilePiecesBridge bridge;

    public Pieces() {
        bridge.pieces = this;
    }

    public void showPieces() {
        sp.setVisible(true);
    }

    public void hidePieces() {
        sp.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sp.setVisible(false);

        try {
            URI uri = Main.class.getResource("assets/index.txt").toURI();
            List<String> lines = Files.readAllLines(Paths.get(uri));

            Image[] images = new Image[lines.size()];
            BoardPiece[] pics = new BoardPiece[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                images[i] = new Image(Main.class.getResourceAsStream("assets/" + lines.get(i)));
                pics[i] = new BoardPiece();
                pics[i].setImage(images[i]);
                vb.getChildren().add(pics[i]);
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
