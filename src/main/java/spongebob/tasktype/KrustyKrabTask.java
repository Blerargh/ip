package spongebob.tasktype;

/**
 * Represents the general task at the Krusty Krab.
 * This is the parent class for specific task types like Order, Delivery, and
 * Reservation.
 */
public class KrustyKrabTask {
    private String taskName;
    private boolean isCompleted;

    public KrustyKrabTask(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public void cancel() {
        this.isCompleted = false;
    }

    public boolean matchesKeyword(String keyword) {
        return this.taskName.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        String str = "";
        if (this.isCompleted) {
            str += "[X] " + this.taskName;
        } else {
            str += "[ ] " + this.taskName;
        }
        return str;
    }
}
