package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to add an order to the task list.
 */
public class AddOrderCommand extends KrustyKrabTaskCommand {
    public AddOrderCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        taskList.addOrder(this.getInputDetails(), guiWindow);
    }

    @Override
    protected void assertValidInput() throws SpongebobException {
        if (this.getInputDetails().isBlank()) {
            throw new SpongebobException("What Krabby Patty order would you like to make?");
        }
    }

}
