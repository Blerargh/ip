package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the list command, which displays all tasks in the Krusty Krab
 * task list.
 */
public class ListCommand extends KrustyKrabTaskCommand {
    public ListCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        taskList.printTasks(guiWindow);
    }

    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().isEmpty()) {
            throw new SpongebobException("Mr Krabs, the list command does not take any additional details!");
        }
    }
}
