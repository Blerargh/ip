package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class UnmarkCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        UnmarkCommand unmarkCommand1 = new UnmarkCommand("1");
        Assertions.assertDoesNotThrow(() -> unmarkCommand1.assertValidInput());
    }

    @Test
    public void assertValidInput_invalidInput_exceptionThrown() {
        UnmarkCommand unmarkCommand1 = new UnmarkCommand("invalid");
        Assertions.assertThrows(SpongebobException.class, () -> unmarkCommand1.assertValidInput());

        UnmarkCommand unmarkCommand2 = new UnmarkCommand("-1");
        Assertions.assertThrows(SpongebobException.class, () -> unmarkCommand2.assertValidInput());

        UnmarkCommand unmarkCommand3 = new UnmarkCommand("0");
        Assertions.assertThrows(SpongebobException.class, () -> unmarkCommand3.assertValidInput());
    }
}
