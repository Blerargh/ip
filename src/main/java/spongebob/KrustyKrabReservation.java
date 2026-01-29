package spongebob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KrustyKrabReservation extends KrustyKrabTask {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public KrustyKrabReservation(String reservation, LocalDateTime startTime, LocalDateTime endTime) {
        super(reservation);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String str = "[R]";
        if (this.isCompleted()) {
            str += "[X] " + this.getTaskName();
        } else {
            str += "[ ] " + this.getTaskName();
        }

        String formattedStartTime = this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String formattedEndTime = this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        str += " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
        return str;
    }
}
