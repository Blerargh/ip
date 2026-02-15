package spongebob.commands;

import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Abstract class representing a command that operates on the Krusty Krab task
 * list. Each specific command (e.g., ListCommand, MarkCommand) will extend this
 * class and implement the execute method to perform its specific action on the
 * task list.
 */
public abstract class KrustyKrabTaskCommand {
    private String inputDetails;

    public KrustyKrabTaskCommand(String inputDetails) {
        this.inputDetails = inputDetails;
    }

    public String getInputDetails() {
        return this.inputDetails;
    }

    public abstract void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException;

    protected abstract void assertValidInput() throws SpongebobException;
}
