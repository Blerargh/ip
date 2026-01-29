package spongebob;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Spongebob {
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

    public void saveTasks() {
        Path path = Paths.get("src/main/java/data");
        try {
            Files.createDirectories(path);
            FileWriter fileWriter = new FileWriter("src/main/java/data/orders.txt");
            for (KrustyKrabTask task : this.krustyKrabOrderList) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    public void markTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (!task.isCompleted()) {
                task.complete();
                System.out.println("Task complete!\n" + task.toString());
                this.saveTasks();
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
                task.cancel();
                System.out.println("Task cancelled!\n" + task.toString());
                this.saveTasks();
            } else {
                System.out.println("This task is not completed yet!\n" + task.toString());
            }
        } else {
            System.out.println("Which task are you referring to?");
        }
    }

    public void addOrder(String orderDetails) {
        String taskDetails = orderDetails.trim();
        KrustyKrabOrder newOrder = new KrustyKrabOrder(taskDetails);
        this.krustyKrabOrderList.add(newOrder);
        System.out.println("Krabby Patty Order received!\n" + newOrder.toString());
        this.saveTasks();
    }

    public void addDelivery(String deliveryDetails) throws SpongebobException, ArrayIndexOutOfBoundsException {
        String taskDetails = deliveryDetails.split("/by")[0].trim();
        if (taskDetails.isEmpty()) {
            throw new SpongebobException("What delivery order would you like to make?");
        }

        String deliveryDeadline = deliveryDetails.split("/by")[1].trim();
        KrustyKrabDelivery newDelivery = new KrustyKrabDelivery(taskDetails, deliveryDeadline);
        this.krustyKrabOrderList.add(newDelivery);
        System.out.println("Krabby Patty Delivery scheduled!\n" + newDelivery.toString());
        this.saveTasks();
    }

    public void addReservation(String reservationDetails) throws SpongebobException, ArrayIndexOutOfBoundsException {
        String taskDetails = reservationDetails.split("/from")[0].trim();
        if (taskDetails.isEmpty()) {
            throw new SpongebobException("What reservation would you like to make?");
        }
        String reservationStartTime = reservationDetails.split("/from")[1].split("/to")[0].trim();
        String reservationEndTime = reservationDetails.split("/to")[1].trim();
        KrustyKrabReservation newReservation = new KrustyKrabReservation(taskDetails, reservationStartTime,
                reservationEndTime);
        this.krustyKrabOrderList.add(newReservation);
        System.out.println("Krusty Krab Reservation made!\n" + newReservation.toString());
        this.saveTasks();
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.remove(index);
            System.out.println("Task removed!\n" + task.toString());
            this.saveTasks();
        } else {
            System.out.println("Which task are you referring to?");
        }
    }

    public static void printHorizontalLine() {
        System.out.println("_________________________________________________________");
    }

    public static void main(String[] args) {
        Spongebob spongebob = new Spongebob();

        Spongebob.printHorizontalLine();
        System.out.println("Hello, I'm Spongebob Squarepants!\nWhat can I do for you at the Krusty Krab today?");
        Spongebob.printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            userInput = userInput.trim();
            Spongebob.printHorizontalLine();
            Actions action = Actions.fromString(userInput);
            switch (action) {
                case LIST:
                    spongebob.printTasks();
                    break;
                case BYE:
                    System.out.println("Goodbye! Have a great day under the sea!");
                    Spongebob.printHorizontalLine();
                    scanner.close();
                    return;
                case MARK:
                    int indexToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    spongebob.markTask(indexToMark);
                    break;
                case UNMARK:
                    int indexToUnmark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    spongebob.unmarkTask(indexToUnmark);
                    break;
                case MARK_ERROR:
                    System.out.println("Which task are you referring to?");
                    break;
                case UNMARK_ERROR:
                    System.out.println("Which task are you referring to?");
                    break;
                case ADD_ORDER:
                    try {
                        String orderDetails = userInput.substring(6);
                        spongebob.addOrder(orderDetails);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("What Krabby Patty order would you like to make?");
                    }
                    break;
                case ADD_DELIVERY:
                    try {
                        String deliveryDetails = userInput.substring(9);
                        spongebob.addDelivery(deliveryDetails);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("What delivery order would you like to make?");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("By when should I deliver the Krabby Patty?");
                    } catch (SpongebobException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ADD_RESERVATION:
                    try {
                        String reservationDetails = userInput.substring(12);
                        spongebob.addReservation(reservationDetails);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("What reservation would you like to make?");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("From when to when do you want to reserve the Krusty Krab?");
                    } catch (SpongebobException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DELETE:
                    int indexToDelete = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    spongebob.deleteTask(indexToDelete);
                    break;
                case DELETE_ERROR:
                    System.out.println("Which task are you referring to?");
                    break;
                case ERROR:
                    System.out.println("What are you saying, Mr. Krabs?");
                    break;
                case EMPTY:
                    System.out.println("Did you make a request?");
                    break;
            }
            Spongebob.printHorizontalLine();
        }
    }
}
