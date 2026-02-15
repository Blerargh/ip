package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents an empty command when the user input is empty.
 */
public class EmptyCommand extends KrustyKrabTaskCommand {
    public EmptyCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) {
        guiWindow.displaySpongebobResponse("Did you make a request?");
    }

    @Override
    protected void assertValidInput() {
        // No validation needed for empty command
    }

}
