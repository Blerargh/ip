package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to undo the last modification made to the task list.
 */
public class UndoCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for UndoCommand.
     *
     * @param inputDetails The details of the command input, which should be empty
     *                     for the undo command. The undo command does not require
     *                     any additional input, as it simply undoes the last
     *                     modification made to the task list. If there are any
     *                     input details provided for the undo command, it will be
     *                     considered invalid and an appropriate error message will
     *                     be displayed in the GUI.
     */
    public UndoCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the undo command to undo the last modification made to the task
     * list. This method first validates the input details to ensure that it is
     * empty, as the undo command does not take any additional input. If the input
     * details are not empty, a SpongebobException is thrown with an appropriate
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
     *                            list.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList, boolean isUndo) throws SpongebobException {
        this.assertValidInput();
        taskList.undoLastModification(guiWindow);
    }

    /**
     * Asserts that the input details for the UndoCommand are valid. This method
     * checks that the input is empty, as the undo command does not take any
     * additional input.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            UndoCommand, such as non-empty input.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().isEmpty()) {
            throw new SpongebobException("The undo command does not take any additional input.");
        }
    }

}
