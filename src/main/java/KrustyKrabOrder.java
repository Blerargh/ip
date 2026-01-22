public class KrustyKrabOrder {
    private String order;
    private boolean isCompleted;

    public KrustyKrabOrder(String order) {
        this.order = order;
        this.isCompleted = false;
    }

    public String getOrder() {
        return this.order;
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
}
