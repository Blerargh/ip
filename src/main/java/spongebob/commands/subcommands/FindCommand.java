package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to find tasks in the task list that match the given
 * keyword(s).
 */
public class FindCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for FindCommand.
     *
     * @param inputDetails The details of the command input, which should be the
     *                     keyword(s) to search for in the task list. The input
     *                     should not be empty and should contain valid keyword(s)
     *                     for searching the task list.
     */
    public FindCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the command to find tasks in the task list that match the given
     * keyword(s). This method first validates the input details to ensure that it
     * is not empty. If the input is valid, the method will search for tasks in the
     * task list that match the given keyword(s) and display the results in the GUI.
     * If the input is invalid or if there is an issue with searching the task list,
     * a
     * SpongebobException will be thrown with an appropriate error message.
     *
     * @param guiWindow The main GUI window to display the results in.
     * @param taskList  The Krusty Krab task list to operate on.
     * @throws SpongebobException If there is an error during command execution,
     *                            such as invalid input or issues with the task
     *                            list.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        taskList.findTasks(this.getInputDetails(), guiWindow);
    }

    /**
     * Asserts that the input details for the FindCommand are valid. This method
     * checks that the input is not empty. If the input is invalid, a
     * SpongebobException will be thrown with an appropriate error message
     * indicating that valid keyword(s) are required for searching the task list.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            FindCommand, such as empty input.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        if (this.getInputDetails().isEmpty()) {
            throw new SpongebobException("Please enter valid keyword(s) to search for!");
        }
    }
}
