import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RentalForm extends JPanel {
    private JComboBox<String> vehicleComboBox;
    private JTextField daysField;
    private JButton calculateButton;
    private JLabel priceLabel;

    public RentalForm() {
        setLayout(new BorderLayout());

        // Panel to hold the form fields
        JPanel formPanel = new JPanel(new GridLayout(4, 2));

        // ComboBox for selecting vehicle
        vehicleComboBox = new JComboBox<>();
        vehicleComboBox.addItem("Honda Civic - $150 per day");
        vehicleComboBox.addItem("Toyota Corolla - $130 per day");
        vehicleComboBox.addItem("Ford Focus - $140 per day");
        vehicleComboBox.addItem("Chevrolet Malibu - $160 per day");
        vehicleComboBox.addItem("Nissan Altima - $120 per day");

        // TextField for entering days
        daysField = new JTextField();

        // Button to calculate the rental price
        calculateButton = new JButton("Calculate Price");

        // Label to display the calculated price
        priceLabel = new JLabel("Total Price: $0", SwingConstants.CENTER);

        formPanel.add(new JLabel("Select Vehicle:"));
        formPanel.add(vehicleComboBox);
        formPanel.add(new JLabel("Number of Days:"));
        formPanel.add(daysField);
        formPanel.add(new JLabel());  // Empty cell
        formPanel.add(calculateButton);
        formPanel.add(new JLabel());  // Empty cell
        formPanel.add(priceLabel);

        add(formPanel, BorderLayout.CENTER);

        // Calculate button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedVehicle = (String) vehicleComboBox.getSelectedItem();
                    int pricePerDay = Integer.parseInt(selectedVehicle.split(" - \\$")[1].split(" per day")[0]);
                    int days = Integer.parseInt(daysField.getText());
                    int totalPrice = pricePerDay * days;

                    priceLabel.setText("Total Price: $" + totalPrice);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RentalForm.this, "Please enter a valid number for days.");
                }
            }
        });
    }
}
