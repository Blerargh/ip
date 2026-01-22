import java.util.Scanner;
import java.util.ArrayList;

public class Spongebob {
    private ArrayList<KrustyKrabOrder> krustyKrabOrderList = new ArrayList<>();

    public void printOrders() {
        if (this.krustyKrabOrderList.isEmpty()) {
            System.out.println("How about ordering a Krabby Patty first?");
        } else {
            for (int i = 0; i < this.krustyKrabOrderList.size(); i++) {
                System.out.println("Krabby Patty Order " 
                        + (i + 1) + ": " 
                        + this.krustyKrabOrderList.get(i).getOrder());
            }
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

            String upperCaseUserInput = userInput.trim().toUpperCase();

            // Handle order
            if (upperCaseUserInput.isEmpty()) {
                System.out.println("Did you say something?");
            } else if (upperCaseUserInput.equals(Actions.ActionType.LIST.name())) {
                spongebob.printOrders();
            } else if (upperCaseUserInput.equals(Actions.ActionType.BYE.name())) {
                break;
            } else if (upperCaseUserInput.equals(Actions.ActionType.MARK.name())) {
                // tbi
            } else if (upperCaseUserInput.equals(Actions.ActionType.UNMARK.name())) {
                // tbi
            } else {
                KrustyKrabOrder newOrder = new KrustyKrabOrder(userInput);
                spongebob.krustyKrabOrderList.add(newOrder);
                System.out.println("Krabby Patty order received!");
            }
            printHorizontalLine();
        }

        System.out.println("Goodbye! Have a great day under the sea!");
        printHorizontalLine();
        scanner.close();
    }
}
