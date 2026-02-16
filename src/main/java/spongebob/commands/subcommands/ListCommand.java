package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the list command, which displays all tasks in the Krusty Krab
 * task list.
 */
public class ListCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for ListCommand.
     *
     * @param inputDetails The details of the command input, which should be empty
     *                     for the list command.
     */
    public ListCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the list command to display all tasks in the Krusty Krab task list.
     * This method first validates the input details to ensure that there are no
     * additional details provided with the list command. If the input is valid, the
     * method will display all tasks in the task list in the GUI. If the input is
     * invalid or if there is an issue with displaying the task list, a
     * SpongebobException will be thrown with an appropriate error message.
     *
     * @param guiWindow The main GUI window to display the results in.
     * @param taskList  The Krusty Krab task list to operate on.
     * @param isUndo    Whether the command is being executed as part of an undo
     *                  operation. Not relevant for the list command, as it does not
     *                  modify the task list or display any response in the GUI when
     *                  executed as part of an undo operation, since the list
     *                  command is typically used to display the current state of
     *                  the task list and does not have any specific response to
     *                  display when executed as part of an undo operation.
     * @throws SpongebobException If there is an error during command execution,
     *                            such as additional input for the list command or
     *                            issues with the task list.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList, boolean isUndo) throws SpongebobException {
        this.assertValidInput();
        taskList.printTasks(guiWindow);
    }

    /**
     * Asserts that the input details for the ListCommand are valid. This method
     * checks that there are no additional details provided with the list command.
     * If the input is not empty, a SpongebobException is thrown. This ensures that
     * the list command is used correctly without any additional input.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            ListCommand, such as additional input provided.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().isEmpty()) {
            throw new SpongebobException("Mr Krabs, the list command does not take any additional details!");
        }
    }
}
