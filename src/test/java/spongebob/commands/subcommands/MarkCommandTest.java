package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class MarkCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        MarkCommand markCommand1 = new MarkCommand("1");
        Assertions.assertDoesNotThrow(() -> markCommand1.assertValidInput());
    }

    @Test
    public void assertValidInput_invalidInput_exceptionThrown() {
        MarkCommand markCommand1 = new MarkCommand("invalid");
        Assertions.assertThrows(SpongebobException.class, () -> markCommand1.assertValidInput());

        MarkCommand markCommand2 = new MarkCommand("-1");
        Assertions.assertThrows(SpongebobException.class, () -> markCommand2.assertValidInput());

        MarkCommand markCommand3 = new MarkCommand("0");
        Assertions.assertThrows(SpongebobException.class, () -> markCommand3.assertValidInput());
    }
}
