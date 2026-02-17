package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class ListCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        ListCommand listCommand1 = new ListCommand("");
        Assertions.assertDoesNotThrow(() -> listCommand1.assertValidInput());
    }

    @Test
    public void assertValidInput_additionalDetails_exceptionThrown() {
        ListCommand listCommand1 = new ListCommand("additional details");
        Assertions.assertThrows(SpongebobException.class, () -> listCommand1.assertValidInput());

        ListCommand listCommand2 = new ListCommand("1");
        Assertions.assertThrows(SpongebobException.class, () -> listCommand2.assertValidInput());
    }
}
