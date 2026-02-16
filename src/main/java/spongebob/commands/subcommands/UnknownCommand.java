package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the unknown command when the user input does not match any known
 * command.
 */
public class UnknownCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for UnknownCommand.
     *
     * @param inputDetails The input details for the unknown command, which can be
     *                     any string that does not match a known command. The input
     *                     details are not used for any specific functionality in
     *                     the unknown command, but they can be stored for potential
     *                     future use or for displaying in the GUI if needed.
     */
    public UnknownCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the unknown command. This method simply displays a message in the
     * GUI indicating that the command is not recognized. Since this command does
     * not perform any operations on the task list, it simply returns without
     * modifying the task list.
     *
     * @param guiWindow The main GUI window to display the results in.
     * @param taskList  The Krusty Krab task list to operate on,
     *                  which is not modified by this command since it is an unknown
     *                  command.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) {
        guiWindow.displaySpongebobResponse("What are you saying, Mr. Krabs?");
    }

    /**
     * Asserts that the input details for the UnknownCommand are valid.
     * Since the input details for an unknown command are not used for any specific
     * functionality, this method does not perform any validation and simply
     * returns.
     */
    @Override
    protected void assertValidInput() {
        // No validation needed for unknown command
    }

}
