package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to add a delivery task to the task list.
 */
public class AddDeliveryCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for AddDeliveryCommand.
     *
     * @param inputDetails The details of the delivery task to be added, which
     *                     should include the delivery details and the delivery
     *                     date and time in dd-MM-yyyy HH:mm format, separated by
     *                     "/by".
     *                     For example: "delivery Krabby Patty /by 2024-12-31
     *                     18:00".
     */
    public AddDeliveryCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the command to add a delivery task to the task list. This method
     * first validates the input details and then adds the delivery task to the task
     * list.
     * If the input is invalid or if there is an issue with adding the task to
     * the list, a SpongebobException will be thrown with an appropriate error
     * message.
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
        taskList.addDelivery(this.getInputDetails(), guiWindow);
    }

    /**
     * Asserts that the input details for the AddDeliveryCommand are valid. This
     * method checks that the input is not blank, that it contains both the delivery
     * details and the delivery date, and that it does not contain more than one
     * delivery date.
     * If the input is invalid, a SpongebobException will be thrown with an
     * appropriate error message indicating the issue with the input.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            AddDeliveryCommand.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        String inputDetails = this.getInputDetails();
        if (inputDetails.isBlank()) {
            throw new SpongebobException("What delivery order would you like to make?");
        }

        if (inputDetails.split("/by").length < 2) {
            throw new SpongebobException("Please provide both the delivery details and the delivery date.");
        }

        if (inputDetails.split("/by").length > 2) {
            throw new SpongebobException("Please provide only one delivery date.");
        }
    }
}
