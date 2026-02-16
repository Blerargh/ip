package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to delete a task from the task list.
 */
public class DeleteCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for DeleteCommand.
     *
     * @param inputDetails The details of the command input, which should be the
     *                     index of the task to be deleted in the task list. The
     *                     index should be a positive integer corresponding to the
     *                     task's position in the list (1-based index).
     */
    public DeleteCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the command to delete a task from the task list. This method first
     * validates the input details to ensure that it is a valid index. If the input
     * is valid, the specified task will be deleted from the task list. If the input
     * is invalid or if there is an issue with deleting the task from the list, a
     * SpongebobException will be thrown with an appropriate error message.
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
     *                            list.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList, boolean isUndo) throws SpongebobException {
        this.assertValidInput();
        int indexToDelete = Integer.parseInt(this.getInputDetails()) - 1;
        taskList.deleteTask(indexToDelete, guiWindow, isUndo);
    }

    /**
     * Asserts that the input details for the DeleteCommand are valid. This method
     * checks that the input is a positive integer corresponding to a valid task
     * index in the task list. If the input is invalid, a SpongebobException will be
     * thrown with an appropriate error message.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            DeleteCommand, such as non-integer input or an
     *                            index that is out of bounds.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().matches("\\d+")) {
            throw new SpongebobException("Which task are you referring to?");
        }
    }
}
