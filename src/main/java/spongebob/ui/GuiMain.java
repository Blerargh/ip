package spongebob.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import spongebob.SpongebobMain;
import spongebob.ui.components.MainWindow;

/**
 * The main GUI class for the Spongebob application.
 */
public class GuiMain extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image krabsImage = new Image(this.getClass().getResourceAsStream("/images/mrkrabs.png"));
    private Image spongebobImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));
    private SpongebobMain spongebob = new SpongebobMain();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SpongebobMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Spongebob");
            fxmlLoader.<MainWindow>getController().setSpongebob(this.spongebob); // inject the Spongebob instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
