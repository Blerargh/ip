package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class ByeCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        ByeCommand byeCommand = new ByeCommand("");
        Assertions.assertDoesNotThrow(() -> byeCommand.assertValidInput());
    }

    @Test
    public void assertValidInput_additionalDetails_exceptionThrown() {
        ByeCommand byeCommand1 = new ByeCommand("additional details");
        Assertions.assertThrows(SpongebobException.class, () -> byeCommand1.assertValidInput());

        ByeCommand byeCommand2 = new ByeCommand("1");
        Assertions.assertThrows(SpongebobException.class, () -> byeCommand2.assertValidInput());
    }
}
