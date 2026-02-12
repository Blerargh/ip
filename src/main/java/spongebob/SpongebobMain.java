package spongebob;

import spongebob.exceptions.SpongebobException;
import spongebob.parser.ActionParser;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.tasklistmanager.KrustyKrabTaskStorage;
import spongebob.ui.components.MainWindow;

/**
 * The main class that runs the Spongebob application.
 */
public class SpongebobMain {
    private KrustyKrabTaskStorage taskStorage = new KrustyKrabTaskStorage();
    private KrustyKrabTaskList taskList;

    public void importTaskList(MainWindow guiWindow) {
        try {
            this.taskList = this.taskStorage.loadTasks();
            guiWindow.displaySpongebobResponse("Tasks loaded successfully.");
        } catch (SpongebobException e) {
            this.taskList = new KrustyKrabTaskList();
            guiWindow.displaySpongebobResponse(e.getMessage());
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public void getResponse(String krabsInput, MainWindow mainWindow) {
        krabsInput = krabsInput.trim();
        ActionParser action = ActionParser.fromString(krabsInput);
        ActionParser.executeAction(action, this.taskList, krabsInput, mainWindow);
    }
}
