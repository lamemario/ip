package vatican.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import vatican.Vatican;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Vatican vatican;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sga.png"));
    private Image vaticanImage = new Image(this.getClass().getResourceAsStream("/images/drizzy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setVatican(Vatican v) {
        vatican = v;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = vatican.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, vaticanImage)
        );
        userInput.clear();
    }
}
