import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class RentalForm extends JPanel {
    private JComboBox<String> vehicleComboBox;
    private JTextField daysField;
    private JButton calculateButton, placeOrderButton;
    private JLabel priceLabel;

    public RentalForm(Consumer<Order> orderCallback) {
        setLayout(new BorderLayout());

        // Panel to hold the form fields
        JPanel formPanel = new JPanel(new GridLayout(4, 2));

        // ComboBox for selecting vehicle
        vehicleComboBox = new JComboBox<>();
        vehicleComboBox.addItem("Toyota Camry - $150 per day");
        vehicleComboBox.addItem("Honda Civic - $120 per day");
        vehicleComboBox.addItem("Ford Focus - $100 per day");
        vehicleComboBox.addItem("Chevrolet Malibu - $130 per day");
        vehicleComboBox.addItem("Nissan Altima - $120 per day");
        vehicleComboBox.addItem("Harley-Davidson Street 750- $90 per day");
        vehicleComboBox.addItem("Kawasaki Ninja 400 - $85 per day");
        vehicleComboBox.addItem("Yamaha YZF-R3 - $80 per day");
        vehicleComboBox.addItem("Honda CBR500R - $86 per day");
        vehicleComboBox.addItem("Ford F-150 - $200 per day");
        vehicleComboBox.addItem("Chevrolet Silverado - $250 per day");
        vehicleComboBox.addItem("Ram 1500 - $280 per day");
        vehicleComboBox.addItem("Toyota Tundra - $200 per day");
        vehicleComboBox.addItem("GMC Sierra - $230 per day");
        // TextField for entering days
        daysField = new JTextField();

        // Buttons for calculate and place order
        calculateButton = new JButton("Calculate Price");
        placeOrderButton = new JButton("Place Order");

        // Label to display the calculated price
        priceLabel = new JLabel("Total Price: $0", SwingConstants.CENTER);

        formPanel.add(new JLabel("Select Vehicle:"));
        formPanel.add(vehicleComboBox);
        formPanel.add(new JLabel("Number of Days:"));
        formPanel.add(daysField);
        formPanel.add(new JLabel()); // Empty cell
        formPanel.add(calculateButton);
        formPanel.add(placeOrderButton);
        formPanel.add(priceLabel);

        add(formPanel, BorderLayout.CENTER);

        // Calculate button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedVehicle = (String) vehicleComboBox.getSelectedItem();
                    if (selectedVehicle == null) {
                        throw new IllegalArgumentException("No vehicle selected.");
                    }

                    int pricePerDay = Integer.parseInt(selectedVehicle.split("\\$")[1].split(" ")[0]);
                    int days = Integer.parseInt(daysField.getText());

                    if (days <= 0) {
                        throw new IllegalArgumentException("Days must be greater than zero.");
                    }

                    int totalPrice = pricePerDay * days;
                    priceLabel.setText("Total Price: $" + totalPrice);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RentalForm.this, "Please enter a valid number for days.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(RentalForm.this, ex.getMessage(), "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Place order button action
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedVehicle = (String) vehicleComboBox.getSelectedItem();
                    if (selectedVehicle == null) {
                        throw new IllegalArgumentException("No vehicle selected.");
                    }

                    int pricePerDay = Integer.parseInt(selectedVehicle.split("\\$")[1].split(" ")[0]);
                    int days = Integer.parseInt(daysField.getText());

                    if (days <= 0) {
                        throw new IllegalArgumentException("Days must be greater than zero.");
                    }

                    int totalPrice = pricePerDay * days;

                    Order order = new Order(
                            "Order" + System.currentTimeMillis(),
                            selectedVehicle.split(" - ")[0], // Extract vehicle name
                            days,
                            totalPrice);

                    orderCallback.accept(order); // Pass the order to the callback
                    JOptionPane.showMessageDialog(RentalForm.this, "Order placed successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RentalForm.this, "Please enter a valid number for days.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(RentalForm.this, ex.getMessage(), "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
