package spongebob.commands;

import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Abstract class representing a command that operates on the Krusty Krab task
 * list. Each specific command (e.g., ListCommand, MarkCommand) will extend this
 * class and implement the execute method to perform its specific action on the
 * task list.
 */
public abstract class KrustyKrabTaskCommand {
    private String inputDetails;

    /**
     * Constructor for KrustyKrabTaskCommand.
     *
     * @param inputDetails The details of the command input, which may include task
     *                     details, indices, or other relevant information needed to
     *                     execute the command.
     */
    public KrustyKrabTaskCommand(String inputDetails) {
        this.inputDetails = inputDetails;
    }

    /**
     * Gets the input details for the command.
     *
     * @return The input details for the command, which may include task details,
     *         indices, or other relevant information needed to execute the command.
     */
    public String getInputDetails() {
        return this.inputDetails;
    }

    /**
     * Executes the command on the given task list and updates the GUI window with
     * the results.
     *
     * @param guiWindow The main GUI window to display the results in.
     * @param taskList  The Krusty Krab task list to operate on.
     * @throws SpongebobException If there is an error during command execution,
     *                            such as invalid input or issues with the task
     *                            list.
     */
    public abstract void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException;

    /**
     * Asserts that the input details for the command are valid. This method should
     * be called at the beginning of the execute method to ensure that the command
     * can be executed without issues.
     * If the input is invalid, a SpongebobException should be thrown with an
     * appropriate error message.
     *
     * @throws SpongebobException If the input details are invalid for the command.
     */
    protected abstract void assertValidInput() throws SpongebobException;
}
