package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class AddReservationCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        AddReservationCommand addReservationCommand1 = new AddReservationCommand(
                "Patrick's Birthday /from 2024-12-31 19:00 /to 2024-12-31 21:00");
        Assertions.assertDoesNotThrow(() -> addReservationCommand1.assertValidInput());
    }

    @Test
    public void assertValidInput_missingSeparators_exceptionThrown() {
        AddReservationCommand addReservationCommand1 = new AddReservationCommand(
                "Patrick's Birthday /from 2024-12-31 19:00");
        Assertions.assertThrows(SpongebobException.class, () -> addReservationCommand1.assertValidInput());

        AddReservationCommand addReservationCommand2 = new AddReservationCommand(
                "Patrick's Birthday /to 2024-12-31 21:00");
        Assertions.assertThrows(SpongebobException.class, () -> addReservationCommand2.assertValidInput());

        AddReservationCommand addReservationCommand3 = new AddReservationCommand(
                "Patrick's Birthday 2024-12-31 21:00");
        Assertions.assertThrows(SpongebobException.class, () -> addReservationCommand3.assertValidInput());
    }

    @Test
    public void assertValidInput_multipleSeparators_exceptionThrown() {
        AddReservationCommand addReservationCommand1 = new AddReservationCommand(
                "Patrick's Birthday /from 2024-12-31 19:00 /to 2024-12-31 21:00 /to 2024-12-31 22:00");
        Assertions.assertThrows(SpongebobException.class, () -> addReservationCommand1.assertValidInput());
    }
}
