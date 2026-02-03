package spongebob.tasktype;

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

    public void markComplete() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
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
