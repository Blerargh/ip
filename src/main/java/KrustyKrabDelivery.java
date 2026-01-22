public class KrustyKrabDelivery extends KrustyKrabTask {
    String deliveryTime;

    public KrustyKrabDelivery(String delivery, String deliveryTime) {
        super(delivery);
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        String str = "[D]";
        if (this.isCompleted()) {
            str += "[X] " + this.getTaskName();
        } else {
            str += "[ ] " + this.getTaskName();
        }
        str += " (by: " + this.deliveryTime + ")";
        return str;
    }
}