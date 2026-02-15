package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the unknown command when the user input does not match any known
 * command.
 */
public class UnknownCommand extends KrustyKrabTaskCommand {
    public UnknownCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) {
        guiWindow.displaySpongebobResponse("What are you saying, Mr. Krabs?");
    }

    @Override
    protected void assertValidInput() {
        // No validation needed for unknown command
    }

}
