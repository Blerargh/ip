package spongebob.ui;

public class SpongebobMainUi {
    public void printHorizontalLine() {
        System.out.println("_________________________________________________________");
    }

    public void printWelcomeMessage() {
        this.printHorizontalLine();
        System.out.println("Hello, I'm Spongebob Squarepants!\nWhat can I do for you at the Krusty Krab today?");
        this.printHorizontalLine();
    }

    public void printGoodbyeMessage() {
        System.out.println("Goodbye! Have a great day under the sea!");
    }
}
