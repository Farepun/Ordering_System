class Order {
    private String orderId;
    private String vehicle;
    private int days;
    private int totalPrice;

    public Order(String orderId, String vehicle, int days, int totalPrice) {
        this.orderId = orderId;
        this.vehicle = vehicle;
        this.days = days;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + "\nVehicle: " + vehicle + "\nDays: " + days + "\nTotal Price: $" + totalPrice;
    }
}
