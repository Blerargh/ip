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

public class KrustyKrabTaskList {
    private ArrayList<KrustyKrabTask> krustyKrabOrderList = new ArrayList<>();

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

    public void addOrder(String orderDetails) {
        // Retrieve order details
        String taskDetails = orderDetails.trim();
        KrustyKrabOrder newOrder = new KrustyKrabOrder(taskDetails);
        this.krustyKrabOrderList.add(newOrder);
        System.out.println("Krabby Patty Order received!\n" + newOrder.toString());
        KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
    }

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

    public void deleteTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.remove(index);
            System.out.println("Task removed!\n" + task.toString());
            KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
        } else {
            System.out.println("Which task are you referring to?");
        }
    }

    public void markTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (!task.isCompleted()) {
                task.markComplete();
                System.out.println("Task complete!\n" + task.toString());
                KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
            } else {
                System.out.println("This task is already completed!\n" + task.toString());
            }
        } else {
            System.out.println("Which task are you referring to?");
        }
    }

    public void unmarkTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (task.isCompleted()) {
                task.markIncomplete();
                System.out.println("Task cancelled!\n" + task.toString());
                KrustyKrabTaskStorage.saveTasks(this.krustyKrabOrderList);
            } else {
                System.out.println("This task is not completed yet!\n" + task.toString());
            }
        } else {
            System.out.println("Which task are you referring to?");
        }
    }
}
