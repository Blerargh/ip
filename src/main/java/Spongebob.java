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

            try {
                Actions action;
                if (userInput.trim().isEmpty()) {
                    action = Actions.EMPTY;
                } else {
                    action = Actions.valueOf(userInput.trim().toUpperCase());
                }

                // Handle order
                switch (action) {
                    case EMPTY:
                        System.out.println("Did you say something?");
                        break;
                    case LIST:
                        spongebob.printOrders();
                        break;
                    case MARK:
                        // tbi
                        break;
                    case UNMARK:
                        // tbi
                        break;
                    case BYE:
                        System.out.println("Goodbye! Have a great day under the sea!");
                        break;
                }
                if (action.equals(Actions.BYE)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                KrustyKrabOrder newOrder = new KrustyKrabOrder(userInput);
                spongebob.krustyKrabOrderList.add(newOrder);
                System.out.println("Krabby Patty order received!");
            } finally {
                printHorizontalLine();
            }            
        }
        scanner.close();
    }
}
