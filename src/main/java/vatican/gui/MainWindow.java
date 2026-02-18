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

    // Safer Image Loading (prevents crash if image is missing)
    private final Image userImage = loadImage("/images/sga.png");
    private final Image vaticanImage = loadImage("/images/drizzy.png");

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Focus on text box immediately
        userInput.requestFocus();
    }

    public void setVatican(Vatican v) {
        vatican = v;
        // Assertion requirement
        assert vatican != null : "Vatican instance cannot be null initialization";

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

        // 1. Empty Input Check
        if (input.trim().isEmpty()) {
            handleEmptyInput();
            return;
        }

        // 2. Execute Logic & Determine Style
        String response = vatican.getResponse(input);
        String commandType = getCommandType(input);
        boolean isError = commandType.equals("Error");

        // 3. Update GUI
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                createVaticanDialog(response, commandType, isError)
        );

        userInput.clear();

        // 4. Exit Logic
        if (input.trim().equalsIgnoreCase("bye")) {
            triggerExit();
        }
    }

    /**
     * Determines the visual style of the response based on the command type.
     * This separates styling logic from the main handler (SLAP).
     */
    private String getCommandType(String input) {
        try {
            Command c = Parser.parse(input);
            if (c instanceof AddCommand) {
                return "Add";
            }
            if (c instanceof DeleteCommand) {
                return "Delete";
            }
            if (c instanceof MarkCommand) {
                return "Mark";
            }
            if (c instanceof ExitCommand) {
                return "Delete"; // Exit shares Delete style
            }
            return "Normal";
        } catch (VaticanException e) {
            return "Error";
        }
    }

    private void handleEmptyInput() {
        dialogContainer.getChildren().add(
                DialogBox.getVaticanDialog(
                        "you're saying nothing. Don't be a wasteyute.",
                        vaticanImage,
                        true // Force Error style
                )
        );
        userInput.clear();
    }

    private DialogBox createVaticanDialog(String response, String type, boolean isError) {
        DialogBox box = DialogBox.getVaticanDialog(response, vaticanImage, isError);
        if (!isError && !type.equals("Normal")) {
            box.changeDialogStyle(type);
        }
        return box;
    }

    private void triggerExit() {
        javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(
                javafx.util.Duration.seconds(1.5)
        );
        delay.setOnFinished(event -> javafx.application.Platform.exit());
        delay.play();
    }

    /**
     * Helper to load images safely. Returns null if not found instead of crashing.
     */
    private Image loadImage(String path) {
        var resource = this.getClass().getResourceAsStream(path);
        return resource == null ? null : new Image(resource);
    }
}
