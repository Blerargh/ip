package spongebob.ui.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import spongebob.SpongebobMain;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField krabsInput;
    @FXML
    private Button sendButton;

    private SpongebobMain spongebob;

    private Image krabsImage = new Image(this.getClass().getResourceAsStream("/images/mrkrabs.png"));
    private Image spongebobImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /** Injects the SpongebobMain instance */
    public void setSpongebob(SpongebobMain d) {
        this.spongebob = d;
        this.spongebob.importTaskList(this);
        this.displaySpongebobResponse(
                "Hello, I'm Spongebob Squarepants!\nWhat can I do for you at the Krusty Krab today?");
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Spongebob's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleKrabsInput() {
        String input = this.krabsInput.getText().trim();
        this.dialogContainer.getChildren().addAll(
                DialogBox.getKrabsDialogBox(input, this.krabsImage));
        this.spongebob.getResponse(input, this);
        this.krabsInput.clear();
    }

    /**
     * Displays Spongebob's response in the dialog container.
     *
     * @param s The response string from Spongebob.
     */
    public void displaySpongebobResponse(String s) {
        this.dialogContainer.getChildren().addAll(
                DialogBox.getSpongebobDialogBox(s, this.spongebobImage));
    }
}
