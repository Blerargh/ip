package spongebob.tasklistmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import spongebob.exceptions.SpongebobException;
import spongebob.tasktype.KrustyKrabDelivery;
import spongebob.tasktype.KrustyKrabOrder;
import spongebob.tasktype.KrustyKrabReservation;
import spongebob.tasktype.KrustyKrabTask;
import spongebob.ui.components.MainWindow;

/**
 * Manages the list of Krusty Krab tasks including orders, deliveries, and
 * reservations.
 * Manages the addition, deletion, marking, and unmarking of tasks.
 * Interacts with KrustyKrabTaskStorage for saving and loading tasks.
 */
public class KrustyKrabTaskList {
    private ArrayList<KrustyKrabTask> krustyKrabOrderList = new ArrayList<>();
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Prints the list of Krusty Krab tasks to the console.
     */
    public void printTasks(MainWindow guiWindow) {
        String displayString = "";
        if (this.krustyKrabOrderList.isEmpty()) {
            displayString += "How about making a request first?";
        } else {
            displayString += "Here are the tasks in your Krusty Krab task list:";
            for (int i = 0; i < this.krustyKrabOrderList.size(); i++) {
                displayString += "\nKrabby Patty Task " + (i + 1) + ": ";
                KrustyKrabTask order = this.krustyKrabOrderList.get(i);
                displayString += order.toString();
            }
        }
        guiWindow.displaySpongebobResponse(displayString);
    }

    /**
     * Adds a general Krusty Krab task to the task list.
     * Reserved for internal use with loading tasks from storage.
     * 
     * @param task
     */
    public void addTask(KrustyKrabTask task) {
        this.krustyKrabOrderList.add(task);
    }

    /**
     * Adds a new Krusty Krab order to the task list.
     * 
     * @param orderDetails Details of the order to be added.
     * @param guiWindow    The main GUI window to display the results in.
     */
    public void addOrder(String orderDetails, MainWindow guiWindow) {
        // Retrieve order details
        String taskDetails = orderDetails.trim();
        KrustyKrabOrder newOrder = new KrustyKrabOrder(taskDetails);
        this.krustyKrabOrderList.add(newOrder);
        String displayString = "Krabby Patty Order received!\n" + newOrder.toString();

        try {
            KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
            displayString += "\nTasks saved successfully!";
        } catch (SpongebobException e) {
            displayString += String.format("\n%s", e.getMessage());
        }

        guiWindow.displaySpongebobResponse(displayString);
    }

    /**
     * Adds a new Krusty Krab delivery to the task list.
     * 
     * @param deliveryDetails Details of the delivery to be added.
     * @param guiWindow       The main GUI window to display the results in.
     * @throws SpongebobException             If there is an error with the delivery
     *                                        details.
     * @throws ArrayIndexOutOfBoundsException If the delivery details are not in the
     *                                        expected format.
     */
    public void addDelivery(String deliveryDetails, MainWindow guiWindow)
            throws SpongebobException, ArrayIndexOutOfBoundsException {
        // Retrieve delivery details
        String taskDetails = deliveryDetails.split("/by")[0].trim();
        if (taskDetails.isEmpty()) {
            throw new SpongebobException("What delivery order would you like to make?");
        }
        String deliveryDeadline = deliveryDetails.split("/by")[1].trim();

        // Check for valid date time format
        try {
            LocalDateTime deliverBy = LocalDateTime.parse(deliveryDeadline,
                    KrustyKrabTaskList.DATE_TIME_FORMATTER);
            KrustyKrabDelivery newDelivery = new KrustyKrabDelivery(taskDetails, deliverBy);
            this.krustyKrabOrderList.add(newDelivery);
            String displayString = "Krabby Patty Delivery scheduled!\n" + newDelivery.toString();

            try {
                KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
                displayString += "\nTasks saved successfully!";
            } catch (SpongebobException e) {
                displayString += String.format("\n%s", e.getMessage());
            }
            guiWindow.displaySpongebobResponse(displayString);
        } catch (DateTimeParseException e) {
            throw new SpongebobException("Please enter the delivery deadline in the format dd-MM-yyyy HH:mm.");
        }

    }

    /**
     * Adds a new Krusty Krab reservation to the task list.
     * 
     * @param reservationDetails Details of the reservation to be added.
     * @param guiWindow          The main GUI window to display the results in.
     * @throws SpongebobException             If there is an error with the
     *                                        reservation details.
     * @throws ArrayIndexOutOfBoundsException If the reservation details are not
     *                                        inthe expected format.
     */
    public void addReservation(String reservationDetails, MainWindow guiWindow)
            throws SpongebobException, ArrayIndexOutOfBoundsException {
        // Retrieve reservation details
        String taskDetails = reservationDetails.split("/from")[0].trim();
        if (taskDetails.isEmpty()) {
            throw new SpongebobException("What reservation would you like to make?");
        }
        String reservationStartTime = reservationDetails.split("/from")[1].split("/to")[0].trim();
        String reservationEndTime = reservationDetails.split("/to")[1].trim();

        // Check for valid date time format
        try {
            LocalDateTime startTime = LocalDateTime.parse(reservationStartTime,
                    KrustyKrabTaskList.DATE_TIME_FORMATTER);
            LocalDateTime endTime = LocalDateTime.parse(reservationEndTime,
                    KrustyKrabTaskList.DATE_TIME_FORMATTER);
            if (endTime.isBefore(startTime)) {
                throw new SpongebobException("What do you mean your reservation ends before it starts?");
            }

            // If valid, add reservation to list
            KrustyKrabReservation newReservation = new KrustyKrabReservation(taskDetails, startTime, endTime);
            this.krustyKrabOrderList.add(newReservation);
            String displayString = "Krusty Krab Reservation made!\n" + newReservation.toString();

            try {
                KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
                displayString += "\nTasks saved successfully!";
            } catch (SpongebobException e) {
                displayString += "\nAn error occurred while saving tasks.";
            }
            guiWindow.displaySpongebobResponse(displayString);
        } catch (DateTimeParseException e) {
            throw new SpongebobException("Please enter the reservation time in the format dd-MM-yyyy HH:mm.");
        }
    }

    /**
     * Deletes a Krusty Krab task from the task list based on the provided index.
     * 
     * @param index     The index of the task to be deleted.
     * @param guiWindow The main GUI window to display the results in.
     */
    public void deleteTask(int index, MainWindow guiWindow) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.remove(index);
            String displayString = "Task removed!\n" + task.toString();

            try {
                KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
                displayString += "\nTasks saved successfully!";
            } catch (SpongebobException e) {
                displayString += "\nAn error occurred while saving tasks.";
            }
            guiWindow.displaySpongebobResponse(displayString);
        } else {
            String displayString = "Which task are you referring to?";
            guiWindow.displaySpongebobResponse(displayString);
        }
    }

    /**
     * Marks a Krusty Krab task as completed based on the provided index.
     * 
     * @param index     The index of the task to be marked as completed.
     * @param guiWindow The main GUI window to display the results in.
     */
    public void markTask(int index, MainWindow guiWindow) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (!task.isCompleted()) {
                task.markComplete();
                String displayString = "Task complete!\n" + task.toString();

                try {
                    KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
                    displayString += "\nTasks saved successfully!";
                } catch (SpongebobException e) {
                    displayString += "\nAn error occurred while saving tasks.";
                }
                guiWindow.displaySpongebobResponse(displayString);
            } else {
                String displayString = "This task is already completed!\n" + task.toString();
                guiWindow.displaySpongebobResponse(displayString);
            }
        } else {
            String displayString = "Which task are you referring to?";
            guiWindow.displaySpongebobResponse(displayString);
        }
    }

    /**
     * Unmarks a Krusty Krab task as completed based on the provided index.
     * 
     * @param index     The index of the task to be unmarked as completed.
     * @param guiWindow The main GUI window to display the results in.
     */
    public void unmarkTask(int index, MainWindow guiWindow) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (task.isCompleted()) {
                task.markIncomplete();
                String displayString = "Task cancelled!\n" + task.toString();

                try {
                    KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
                    displayString += "\nTasks saved successfully!";
                } catch (SpongebobException e) {
                    displayString += "\nAn error occurred while saving tasks.";
                }
                guiWindow.displaySpongebobResponse(displayString);
            } else {
                String displayString = "This task is not completed yet!\n" + task.toString();
                guiWindow.displaySpongebobResponse(displayString);
            }
        } else {
            String displayString = "Which task are you referring to?";
            guiWindow.displaySpongebobResponse(displayString);
        }
    }

    /**
     * Finds and displays tasks that match the given keyword.
     * 
     * @param keyword   The keyword to search for in the task list.
     * @param guiWindow The main GUI window to display the results in.
     */
    public void findTasks(String keyword, MainWindow guiWindow) {
        String displayString = "Here are the matching tasks in your Krusty Krab task list:";

        boolean anyFound = false;
        for (KrustyKrabTask task : this.krustyKrabOrderList) {
            if (task.matchesKeyword(keyword)) {
                anyFound = true;
                displayString += '\n' + task.toString();
            }
        }

        if (!anyFound) {
            displayString += '\n' + "...No matching tasks found, are you sure you made such a request?";
        }

        guiWindow.displaySpongebobResponse(displayString);
    }
}
