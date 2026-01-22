import java.util.Scanner;
import java.util.ArrayList;

public class Spongebob {
    private ArrayList<String> krustyKrabOrderList = new ArrayList<>();

    enum Actions {
        LIST,
        BYE,
    }

    public static void printOrders(ArrayList<String> krustyKrabOrderList) {
        if (krustyKrabOrderList.isEmpty()) {
            System.out.println("How about ordering a Krabby Patty first?");
        } else {
            for (int i = 0; i < krustyKrabOrderList.size(); i++) {
                System.out.println("Krabby Patty Order " + (i + 1) + ": " + krustyKrabOrderList.get(i));
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
            if (upperCaseUserInput.equals(Actions.LIST.name())) {
                printOrders(spongebob.krustyKrabOrderList);
            } else if (upperCaseUserInput.equals(Actions.BYE.name())) {
                break;
            } else {
                spongebob.krustyKrabOrderList.add(userInput);
                System.out.println("Krabby Patty order received!");
            }
            printHorizontalLine();
        }

        System.out.println("Goodbye! Have a great day under the sea!");
        printHorizontalLine();
        scanner.close();
    }
}
