package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class AddOrderCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        AddOrderCommand addOrderCommand = new AddOrderCommand("Krabby Patty");
        Assertions.assertDoesNotThrow(() -> addOrderCommand.assertValidInput());
    }

    @Test
    public void assertValidInput_emptyInput_exceptionThrown() {
        AddOrderCommand addOrderCommand = new AddOrderCommand("");
        Assertions.assertThrows(SpongebobException.class, () -> addOrderCommand.assertValidInput());
    }
}
