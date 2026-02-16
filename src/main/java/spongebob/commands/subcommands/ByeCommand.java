package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to exit the program.
 * When executed, the program will terminate.
 */
public class ByeCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for ByeCommand.
     *
     * @param inputDetails The details of the command input, which should be empty
     *                     for the bye command.
     */
    public ByeCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the command to exit the program. This method first validates the
     * input details to ensure that there are no additional details provided with
     * the bye command. If the input is valid, the program will terminate. If the
     * input is invalid, a SpongebobException will be thrown with an appropriate
     * error message.
     *
     * @param guiWindow The main GUI window to display the results in.
     * @param taskList  The Krusty Krab task list to operate on.
     * @throws SpongebobException If there is an error during command execution,
     *                            such as additional input for the bye command.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        System.exit(0);
    }

    /**
     * Asserts that the input details for the ByeCommand are valid. This method
     * checks that there are no additional details provided with the bye command. If
     * the input is invalid, a SpongebobException will be thrown with an appropriate
     * error message indicating that the bye command should not have any additional
     * details.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            ByeCommand, such as additional input provided.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().equals("")) {
            throw new SpongebobException(
                    "Mr. Krabs, the 'bye' command should not have any additional details!");
        }
    }

}
