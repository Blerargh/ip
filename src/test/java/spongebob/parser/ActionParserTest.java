package spongebob.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActionParserTest {
    @Test
    public void fromString_validActions_correctEnumReturned() {
        String testInput;

        testInput = "list";
        Assertions.assertEquals(ActionParser.LIST, ActionParser.fromString(testInput));

        testInput = "mark 2";
        Assertions.assertEquals(ActionParser.MARK, ActionParser.fromString(testInput));

        testInput = "unmark 3";
        Assertions.assertEquals(ActionParser.UNMARK, ActionParser.fromString(testInput));

        testInput = "bye";
        Assertions.assertEquals(ActionParser.BYE, ActionParser.fromString(testInput));

        testInput = "order Buy Krabby Patties";
        Assertions.assertEquals(ActionParser.ADD_ORDER, ActionParser.fromString(testInput));

        testInput = "delivery Deliver to Patrick's house /by 12-12-1212 12:12";
        Assertions.assertEquals(ActionParser.ADD_DELIVERY, ActionParser.fromString(testInput));

        testInput = "delivery Deliver to Goo Lagoon No Deadline Specified";
        Assertions.assertEquals(ActionParser.ADD_DELIVERY, ActionParser.fromString(testInput));

        testInput = "reservation Table for 4 /from 12-12-1212 18:00 /to 12-12-1212 20:00";
        Assertions.assertEquals(ActionParser.ADD_RESERVATION, ActionParser.fromString(testInput));

        testInput = "reservation Table for 2 No Time Specified";
        Assertions.assertEquals(ActionParser.ADD_RESERVATION, ActionParser.fromString(testInput));

        testInput = "delete 1";
        Assertions.assertEquals(ActionParser.DELETE, ActionParser.fromString(testInput));

        testInput = "find Patrick";
        Assertions.assertEquals(ActionParser.FIND, ActionParser.fromString(testInput));

        testInput = "find";
        Assertions.assertEquals(ActionParser.FIND, ActionParser.fromString(testInput));

        testInput = "";
        Assertions.assertEquals(ActionParser.EMPTY, ActionParser.fromString(testInput));
    }

    @Test
    public void fromString_invalidListAction_errorEnumReturned() {
        String testInput;

        testInput = "list everything";
        Assertions.assertEquals(ActionParser.ERROR, ActionParser.fromString(testInput));

        testInput = "list all";
        Assertions.assertEquals(ActionParser.ERROR, ActionParser.fromString(testInput));
    }

    @Test
    public void fromString_invalidMarkUnmarkAction_correspondingErrorEnumReturned() {
        String testInput;

        testInput = "mark";
        Assertions.assertEquals(ActionParser.MARK_ERROR, ActionParser.fromString(testInput));

        testInput = "mark two";
        Assertions.assertEquals(ActionParser.MARK_ERROR, ActionParser.fromString(testInput));

        testInput = "mark 1 extra";
        Assertions.assertEquals(ActionParser.MARK_ERROR, ActionParser.fromString(testInput));

        testInput = "unmark";
        Assertions.assertEquals(ActionParser.UNMARK_ERROR, ActionParser.fromString(testInput));

        testInput = "unmark three";
        Assertions.assertEquals(ActionParser.UNMARK_ERROR, ActionParser.fromString(testInput));

        testInput = "unmark 2 extra";
        Assertions.assertEquals(ActionParser.UNMARK_ERROR, ActionParser.fromString(testInput));
    }

    @Test
    public void fromString_invalidByeAction_errorEnumReturned() {
        String testInput;

        testInput = "bye now";
        Assertions.assertEquals(ActionParser.ERROR, ActionParser.fromString(testInput));

        testInput = "bye bye";
        Assertions.assertEquals(ActionParser.ERROR, ActionParser.fromString(testInput));
    }

    @Test
    public void fromString_invalidDeleteAction_errorEnumReturned() {
        String testInput;

        testInput = "delete";
        Assertions.assertEquals(ActionParser.DELETE_ERROR, ActionParser.fromString(testInput));

        testInput = "delete five";
        Assertions.assertEquals(ActionParser.DELETE_ERROR, ActionParser.fromString(testInput));

        testInput = "delete 3 extra";
        Assertions.assertEquals(ActionParser.DELETE_ERROR, ActionParser.fromString(testInput));
    }

    @Test
    public void fromString_unknownAction_errorEnumReturned() {
        String testInput;

        testInput = "patrick?";
        Assertions.assertEquals(ActionParser.ERROR, ActionParser.fromString(testInput));

        testInput = "i quit, Mr. Krabs";
        Assertions.assertEquals(ActionParser.ERROR, ActionParser.fromString(testInput));
    }
}
