package spongebob.tasktype;

/**
 * Represents an order task at the Krusty Krab.
 */
public class KrustyKrabOrder extends KrustyKrabTask {
    public KrustyKrabOrder(String order) {
        super(order);
    }

    @Override
    public String toString() {
        String str = "[O]";
        if (this.isCompleted()) {
            str += "[X] " + this.getTaskName();
        } else {
            str += "[ ] " + this.getTaskName();
        }
        return str;
    }
}
