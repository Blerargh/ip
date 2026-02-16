package spongebob.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

public class ActionParserTest {
    @Test
    public void extractCommand_validCommands_returnsCorrespondingCommandClass() {
        ActionParser parser = new ActionParser();

        String[] validCommands = {
            "list",
            "mark 1",
            "unmark 2",
            "bye",
            "order Krabby Patties",
            "delivery Deliver Krabby Patties /by 31-12-2024 18:00",
            "reservation Patrick's birthday /from 31-12-2024 18:00 /to 31-12-2990 20:00",
            "delete 3",
            "find Krabby",
            "",
            "haha unknown command"
        };

        KrustyKrabTaskCommand[] expectedCommands = {
            new ListCommand(""),
            new MarkCommand("1"),
            new UnmarkCommand("2"),
            new ByeCommand(""),
            new AddOrderCommand("Krabby Patties"),
            new AddDeliveryCommand("Deliver Krabby Patties /by 31-12-2024 18:00"),
            new AddReservationCommand("Patrick's birthday /from 31-12-2024 18:00 /to 31-12-2990 20:00"),
            new DeleteCommand("3"),
            new FindCommand("Krabby"),
            new EmptyCommand(""),
            new UnknownCommand("haha unknown command")
        };

        for (int i = 0; i < validCommands.length; i++) {
            String command = validCommands[i];
            KrustyKrabTaskCommand expectedCommand = expectedCommands[i];
            KrustyKrabTaskCommand actualCommand = parser.extractCommand(command.split(" ")[0], command);
            Assertions.assertEquals(expectedCommand, actualCommand);
        }
    }
}
