package spongebob.commands.subcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class UndoCommandTest {
    @Test
    public void assertValidInput_validInput_noExceptionThrown() {
        UndoCommand undoCommand1 = new UndoCommand("");
        Assertions.assertDoesNotThrow(() -> undoCommand1.assertValidInput());
    }

    @Test
    public void assertValidInput_invalidInput_exceptionThrown() {
        UndoCommand undoCommand1 = new UndoCommand("invalid");
        Assertions.assertThrows(SpongebobException.class, () -> undoCommand1.assertValidInput());
    }
}
