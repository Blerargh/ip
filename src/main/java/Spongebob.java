import java.util.Scanner;
import java.util.ArrayList;

public class Spongebob {
    private ArrayList<KrustyKrabTask> krustyKrabOrderList = new ArrayList<>();

    public void printOrders() {
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

    public void markTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (!task.isCompleted()) {
                task.complete();
                System.out.println("Task complete!\n" + task.toString());
            } else {
                System.out.println("This task is already completed!\n" + task.toString());
            }
        } else {
            System.out.println("Did you make this request?");
        }
    }

    public void unmarkTask(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabTask task = this.krustyKrabOrderList.get(index);
            if (task.isCompleted()) {
                task.cancel();
                System.out.println("Task cancelled!\n" + task.toString());
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
    }

    public void addDelivery(String deliveryDetails) {
        String taskDetails = deliveryDetails.split("/by")[0].trim();
        String deliveryDeadline = deliveryDetails.split("/by")[1].trim();
        KrustyKrabDelivery newDelivery = new KrustyKrabDelivery(taskDetails, deliveryDeadline);
        this.krustyKrabOrderList.add(newDelivery);
        System.out.println("Krabby Patty Delivery scheduled!\n" + newDelivery.toString());
    }

    public void addReservation(String reservationDetails) {
        String taskDetails = reservationDetails.split("/from")[0].trim();
        String reservationStartTime = reservationDetails.split("/from")[1].split("/to")[0].trim();
        String reservationEndTime = reservationDetails.split("/to")[1].trim();
        KrustyKrabReservation newReservation = 
                new KrustyKrabReservation(taskDetails, reservationStartTime, reservationEndTime);
        this.krustyKrabOrderList.add(newReservation);
        System.out.println("Krusty Krab Reservation made!\n" + newReservation.toString());
    }

    public static void printHorizontalLine() {
        System.out.println("_________________________________________________________");
    }

    public static void main(String[] args) {
        // Initialise Spongebob
        Spongebob spongebob = new Spongebob();
        
        // Greeting message
        printHorizontalLine();
        System.out.println("Hello, I'm Spongebob Squarepants!\nWhat can I do for you at the Krusty Krab today?");
        printHorizontalLine();

        // Parse user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            userInput = userInput.trim();
            printHorizontalLine();
            Actions action = Actions.fromString(userInput);
            switch (action) {
                case LIST:
                    spongebob.printOrders();
                    break;
                case ADD:
                    spongebob.krustyKrabOrderList.add(new KrustyKrabTask(userInput));
                    System.out.println("Krabby Patty task received!\n[ ] " + userInput);
                    break;
                case BYE:
                    System.out.println("Goodbye! Have a great day under the sea!");
                    printHorizontalLine();
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
                    System.out.println("I cannot fulfill this task cancellation request!");
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
                        System.out.println("By when should I deliver the order?");
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
                    }
                    break;
                case EMPTY:
                    System.out.println("Did you make this request?");
                    break;
            }
            printHorizontalLine();
        }
    }
}
