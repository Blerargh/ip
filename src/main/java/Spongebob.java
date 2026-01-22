import java.util.Scanner;
import java.util.ArrayList;

public class Spongebob {
    private ArrayList<KrustyKrabOrder> krustyKrabOrderList = new ArrayList<>();

    public void printOrders() {
        if (this.krustyKrabOrderList.isEmpty()) {
            System.out.println("How about ordering a Krabby Patty first?");
        } else {
            for (int i = 0; i < this.krustyKrabOrderList.size(); i++) {
                String str = "Krabby Patty Order " + (i + 1) + ": ";
                KrustyKrabOrder order = this.krustyKrabOrderList.get(i);
                if (order.isCompleted()) {
                    str += "[X] " + order.getOrder();
                } else {
                    str += "[ ] " + order.getOrder();
                }
                System.out.println(str);
            }
        }
    }

    public void markOrder(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabOrder order = this.krustyKrabOrderList.get(index);
            if (!order.isCompleted()) {
                order.complete();
                System.out.println("Order complete!\n[X] " + order.getOrder());
            } else {
                System.out.println("This order is already completed!\n[X] " + order.getOrder());
            }
        } else {
            System.out.println("Did you order this?");
        }
    }

    public void unmarkOrder(int index) {
        if (index >= 0 && index < this.krustyKrabOrderList.size()) {
            KrustyKrabOrder order = this.krustyKrabOrderList.get(index);
            if (order.isCompleted()) {
                order.cancel();
                System.out.println("Order cancelled!\n[ ] " + order.getOrder());
            } else {
                System.out.println("This order is not completed yet!\n[ ] " + order.getOrder());
            }
        } else {
            System.out.println("Which order are you referring to?");
        }
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
            printHorizontalLine();
            Actions action = Actions.fromString(userInput);
            switch (action) {
                case LIST:
                    spongebob.printOrders();
                    break;
                case ADD:
                    spongebob.krustyKrabOrderList.add(new KrustyKrabOrder(userInput));
                    System.out.println("Krabby Patty order received!\n[ ] " + userInput);
                    break;
                case BYE:
                    System.out.println("Goodbye! Have a great day under the sea!");
                    printHorizontalLine();
                    scanner.close();
                    return;
                case MARK:
                    int indexToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    spongebob.markOrder(indexToMark);
                    break;
                case UNMARK:
                    int indexToUnmark = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    spongebob.unmarkOrder(indexToUnmark);
                    break;
                case MARK_ERROR:
                    System.out.println("Which order are you referring to?");
                    break;
                case UNMARK_ERROR:
                    System.out.println("I cannot fulfill this order cancellation request!");
                    break;
                case EMPTY:
                    System.out.println("Did you order something?");
                    break;
            }
            printHorizontalLine();
        }
    }
}
