package spongebob;

import java.util.Scanner;

public class Spongebob {
    public static void printHorizontalLine() {
        System.out.println("_________________________________________________________");
    }

    public static void main(String[] args) {
        // Initialisation
        KrustyKrabTaskList taskList = new KrustyKrabTaskList();

        // Greeting message
        Spongebob.printHorizontalLine();
        System.out.println("Hello, I'm Spongebob Squarepants!\nWhat can I do for you at the Krusty Krab today?");
        Spongebob.printHorizontalLine();

        // Main loop to process user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            userInput = userInput.trim();
            Spongebob.printHorizontalLine();
            Actions action = Actions.fromString(userInput);
            switch (action) {
                case LIST:
                    taskList.printTasks();
                    break;
                case BYE:
                    System.out.println("Goodbye! Have a great day under the sea!");
                    Spongebob.printHorizontalLine();
                    scanner.close();
                    return;
                case MARK:
                    int indexToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    taskList.markTask(indexToMark);
                    break;
                case UNMARK:
                    int indexToUnmark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    taskList.unmarkTask(indexToUnmark);
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
                        taskList.addOrder(orderDetails);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("What Krabby Patty order would you like to make?");
                    }
                    break;
                case ADD_DELIVERY:
                    try {
                        String deliveryDetails = userInput.substring(9);
                        taskList.addDelivery(deliveryDetails);
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
                        taskList.addReservation(reservationDetails);
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
                    taskList.deleteTask(indexToDelete);
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
