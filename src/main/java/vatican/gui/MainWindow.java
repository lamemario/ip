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
        // Check "false" for isError
        dialogContainer.getChildren().add(
                DialogBox.getVaticanDialog(" More life. It's Vatican, dun know.\n"
                        + " So, what's the deal? I'm tryna see how I can bless you.", vaticanImage, false)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // 1. If the input is just whitespace or empty
        if (input.trim().isEmpty()) {
            dialogContainer.getChildren().add(
                    DialogBox.getVaticanDialog(
                            "Two twos my word fam, you're saying nothing. Don't be a wasteyute, man. "
                                    + "I'm tryna bless you here. Speak up, fam",
                            vaticanImage,
                            true // Set isError to true so it shows in RED
                    )
            );
            userInput.clear();
            return; // Stop here, don't send empty text to the logic
        }

        // 2. Standard Logic
        String response = vatican.getResponse(input);

        // Detect if the response is an error (check for your specific error prefixes)
        boolean isError = response.toLowerCase().startsWith("two twos")
                || response.toLowerCase().startsWith("error")
                || response.toLowerCase().startsWith("fam"); // Added "fam" just in case

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVaticanDialog(response, vaticanImage, isError)
        );
        userInput.clear();

        // 3. Exit Logic
        if (input.trim().equalsIgnoreCase("bye")) {
            javafx.animation.PauseTransition delay = new javafx.animation
                    .PauseTransition(javafx.util.Duration.seconds(1.5));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }
}
