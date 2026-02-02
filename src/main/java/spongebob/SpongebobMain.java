package spongebob;

import java.util.Scanner;

import spongebob.parser.ActionParser;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.tasklistmanager.KrustyKrabTaskStorage;
import spongebob.ui.SpongebobMainUi;

public class SpongebobMain {
    private SpongebobMainUi ui = new SpongebobMainUi();

    public void run() {
        KrustyKrabTaskList taskList = KrustyKrabTaskStorage.loadTasks();
        this.ui.printWelcomeMessage();

        // Main loop to process user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            userInput = userInput.trim();

            this.ui.printHorizontalLine();
            ActionParser action = ActionParser.fromString(userInput);
            ActionParser.executeAction(action, taskList, userInput);

            if (action.equals(ActionParser.BYE)) {
                this.ui.printGoodbyeMessage();
                break;
            }
            this.ui.printHorizontalLine();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new SpongebobMain().run();
    }
}
