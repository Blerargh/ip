package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class DeleteCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        DeleteCommand deleteCommand1 = new DeleteCommand("1");
        Assertions.assertDoesNotThrow(() -> deleteCommand1.assertValidInput());
    }

    @Test
    public void assertValidInput_invalidInput_exceptionThrown() {
        DeleteCommand deleteCommand1 = new DeleteCommand("invalid");
        Assertions.assertThrows(SpongebobException.class, () -> deleteCommand1.assertValidInput());

        DeleteCommand deleteCommand2 = new DeleteCommand("-1");
        Assertions.assertThrows(SpongebobException.class, () -> deleteCommand2.assertValidInput());

        DeleteCommand deleteCommand3 = new DeleteCommand("0");
        Assertions.assertThrows(SpongebobException.class, () -> deleteCommand3.assertValidInput());
    }
}
