import java.time.LocalDate;

public class Order {
    private String OrderId;
    private Customer customer;
    private Vehicle vehicle;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;
    public Order(String orderId, Customer customer, Vehicle vehicle, LocalDate rentalStart, LocalDate rentalEnd) {
        OrderId = orderId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
        vehicle.setAvailable(false);
    }
    public String getOrderId() {
        return OrderId;
    }
    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public LocalDate getRentalStart() {
        return rentalStart;
    }
    public void setRentalStart(LocalDate rentalStart) {
        this.rentalStart = rentalStart;
    }
    public LocalDate getRentalEnd() {
        return rentalEnd;
    }
    public void setRentalEnd(LocalDate rentalEnd) {
        this.rentalEnd = rentalEnd;
    }

    public double RentCalculation(){
        long days = java.time.temporal.ChronoUnit.DAYS.between(rentalStart, rentalEnd);
        return days * vehicle.getPrice();
    }

    
}
