import java.util.Scanner;

public class Spongebob {
    public static void printHorizontalLine() {
        System.out.println("_________________________________________________________");
    }
    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("Hello, I'm Spongebob Squarepants!\nWhat can I do for you today?");
        printHorizontalLine();
        
        // Read user input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        
        // Respond to user input
        printHorizontalLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            printHorizontalLine();
            userInput = scanner.nextLine();
            printHorizontalLine();
        }
        System.out.println("Goodbye! Have a great day under the sea!");
        printHorizontalLine();
        scanner.close();
    }
}
