package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to find tasks in the task list that match the given
 * keyword(s).
 */
public class FindCommand extends KrustyKrabTaskCommand {
    public FindCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        taskList.findTasks(this.getInputDetails(), guiWindow);
    }

    @Override
    protected void assertValidInput() throws SpongebobException {
        if (this.getInputDetails().isEmpty()) {
            throw new SpongebobException("Please enter valid keyword(s) to search for!");
        }
    }
}
