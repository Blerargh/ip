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

/**
 * Manages the list of Krusty Krab tasks including orders, deliveries, and
 * reservations.
 * Manages the addition, deletion, marking, and unmarking of tasks.
 * Interacts with KrustyKrabTaskStorage for saving and loading tasks.
 */
public class KrustyKrabTaskList {
    private ArrayList<KrustyKrabTask> krustyKrabOrderList = new ArrayList<>();

    /**
     * Prints the list of Krusty Krab tasks to the console.
     */
    public void printTasks() {
        if (this.krustyKrabOrderList.isEmpty()) {
            System.out.println("How about making a request first?");
        } else {
            for (int i = 0; i < this.krustyKrabOrderList.size(); i++) {
                String str = "Krabby Patty Task " + (i + 1) + ": ";
                KrustyKrabTask order = this.krustyKrabOrderList.get(i);
                System.out.println(str + order.toString());
            }
        }
    }

    /**
     * Adds a new Krusty Krab order to the task list.
     * 
     * @param orderDetails Details of the order to be added.
     */
    public void addOrder(String orderDetails) {
        // Retrieve order details
        String taskDetails = orderDetails.trim();
        KrustyKrabOrder newOrder = new KrustyKrabOrder(taskDetails);
        this.krustyKrabOrderList.add(newOrder);
        System.out.println("Krabby Patty Order received!\n" + newOrder.toString());
        KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
    }

    /**
     * Adds a new Krusty Krab delivery to the task list.
     * 
     * @param deliveryDetails Details of the delivery to be added.
     * @throws SpongebobException             If there is an error with the delivery
     *                                        details.
     * @throws ArrayIndexOutOfBoundsException If the delivery details are not in the
     *                                        expected format.
     */
    public void addDelivery(String deliveryDetails) throws SpongebobException, ArrayIndexOutOfBoundsException {
        // Retrieve delivery details
        String taskDetails = deliveryDetails.split("/by")[0].trim();
        if (taskDetails.isEmpty()) {
            throw new SpongebobException("What delivery order would you like to make?");
        }
        String deliveryDeadline = deliveryDetails.split("/by")[1].trim();

        // Check for valid date time format
        try {
            LocalDateTime deliverBy = LocalDateTime.parse(deliveryDeadline,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            KrustyKrabDelivery newDelivery = new KrustyKrabDelivery(taskDetails, deliverBy);
            this.krustyKrabOrderList.add(newDelivery);
            System.out.println("Krabby Patty Delivery scheduled!\n" + newDelivery.toString());
            KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        } catch (DateTimeParseException e) {
            throw new SpongebobException("Please enter the delivery deadline in the format dd-MM-yyyy HH:mm.");
        }

    }

    /**
     * Adds a new Krusty Krab reservation to the task list.
     * 
     * @param reservationDetails Details of the reservation to be added.
     * @throws SpongebobException             If there is an error with the
     *                                        reservation details.
     * @throws ArrayIndexOutOfBoundsException If the reservation details are not
     *                                        inthe expected format.
     */
    public void addReservation(String reservationDetails) throws SpongebobException, ArrayIndexOutOfBoundsException {
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
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            LocalDateTime endTime = LocalDateTime.parse(reservationEndTime,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            if (endTime.isBefore(startTime)) {
                throw new SpongebobException("What do you mean your reservation ends before it starts?");
            }

            // If valid, add reservation to list
            KrustyKrabReservation newReservation = new KrustyKrabReservation(taskDetails, startTime, endTime);
            this.krustyKrabOrderList.add(newReservation);
            System.out.println("Krusty Krab Reservation made!\n" + newReservation.toString());
            KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        } catch (DateTimeParseException e) {
            throw new SpongebobException("Please enter the reservation time in the format dd-MM-yyyy HH:mm.");
        }
    }

    /**
     * Deletes a Krusty Krab task from the task list based on the provided index.
     * 
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.remove(index);
            System.out.println("Task removed!\n" + task.toString());
            KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        } else {
            System.out.println("Which task are you referring to?");
        }
    }

    /**
     * Marks a Krusty Krab task as completed based on the provided index.
     * 
     * @param index The index of the task to be marked as completed.
     */
    public void markTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (!task.isCompleted()) {
                task.complete();
                System.out.println("Task complete!\n" + task.toString());
                KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
            } else {
                System.out.println("This task is already completed!\n" + task.toString());
            }
        } else {
            System.out.println("Which task are you referring to?");
        }
    }

    /**
     * Unmarks a Krusty Krab task as completed based on the provided index.
     * 
     * @param index The index of the task to be unmarked as completed.
     */
    public void unmarkTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (task.isCompleted()) {
                task.cancel();
                System.out.println("Task cancelled!\n" + task.toString());
                KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
            } else {
                System.out.println("This task is not completed yet!\n" + task.toString());
            }
        } else {
            System.out.println("Which task are you referring to?");
        }
    }

    public void findTasks(String keyword) {
        System.out.println("Here are the matching tasks in your Krusty Krab task list:");

        boolean anyFound = false;
        for (KrustyKrabTask task : this.krustyKrabOrderList) {
            if (task.matchesKeyword(keyword)) {
                anyFound = true;
                System.out.println(task.toString());
            }
        }

        if (!anyFound) {
            System.out.println("...No matching tasks found, are you sure you made such a request?");
        }
    }
}
