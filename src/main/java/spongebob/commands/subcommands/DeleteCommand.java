package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to delete a task from the task list.
 */
public class DeleteCommand extends KrustyKrabTaskCommand {
    public DeleteCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        int indexToDelete = Integer.parseInt(this.getInputDetails()) - 1;
        taskList.deleteTask(indexToDelete, guiWindow);
    }

    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().matches("\\d+")) {
            throw new SpongebobException("Which task are you referring to?");
        }
    }
}
