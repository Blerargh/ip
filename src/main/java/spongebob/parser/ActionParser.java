package spongebob.parser;

import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.commands.subcommands.AddDeliveryCommand;
import spongebob.commands.subcommands.AddOrderCommand;
import spongebob.commands.subcommands.AddReservationCommand;
import spongebob.commands.subcommands.ByeCommand;
import spongebob.commands.subcommands.DeleteCommand;
import spongebob.commands.subcommands.EmptyCommand;
import spongebob.commands.subcommands.FindCommand;
import spongebob.commands.subcommands.ListCommand;
import spongebob.commands.subcommands.MarkCommand;
import spongebob.commands.subcommands.UnknownCommand;
import spongebob.commands.subcommands.UnmarkCommand;
import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Parses user actions and executes corresponding methods in KrustyKrabTaskList.
 */
public class ActionParser {
    /**
     * Extracts then executes the corresponding KrustyKrabTaskCommand from the user
     * input.
     *
     * @param krabsInput The user input string.
     * @param taskList   The KrustyKrabTaskList to update.
     * @param guiWindow  The main GUI window to display Spongebob responses.
     */
    public void fromStringToExecuteCommand(String krabsInput, KrustyKrabTaskList taskList,
            MainWindow guiWindow) {
        String[] words = krabsInput.split(" ");
        String firstWord = words[0].toLowerCase();

        try {
            KrustyKrabTaskCommand command = this.extractCommand(firstWord, krabsInput);
            command.execute(guiWindow, taskList);
        } catch (StringIndexOutOfBoundsException e) {
            guiWindow.displaySpongebobResponse("Please provide more details for your request.");
        } catch (SpongebobException e) {
            guiWindow.displaySpongebobResponse(e.getMessage());
        }
    }

    /**
     * Extracts the corresponding KrustyKrabTaskCommand from the user input.
     *
     * @param firstWord  The first word of the user input, used to determine the
     *                   command type.
     * @param krabsInput The full user input string, which may contain additional
     *                   details needed to execute the command.
     * @return The corresponding KrustyKrabTaskCommand based on the first word of
     *         the user input.
     * @throws StringIndexOutOfBoundsException If the user input does not contain
     *                                         enough details for the command.
     */
    public KrustyKrabTaskCommand extractCommand(String firstWord, String krabsInput)
            throws StringIndexOutOfBoundsException {
        switch (firstWord) {
        case "list":
            return new ListCommand(krabsInput.substring(4).trim());
        case "mark":
            return new MarkCommand(krabsInput.substring(4).trim());
        case "unmark":
            return new UnmarkCommand(krabsInput.substring(6).trim());
        case "bye":
            return new ByeCommand(krabsInput.substring(3).trim());
        case "order":
            return new AddOrderCommand(krabsInput.substring(5).trim());
        case "delivery":
            return new AddDeliveryCommand(krabsInput.substring(8).trim());
        case "reservation":
            return new AddReservationCommand(krabsInput.substring(11).trim());
        case "delete":
            return new DeleteCommand(krabsInput.substring(6).trim());
        case "find":
            return new FindCommand(krabsInput.substring(4).trim());
        case "":
            return new EmptyCommand(krabsInput);
        default:
            return new UnknownCommand(krabsInput);
        }
    }
}
