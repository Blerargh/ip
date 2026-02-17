package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class FindCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        FindCommand findCommand1 = new FindCommand("keyword");
        Assertions.assertDoesNotThrow(() -> findCommand1.assertValidInput());
    }

    @Test
    public void assertValidInput_emptyInput_exceptionThrown() {
        FindCommand findCommand1 = new FindCommand("");
        Assertions.assertThrows(SpongebobException.class, () -> findCommand1.assertValidInput());
    }
}
