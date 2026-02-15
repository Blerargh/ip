package spongebob.commands.subcommands;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to exit the program.
 * When executed, the program will terminate.
 */
public class ByeCommand extends KrustyKrabTaskCommand {
    public ByeCommand(String inputDetails) {
        super(inputDetails);
    }

    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList) throws SpongebobException {
        this.assertValidInput();
        System.exit(0);
    }

    @Override
    protected void assertValidInput() throws SpongebobException {
        if (!this.getInputDetails().equals("")) {
            throw new SpongebobException(
                    "Mr. Krabs, the 'bye' command should not have any additional details!");
        }
    }

}
