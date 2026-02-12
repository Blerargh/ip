package spongebob.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a reservation task at the Krusty Krab.
 */
public class KrustyKrabReservation extends KrustyKrabTask {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor for a Krusty Krab reservation task.
     *
     * @param reservation The details of the reservation.
     * @param startTime   The start time of the reservation.
     * @param endTime     The end time of the reservation.
     */
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

        String formattedStartTime = this.startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        String formattedEndTime = this.endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        str += " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
        return str;
    }
}
