package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to add a reservation task to the task list.
 */
public class AddReservationCommand extends KrustyKrabTaskCommand {
    /**
     * Constructor for AddReservationCommand.
     *
     * @param inputDetails The details of the reservation to be added, which should
     *                     include the reservation details. The input should also
     *                     include the start and end dates in dd-MM-yyyy HH:mm
     *                     format for the reservation, separated by "/from" and
     *                     "/to" respectively.
     *                     For example: "reservation table for 4 /from 2024-12-31
     *                     18:00 /to 2024-12-31 20:00".
     */
    public AddReservationCommand(String inputDetails) {
        super(inputDetails);
    }

    /**
     * Executes the command to add a reservation task to the task list. This method
     * first validates the input details and then adds the reservation task to the
     * task list.
     * If the input is invalid or if there is an issue with adding the task to the
     * list, a SpongebobException will be thrown with an appropriate error message.
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
        taskList.addReservation(this.getInputDetails(), guiWindow, isUndo);
    }

    /**
     * Asserts that the input details for the AddReservationCommand are valid. This
     * method checks that the input is not blank, that it contains both the start
     * and end dates, and that it does not contain more than one start or end date.
     * If the input is invalid, a SpongebobException will be thrown with an
     * appropriate error message indicating the issue with the input.
     *
     * @throws SpongebobException If the input details are invalid for the
     *                            AddReservationCommand. such as blank input,
     *                            missing start or end date, or multiple start or
     *                            end dates provided.
     */
    @Override
    protected void assertValidInput() throws SpongebobException {
        String inputDetails = this.getInputDetails();
        if (inputDetails.isEmpty()) {
            throw new SpongebobException("What reservation would you like to make?");
        }

        if (inputDetails.split("/from").length < 2) {
            throw new SpongebobException("Please provide both the start and end dates for the reservation.");
        }

        if (inputDetails.split("/to").length < 2) {
            throw new SpongebobException("Please provide both the start and end dates for the reservation.");
        }

        if (inputDetails.split("/from").length > 2 || inputDetails.split("/to").length > 2) {
            throw new SpongebobException("Please provide only one start and end date for the reservation.");
        }
    }
}
