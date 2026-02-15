package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to add a delivery task to the task list.
 */
public class AddDeliveryCommand extends KrustyKrabTaskCommand {
    public AddDeliveryCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        taskList.addDelivery(this.getInputDetails(), guiWindow);
    }

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
