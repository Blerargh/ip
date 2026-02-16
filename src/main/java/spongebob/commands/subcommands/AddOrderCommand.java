package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to add an order to the task list.
 */
public class AddOrderCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for AddOrderCommand.
     *
     * @param inputDetails The details of the order to be added, which should
     *                     include the order details.
     *                     For example: "order 2 Krabby Patties with extra pickles".
     */
    public AddOrderCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the command to add an order to the task list. This method first
     * validates the input details and then adds the order to the task list.
     * If the input is invalid or if there is an issue with adding the task to the
     * list, a SpongebobException will be thrown with an appropriate error message.
     *
     * @param guiWindow The main GUI window to display the results in.
     * @param taskList  The Krusty Krab task list to operate on.
     * @throws SpongebobException If there is an error during command execution,
     *                            such as blank input or issues with the task list.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        taskList.addOrder(this.getInputDetails(), guiWindow);
    }

    /**
     * Asserts that the input details for the AddOrderCommand are valid. This method
     * checks that the input is not blank. If the input is invalid, a
     * SpongebobException will be thrown with an appropriate error message
     * indicating the issue with the input.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            AddOrderCommand.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        if (this.getInputDetails().isBlank()) {
            throw new SpongebobException("What Krabby Patty order would you like to make?");
        }
    }

}
