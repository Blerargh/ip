package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents an empty command when the user input is empty.
 */
public class EmptyCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for EmptyCommand.
     *
     * @param inputDetails The input details for the empty command, which should be
     *                     an empty string.
     */
    public EmptyCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the empty command. This method simply displays a message in the GUI
     * indicating that no request was made. Since this command does not perform any
     * operations on the task list, it simply returns without modifying the task
     * list.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) {
        guiWindow.displaySpongebobResponse("Did you make a request?");
    }

    /**
     * Asserts that the input details for the EmptyCommand are valid. Since the
     * empty command does not require any input details, this method does not
     * perform any validation and simply returns. There are no conditions for the
     * input details of the empty command, as it is expected to be an empty string.
     */
    @Override
    protected void assertValidInput() {
        // No validation needed for empty command
    }

}
