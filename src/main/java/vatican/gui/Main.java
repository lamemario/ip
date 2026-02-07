package vatican.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vatican.Vatican;

/**
 * A GUI for Vatican using FXML.
 */
public class Main extends Application {

    private Vatican vatican = new Vatican("data/vatican.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Vatican Chatbot");

            fxmlLoader.<MainWindow>getController().setVatican(vatican);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
