import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    public static void main(String[] args) {
        // Create a list of vehicles
        Vehicle[] cars = new Vehicle[5];
        cars[0] = new Vehicle("Honda", "Civic", 2023, true, 150);
        cars[1] = new Vehicle("Toyota", "Corolla", 2022, true, 130);
        cars[2] = new Vehicle("Ford", "Focus", 2021, false, 140);
        cars[3] = new Vehicle("Chevrolet", "Malibu", 2020, true, 160);
        cars[4] = new Vehicle("Nissan", "Altima", 2019, true, 120);

        // Create some customers using the provided details
        Customer[] customers = new Customer[3];
        customers[0] = new Customer("040318010901", "Alice Brown", "90 , Jalan Kelaman Abadi", "12141241f", "0127864624");
        customers[1] = new Customer("040318010902", "Bob White", "123 , Jalan Newville", "12341241g", "01174561283");
        customers[2] = new Customer("040318010903", "Charlie Green", "45 , Jalan Seaview", "12441241h", "0126817515");

        // Create the main frame
        JFrame frame = new JFrame("Car Rental System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Car Rental");
        JButton loginButton = new JButton("Login");

        // Style the title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Add components to the top panel
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(loginButton, BorderLayout.EAST);

        // Text area to display vehicles
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Panel for buttons (with FlowLayout for centering)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Buttons
        JButton viewAllButton = new JButton("View All Vehicles");
        JButton checkOrderButton = new JButton("Check Order ID");
        JButton rentVehicleButton = new JButton("Rent a Vehicle");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(viewAllButton);
        buttonPanel.add(checkOrderButton);
        buttonPanel.add(rentVehicleButton);
        buttonPanel.add(exitButton);

        // Add components to frame
        frame.add(topPanel, BorderLayout.NORTH); // Add the top panel
        frame.add(scrollPane, BorderLayout.CENTER); // Add the text area
        frame.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel

        // List to hold orders (for storing rental orders)
        List<Order> orders = new ArrayList<>();

        // Button actions
        viewAllButton.addActionListener(e -> {
            String[] carOptions = new String[cars.length];
            for (int i = 0; i < cars.length; i++) {
                carOptions[i] = cars[i].getBrand() + " " + cars[i].getModel();
            }

            String selectedCar = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select a car to view details:",
                    "View All Vehicles",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    carOptions,
                    carOptions[0]
            );

            if (selectedCar != null) {
                for (Vehicle car : cars) {
                    if ((car.getBrand() + " " + car.getModel()).equals(selectedCar)) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Car Details:\n" +
                                        "Brand: " + car.getBrand() + "\n" +
                                        "Model: " + car.getModel() + "\n" +
                                        "Year: " + car.getYear() + "\n" +
                                        "Available: " + (car.isAvailable() ? "Yes" : "No") + "\n" +
                                        "Price: $" + car.getPrice() + " per day\n"
                        );
                        break;
                    }
                }
            }
        });

        checkOrderButton.addActionListener(e -> {
            // Let user enter Order ID
            String orderId = JOptionPane.showInputDialog(frame, "Enter Order ID to view details:");

            if (orderId != null && !orderId.trim().isEmpty()) {
                // Search for the order by ID
                boolean orderFound = false;
                for (Order order : orders) {
                    if (order.getOrderId().equals(orderId)) {
                        // Show the order details
                        JOptionPane.showMessageDialog(frame,
                                "Order Details:\n" +
                                        "Order ID: " + order.getOrderId() + "\n" +
                                        "Customer: " + order.getCustomer().getName() + "\n" +
                                        "Vehicle: " + order.getVehicle().getBrand() + " " + order.getVehicle().getModel() + "\n" +
                                        "Rental Period: " + order.getRentalStart() + " to " + order.getRentalEnd() + "\n" +
                                        "Total Cost: $" + order.RentCalculation()
                        );
                        orderFound = true;
                        break;
                    }
                }

                if (!orderFound) {
                    JOptionPane.showMessageDialog(frame, "Order ID not found.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a valid Order ID.");
            }
        });

        rentVehicleButton.addActionListener(e -> {
            // Let user choose a customer
            String[] customerOptions = new String[customers.length];
            for (int i = 0; i < customers.length; i++) {
                customerOptions[i] = customers[i].getName();
            }

            String selectedCustomerName = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select a customer to create an order:",
                    "Rent a Vehicle",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    customerOptions,
                    customerOptions[0]
            );

            if (selectedCustomerName != null) {
                // Let user choose a vehicle to rent
                String[] availableVehicles = new String[cars.length];
                int availableCount = 0;
                for (Vehicle car : cars) {
                    if (car.isAvailable()) {
                        availableVehicles[availableCount] = car.getBrand() + " " + car.getModel();
                        availableCount++;
                    }
                }

                String selectedVehicle = (String) JOptionPane.showInputDialog(
                        frame,
                        "Select a vehicle to rent:",
                        "Rent a Vehicle",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        availableVehicles,
                        availableVehicles[0]
                );

                if (selectedVehicle != null) {
                    Vehicle selectedCar = null;
                    for (Vehicle car : cars) {
                        if ((car.getBrand() + " " + car.getModel()).equals(selectedVehicle)) {
                            selectedCar = car;
                            break;
                        }
                    }

                    if (selectedCar != null) {
                        // Let user choose rental dates
                        String startDateStr = JOptionPane.showInputDialog(frame, "Enter rental start date (YYYY-MM-DD):");
                        String endDateStr = JOptionPane.showInputDialog(frame, "Enter rental end date (YYYY-MM-DD):");

                        try {
                            LocalDate rentalStart = LocalDate.parse(startDateStr);
                            LocalDate rentalEnd = LocalDate.parse(endDateStr);

                            // Check if the rental dates are valid
                            if (!rentalStart.isBefore(rentalEnd)) {
                                JOptionPane.showMessageDialog(frame, "Start date must be before the end date.");
                                return;
                            }

                            // Check if the vehicle is available for the selected rental period
                            boolean isAvailableForPeriod = true;
                            for (Order order : orders) {
                                if (order.getVehicle() == selectedCar) {
                                    if ((rentalStart.isBefore(order.getRentalEnd()) && rentalStart.isAfter(order.getRentalStart())) ||
                                            (rentalEnd.isBefore(order.getRentalEnd()) && rentalEnd.isAfter(order.getRentalStart()))) {
                                        isAvailableForPeriod = false;
                                        break;
                                    }
                                }
                            }

                            if (!isAvailableForPeriod) {
                                JOptionPane.showMessageDialog(frame, "The vehicle is not available for the selected dates.");
                            } else {
                                // Create an order
                                String orderId = "ORD" + (orders.size() + 1);  // Generating order ID
                                Order newOrder = new Order(orderId, customers[0], selectedCar, rentalStart, rentalEnd);
                                orders.add(newOrder);

                                // Show rental summary
                                double rentalCost = newOrder.RentCalculation();
                                JOptionPane.showMessageDialog(frame, "Rental Order Created!\n" +
                                        "Order ID: " + newOrder.getOrderId() + "\n" +
                                        "Vehicle: " + selectedCar.getBrand() + " " + selectedCar.getModel() + "\n" +
                                        "Rental Period: " + rentalStart + " to " + rentalEnd + "\n" +
                                        "Total Cost: $" + rentalCost);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.");
                        }
                    }
                }
            }
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Exiting the system. Goodbye!");
            System.exit(0);
        });

        loginButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Login functionality is not implemented yet.");
        });

        // Show frame
        frame.setVisible(true);
    }
}
