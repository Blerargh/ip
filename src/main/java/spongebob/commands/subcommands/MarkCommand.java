package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to mark a task in the task list as completed.
 */
public class MarkCommand extends KrustyKrabTaskCommand {
    public MarkCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        int indexToMark = Integer.parseInt(this.getInputDetails()) - 1;
        taskList.markTask(indexToMark, guiWindow);
    }

    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().matches("\\d+")) {
            throw new SpongebobException("Which task are you referring to?");
        }
    }
}
