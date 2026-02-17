package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to unmark a task in the task list as not done.
 */
public class UnmarkCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for UnmarkCommand.
     *
     * @param inputDetails The details of the command input, which should be the
     *                     index of the task to be unmarked as not done in the task
     *                     list. The index should be a positive integer
     *                     corresponding to the task's position in the list (1-based
     *                     index).
     */
    public UnmarkCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the command to unmark a task in the task list as not done. This
     * method first validates the input details to ensure that it is a valid index.
     * If the input is valid, the specified task will be unmarked as not done in the
     * task list. If the input is invalid or if there is an issue with unmarking the
     * task in the list, a SpongebobException will be thrown with an appropriate
     * error message.
     *
     * @param guiWindow The main GUI window to display the results in.
     * @param taskList  The Krusty Krab task list to operate on.
     * @param isUndo    Whether the command is being executed as part of an undo
     *                  operation. If it is an undo operation, the command should
     *                  not display any response in the GUI window, as the response
     *                  would have already been displayed when the command was
     *                  originally executed.
     * @throws SpongebobException If there is an error during command execution,
     *                            such as invalid input or issues with the task
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList, boolean isUndo) throws SpongebobException {
        this.assertValidInput();
        int indexToUnmark = Integer.parseInt(this.getInputDetails()) - 1;
        taskList.unmarkTask(indexToUnmark, guiWindow, isUndo);
    }

    /**
     * Asserts that the input details for the UnmarkCommand are valid. This method
     * checks that the input is a positive integer corresponding to a valid task
     * index in the task list. If the input is invalid, a SpongebobException will be
     * thrown with an appropriate error message.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            UnmarkCommand, such as non-integer input
     *                            or a non-positive integer index.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().matches("\\d+")) {
            throw new SpongebobException("Which task are you referring to?");
        }

        // Assert that task index is strictly positive integer
        int taskIndex = Integer.parseInt(this.getInputDetails()) - 1;
        if (taskIndex < 0) {
            throw new SpongebobException("Which task are you referring to?");
        }
    }
}
