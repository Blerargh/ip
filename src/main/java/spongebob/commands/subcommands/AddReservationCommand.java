package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to add a reservation task to the task list.
 */
public class AddReservationCommand extends KrustyKrabTaskCommand {
    public AddReservationCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        taskList.addReservation(this.getInputDetails(), guiWindow);
    }

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
