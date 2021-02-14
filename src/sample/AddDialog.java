package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddDialog {
    static ArrayList<Pair<String, Image>> choices;
    static Text selected = new Text();

    static {
        try {
            choices = populateList();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Pair<String, Image>> populateList() throws URISyntaxException, IOException {
        if (choices == null) {
            URI uri = AddDialog.class.getResource("Lib/assets/index.txt").toURI();
            List<String> lines = Files.readAllLines(Paths.get(uri));
            ArrayList<Pair<String, Image>> result = new ArrayList<>();
            for (String line : lines) {
                Image image = new Image(AddDialog.class.getResourceAsStream("Lib/assets/" + line + ".png"));
                result.add(new Pair<>(line, image));
            }
            return result;
        } else {
            return choices;
        }
    }

    public static String showDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WarChess");

        ImageView[] images = new ImageView[choices.size()];
        for (int i = 0; i < images.length; i++) {
            Pair<String, Image> pair = choices.get(i);
            images[i] = new ImageView(pair.getValue());
            images[i].setOnMouseClicked(event -> {
                selected.setText(pair.getKey());
            });
        }

        HBox box = new HBox();
        box.getChildren().addAll(images);
        VBox layout = new VBox();
        layout.getChildren().add(box);
        layout.getChildren().add(selected);
        alert.getDialogPane().setContent(layout);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK ? selected.getText() : null;
    }
}
