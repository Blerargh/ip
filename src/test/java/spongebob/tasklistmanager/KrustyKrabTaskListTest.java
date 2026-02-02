package spongebob.tasklistmanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import spongebob.exceptions.SpongebobException;

public class KrustyKrabTaskListTest {
    @Test
    public void addDelivery_invalidDateFormat_throwsSpongebobException() {
        KrustyKrabTaskList taskList = new KrustyKrabTaskList();
        String invalidDeliveryDetails = "Deliver Krabby Patties /by 2024/12/31 18:00";

        Exception exception = Assertions.assertThrows(SpongebobException.class, () -> {
            taskList.addDelivery(invalidDeliveryDetails);
        });

        String expectedMessage = "Please enter the delivery deadline in the format dd-MM-yyyy HH:mm.";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void addDelivery_missingDeliveryDetails_throwsSpongebobException() {
        KrustyKrabTaskList taskList = new KrustyKrabTaskList();
        String missingDetails = "  /by 31-12-2024 18:00";

        Exception exception = Assertions.assertThrows(SpongebobException.class, () -> {
            taskList.addDelivery(missingDetails);
        });

        String expectedMessage = "What delivery order would you like to make?";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void addReservation_invalidDateFormat_throwsSpongebobException() {
        KrustyKrabTaskList taskList = new KrustyKrabTaskList();
        String invalidReservationDetails = "Reserve Patrick's birthday /from 2019/12/31 18:00 /to 2099/12/31 20:00";

        Exception exception = Assertions.assertThrows(SpongebobException.class, () -> {
            taskList.addReservation(invalidReservationDetails);
        });

        String expectedMessage = "Please enter the reservation time in the format dd-MM-yyyy HH:mm.";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void addReservation_missingReservationDetails_throwsSpongebobException() {
        KrustyKrabTaskList taskList = new KrustyKrabTaskList();
        String missingDetails = "  /from 31-12-2024 18:00 /to 31-12-2990 20:00";

        Exception exception = Assertions.assertThrows(SpongebobException.class, () -> {
            taskList.addReservation(missingDetails);
        });

        String expectedMessage = "What reservation would you like to make?";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
