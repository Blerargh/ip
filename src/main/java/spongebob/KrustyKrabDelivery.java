package spongebob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KrustyKrabDelivery extends KrustyKrabTask {
    LocalDateTime deliveryDeadline;

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
