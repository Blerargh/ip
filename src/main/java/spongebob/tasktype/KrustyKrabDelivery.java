package spongebob.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a delivery task at the Krusty Krab.
 */
public class KrustyKrabDelivery extends KrustyKrabTask {
    private LocalDateTime deliveryDeadline;

    /**
     * Constructor for a Krusty Krab delivery task.
     *
     * @param delivery         The details of the delivery.
     * @param deliveryDeadline The deadline for the delivery.
     */
    public KrustyKrabDelivery(String delivery, LocalDateTime deliveryDeadline) {
        super(delivery);
        this.deliveryDeadline = deliveryDeadline;
    }

    @Override
    public String toString() {
        String str = "[D]";
        if (this.isCompleted()) {
            str += "[X] " + this.getTaskName();
        } else {
            str += "[ ] " + this.getTaskName();
        }
        String formattedDeadline = this.deliveryDeadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        str += " (by: " + formattedDeadline + ")";
        return str;
    }
}
