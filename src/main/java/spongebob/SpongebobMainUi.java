package spongebob;

import java.util.Scanner;

public class SpongebobMainUi {
    public static void printHorizontalLine() {
        System.out.println("_________________________________________________________");
    }

    public static void main(String[] args) {
        // Initialisation
        KrustyKrabTaskList taskList = KrustyKrabTaskStorage.loadTasks();

        // Greeting message
        SpongebobMainUi.printHorizontalLine();
        System.out.println("Hello, I'm Spongebob Squarepants!\nWhat can I do for you at the Krusty Krab today?");
        SpongebobMainUi.printHorizontalLine();

        // Main loop to process user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            userInput = userInput.trim();

            SpongebobMainUi.printHorizontalLine();
            ActionParser action = ActionParser.fromString(userInput);
            ActionParser.executeAction(action, taskList, userInput);
            SpongebobMainUi.printHorizontalLine();

            if (action.equals(ActionParser.BYE)) {
                break;
            }
        }
        scanner.close();
    }
}
