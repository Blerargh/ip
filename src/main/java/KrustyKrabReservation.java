public class KrustyKrabReservation extends KrustyKrabTask {
    private String startTime;
    private String endTime;

    public KrustyKrabReservation(String reservation, String startTime, String endTime) {
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
        str += " (from: " + this.startTime + " to: " + this.endTime + ")";
        return str;
    }
}
