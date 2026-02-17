package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddDeliveryCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        AddDeliveryCommand addDeliveryCommand = new AddDeliveryCommand("Order 1 /by 2024-12-31 21:00");
        Assertions.assertDoesNotThrow(() -> addDeliveryCommand.assertValidInput());
    }

    @Test
    public void assertValidInput_missingBySeparator_exceptionThrown() {
        AddDeliveryCommand addDeliveryCommand = new AddDeliveryCommand("Order 1 2024-12-31 21:00");
        Assertions.assertThrows(Exception.class, () -> addDeliveryCommand.assertValidInput());
    }

    @Test
    public void assertValidInput_multipleBySeparators_exceptionThrown() {
        AddDeliveryCommand addDeliveryCommand = new AddDeliveryCommand(
                "Order 1 /by 2024-12-31 21:00 /by 2024-12-31 22:00");
        Assertions.assertThrows(Exception.class, () -> addDeliveryCommand.assertValidInput());
    }
}
