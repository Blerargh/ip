package spongebob.tasklistmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import spongebob.commands.AddTaskCommand;
import spongebob.commands.KrustyKrabTaskCommand;
import spongebob.commands.subcommands.DeleteCommand;
import spongebob.commands.subcommands.MarkCommand;
import spongebob.commands.subcommands.UnmarkCommand;
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
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private ArrayList<KrustyKrabTask> krustyKrabOrderList = new ArrayList<>();
    private ArrayList<KrustyKrabTaskCommand> undoCommands = new ArrayList<>();

    /**
     * Adds a general Krusty Krab task to the task list.
     * Reserved for internal use with loading tasks from storage.
     *
     * @param task The Krusty Krab task to be added to the task list.
     */
    public void addTask(int taskIndex, KrustyKrabTask task) {
        this.krustyKrabOrderList.add(taskIndex, task);
    }

    /**
     * Returns the number of tasks currently in the Krusty Krab task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return this.krustyKrabOrderList.size();
    }

    /**
     * Prints the list of Krusty Krab tasks to the console.
     *
     * @param guiWindow The main GUI window to display the results in.
     */
    public void printTasks(MainWindow guiWindow) {
        assert guiWindow != null : "MainWindow guiWindow should not be null";

        String displayString = "";

        displayString += "Here are the tasks in your Krusty Krab task list:";
        for (int i = 0; i < this.krustyKrabOrderList.size(); i++) {
            displayString += "\nKrabby Patty Task " + (i + 1) + ": ";
            KrustyKrabTask order = this.krustyKrabOrderList.get(i);
            displayString += order.toString();
        }

        if (this.krustyKrabOrderList.isEmpty()) {
            displayString += "\n...Seems like your Krusty Krab task list is empty, how about making a request first?";
        }

        guiWindow.displaySpongebobResponse(displayString);
    }

    /**
     * Adds a new Krusty Krab order to the task list.
     *
     * @param orderDetails Details of the order to be added.
     * @param guiWindow    The main GUI window to display the results in.
     * @throws SpongebobException If there is an error with saving the tasks.
     */
    public void addOrder(String orderDetails, MainWindow guiWindow, boolean isUndo) throws SpongebobException {
        assert orderDetails != null : "Order details string should not be null";
        assert guiWindow != null : "MainWindow guiWindow should not be null";

        // Retrieve order details
        String taskDetails = orderDetails.trim();
        KrustyKrabOrder newOrder = new KrustyKrabOrder(taskDetails);
        this.krustyKrabOrderList.add(newOrder);
        String displayString = "Krabby Patty Order received!\n" + newOrder.toString();

        KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        displayString += "\nTasks saved successfully!";

        if (!isUndo) {
            guiWindow.displaySpongebobResponse(displayString);
            this.undoCommands.add(new DeleteCommand(String.valueOf(this.krustyKrabOrderList.size() - 1)));
        }
    }

    /**
     * Adds a new Krusty Krab delivery to the task list.
     *
     * @param deliveryDetails Details of the delivery to be added.
     * @param guiWindow       The main GUI window to display the results in.
     * @throws SpongebobException If there is an error with the delivery
     *                            details.
     */
    public void addDelivery(String deliveryDetails, MainWindow guiWindow, boolean isUndo) throws SpongebobException {
        assert deliveryDetails != null : "Delivery details string should not be null";
        assert guiWindow != null : "MainWindow guiWindow should not be null";

        // Retrieve delivery details
        String[] partDetails = deliveryDetails.split("/by");
        String taskDetails = partDetails[0].trim();
        if (taskDetails.isEmpty()) {
            throw new SpongebobException("What delivery order would you like to make?");
        }
        String deliveryDeadline = partDetails[1].trim();

        // Check for valid date time format
        LocalDateTime deliverBy;
        deliverBy = this.parseDateTime(deliveryDeadline);

        // If valid, add delivery to list
        KrustyKrabDelivery newDelivery = new KrustyKrabDelivery(taskDetails, deliverBy);
        this.krustyKrabOrderList.add(newDelivery);
        String displayString = "Krabby Patty Delivery scheduled!\n" + newDelivery.toString();

        KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        displayString += "\nTasks saved successfully!";

        if (!isUndo) {
            guiWindow.displaySpongebobResponse(displayString);
            this.undoCommands.add(new DeleteCommand(String.valueOf(this.krustyKrabOrderList.size() - 1)));
        }
    }

    /**
     * Adds a new Krusty Krab reservation to the task list.
     *
     * @param reservationDetails Details of the reservation to be added.
     * @param guiWindow          The main GUI window to display the results in.
     * @throws SpongebobException If there is an error with the
     *                            reservation details.
     */
    public void addReservation(String reservationDetails, MainWindow guiWindow, boolean isUndo)
            throws SpongebobException {
        assert reservationDetails != null : "Reservation details string should not be null";
        assert guiWindow != null : "MainWindow guiWindow should not be null";

        // Retrieve reservation details
        String[] partDetailsSplitByFrom = reservationDetails.split("/from");
        String taskDetails = partDetailsSplitByFrom[0].trim();
        if (taskDetails.isEmpty()) {
            throw new SpongebobException("What reservation would you like to make?");
        }

        String[] partDetailsSplitByTo = partDetailsSplitByFrom[1].split("/to");
        String reservationStartTime = partDetailsSplitByTo[0].trim();
        String reservationEndTime = partDetailsSplitByTo[1].trim();

        // Check for valid date time format
        LocalDateTime startTime = this.parseDateTime(reservationStartTime);
        LocalDateTime endTime = this.parseDateTime(reservationEndTime);

        if (endTime.isBefore(startTime)) {
            throw new SpongebobException("What do you mean your reservation ends before it starts?");
        }

        // If valid, add reservation to list
        KrustyKrabReservation newReservation = new KrustyKrabReservation(taskDetails, startTime, endTime);
        this.krustyKrabOrderList.add(newReservation);
        String displayString = "Krusty Krab Reservation made!\n" + newReservation.toString();

        KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        displayString += "\nTasks saved successfully!";

        if (!isUndo) {
            guiWindow.displaySpongebobResponse(displayString);
            this.undoCommands.add(new DeleteCommand(String.valueOf(this.krustyKrabOrderList.size() - 1)));
        }
    }

    /**
     * Deletes a Krusty Krab task from the task list based on the provided index.
     *
     * @param index     The index of the task to be deleted.
     * @param guiWindow The main GUI window to display the results in.
     * @throws SpongebobException If the provided index is out of bounds.
     */
    public void deleteTask(int index, MainWindow guiWindow, boolean isUndo) throws SpongebobException {
        assert guiWindow != null : "MainWindow guiWindow should not be null";

        String displayString = "";
        KrustyKrabTask task = this.getTask(index);
        this.krustyKrabOrderList.remove(index);
        displayString += "Task removed!\n" + task.toString();

        KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        displayString += "\nTasks saved successfully!";

        if (!isUndo) {
            guiWindow.displaySpongebobResponse(displayString);
            this.undoCommands.add(new AddTaskCommand(index, task));
        }
    }

    /**
     * Marks a Krusty Krab task as completed based on the provided index.
     *
     * @param index     The index of the task to be marked as completed.
     * @param guiWindow The main GUI window to display the results in.
     * @throws SpongebobException If the provided index is out of bounds.
     */

    public void markTask(int index, MainWindow guiWindow, boolean isUndo) throws SpongebobException {
        String displayString = "";
        KrustyKrabTask task = this.getTask(index);

        if (!task.isCompleted()) {
            task.markComplete();
            displayString += "Task complete!\n" + task.toString();

            KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
            displayString += "\nTasks saved successfully!";
        } else {
            displayString += "This task is already completed!\n" + task.toString();
        }

        if (!isUndo) {
            guiWindow.displaySpongebobResponse(displayString);
            this.undoCommands.add(new UnmarkCommand(String.valueOf(index + 1)));
        }
    }

    /**
     * Unmarks a Krusty Krab task as completed based on the provided index.
     *
     * @param index     The index of the task to be unmarked as completed.
     * @param guiWindow The main GUI window to display the results in.
     * @throws SpongebobException If the provided index is out of bounds.
     */

    public void unmarkTask(int index, MainWindow guiWindow, boolean isUndo) throws SpongebobException {
        String displayString = "";
        KrustyKrabTask task = this.getTask(index);

        if (task.isCompleted()) {
            task.markIncomplete();
            displayString += "Task cancelled!\n" + task.toString();

            KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
            displayString += "\nTasks saved successfully!";
        } else {
            displayString += "This task is not completed yet!\n" + task.toString();
        }

        if (!isUndo) {
            guiWindow.displaySpongebobResponse(displayString);
            this.undoCommands.add(new MarkCommand(String.valueOf(index + 1)));
        }
    }

    /**
     * Finds and displays tasks that match the given keyword.
     *
     * @param keyword   The keyword to search for in the task list.
     * @param guiWindow The main GUI window to display the results in.
     */
    public void findTasks(String keyword, MainWindow guiWindow) {
        assert keyword != null : "Keyword string should not be null";
        assert guiWindow != null : "MainWindow guiWindow should not be null";

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

    /**
     * Undoes the last modification made to the task list, if any. This method will
     * execute the undo command corresponding to the last modification, which should
     * reverse the effect of that modification on the task list. If there are no
     * modifications to undo, an appropriate message will be displayed in the GUI
     * window indicating that there are no actions to undo.
     *
     * @param guiWindow The main GUI window to display the results in.
     * @throws SpongebobException If there is an error during the undo operation,
     *                            such as issues with the task list or executing the
     *                            undo command.
     */
    public void undoLastModification(MainWindow guiWindow) throws SpongebobException {
        assert guiWindow != null : "MainWindow guiWindow should not be null";

        String displayString = "";
        if (this.undoCommands.isEmpty()) {
            guiWindow.displaySpongebobResponse("There are no actions to undo!");
            return;
        }

        KrustyKrabTaskCommand undoCommand = this.undoCommands.remove(this.undoCommands.size() - 1);
        undoCommand.execute(guiWindow, this, true);
        displayString += "Last action undone successfully!";

        KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        displayString += "\nTasks saved successfully!";

        guiWindow.displaySpongebobResponse(displayString);
    }

    private KrustyKrabTask getTask(int index) throws SpongebobException {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            return this.krustyKrabOrderList.get(index);
        } else {
            throw new SpongebobException("Which task are you referring to?");
        }
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws SpongebobException {
        try {
            return LocalDateTime.parse(dateTimeString, KrustyKrabTaskList.DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SpongebobException("Please enter the date and time in the correct format: dd-MM-yyyy HH:mm.");
        }
    }
}
