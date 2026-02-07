package vatican.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        Circle clip = new Circle(displayPicture.getFitWidth() / 2, displayPicture.getFitHeight() / 2, 45);
        displayPicture.setClip(clip);
        this.setPadding(new javafx.geometry.Insets(10));
        this.setSpacing(10);
    }

    /**
     * Applies the User style (Green).
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-label"); // CSS class
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Applies the Vatican style (White) OR Error style (Red).
     */
    public static DialogBox getVaticanDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img);
        db.flip();

        if (isError) {
            db.dialog.getStyleClass().add("error-label"); // CSS class
        } else {
            db.dialog.getStyleClass().add("vatican-label"); // CSS class
        }
        return db;
    }

    /**
     * Applies the specific style based on the command type.
     * @param commandType The type of command (Add, Mark, Delete, etc.)
     */
    public void changeDialogStyle(String commandType) {
        switch (commandType) {
        case "Add":
            dialog.getStyleClass().add("add-label");
            break;
        case "Mark":
            dialog.getStyleClass().add("marked-label");
            break;
        case "Delete":
            dialog.getStyleClass().add("delete-label");
            break; // "Error" and "Normal" are handled by the constructor logic
        default:
            // Do nothing if no specific style is found
            break;
        }
    }

}
