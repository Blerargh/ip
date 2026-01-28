public class KrustyKrabDelivery extends KrustyKrabTask {
    String deliveryDeadline;

    public KrustyKrabDelivery(String delivery, String deliveryDeadline) {
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
        str += " (by: " + this.deliveryDeadline + ")";
        return str;
    }
}
