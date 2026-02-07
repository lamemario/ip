package vatican.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import vatican.Parser;
import vatican.Vatican;
import vatican.VaticanException;
import vatican.command.AddCommand;
import vatican.command.Command;
import vatican.command.DeleteCommand;
import vatican.command.ExitCommand;
import vatican.command.MarkCommand;
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
        dialogContainer.getChildren().add(
                DialogBox.getVaticanDialog(
                        "More life. It's Vatican, dun know.\nSo, what's the deal? I'm tryna see how I can bless you.",
                        vaticanImage,
                        false
                )
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // 1. Wasteyute Check (Empty Input)
        if (input.trim().isEmpty()) {
            dialogContainer.getChildren().add(
                    DialogBox.getVaticanDialog(
                            "Fam, you're saying nothing. Don't be a wasteyute.",
                            vaticanImage,
                            true // Force Error style
                    )
            );
            userInput.clear();
            return;
        }

        // 2. Execute Logic (Get text response)
        String response = vatican.getResponse(input);

        // 3. Determine Style (Type-Safe!)
        String commandType = "Normal";
        boolean isError = false;

        try {
            Command c = Parser.parse(input); // Check the command type

            if (c instanceof AddCommand) {
                commandType = "Add"; // Blue
            } else if (c instanceof DeleteCommand) {
                commandType = "Delete"; // Orange
            } else if (c instanceof MarkCommand) {
                commandType = "Mark"; // Green
            } else if (c instanceof ExitCommand) {
                commandType = "Delete"; // Orange (Exit shares Delete style)
            }
            // ListCommand and FindCommand default to "Normal" (White)

        } catch (VaticanException e) {
            isError = true;
            commandType = "Error"; // Red
        }

        // 4. Update GUI
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        DialogBox vaticanBox = DialogBox.getVaticanDialog(response, vaticanImage, isError);

        // Apply specific style if it's not a generic error/normal message
        if (!isError && !commandType.equals("Normal")) {
            vaticanBox.changeDialogStyle(commandType);
        }

        dialogContainer.getChildren().add(vaticanBox);
        userInput.clear();

        // 5. Exit Logic
        if (input.trim().equalsIgnoreCase("bye")) {
            javafx.animation.PauseTransition delay = new javafx.animation
                    .PauseTransition(javafx.util.Duration.seconds(1.5));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }
}
