import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    public static void main(String[] args) {
        // Dummy data for cars, motorcycles, and trucks
        Car[] cars = new Car[5];
        Motorcycle[] motor = new Motorcycle[5];
        Truck[] trucks = new Truck[5];

        cars[0] = new Car(2020, true, 150, "images/toyota_camry.jpg", 5, "Toyota", "Camry");
        cars[1] = new Car(2019, false, 120, "images/honda_civic.jpg", 10, "Honda", "Civic");
        cars[2] = new Car(2021, true, 100, "images/ford_focus.jpg", 3, "Ford", "Focus");
        cars[3] = new Car(2018, true, 130, "images/chevrolet_malibu.jpg", 7, "Chevrolet", "Malibu");
        cars[4] = new Car(2022, false, 120, "images/nissan_altima.jpg", 2, "Nissan", "Altima");

        motor[0] = new Motorcycle(2020, true, 90, "images/harley_street750.jpg", 4, "Harley-Davidson", "Street 750");
        motor[1] = new Motorcycle(2019, true, 85, "images/kawasaki_ninja400.jpg", 6, "Kawasaki", "Ninja 400");
        motor[2] = new Motorcycle(2021, false, 80, "images/yamaha_yzfr3.jpg", 8, "Yamaha", "YZF-R3");
        motor[3] = new Motorcycle(2022, true, 75, "images/ducati_panigalev2.jpg", 1, "Ducati", "Panigale V2");
        motor[4] = new Motorcycle(2020, true, 86, "images/honda_cbr500r.jpg", 5, "Honda", "CBR500R");

        trucks[0] = new Truck(2020, true, 200, "images/ford_f150.jpg", 10, "Ford", "F-150");
        trucks[1] = new Truck(2021, true, 250, "images/chevrolet_silverado.jpg", 12, "Chevrolet", "Silverado");
        trucks[2] = new Truck(2022, false, 280, "images/ram_1500.jpg", 15, "Ram", "1500");
        trucks[3] = new Truck(2020, true, 200, "images/toyota_tundra.jpg", 3, "Toyota", "Tundra");
        trucks[4] = new Truck(2021, true, 230, "images/gmc_sierra.jpg", 9, "GMC", "Sierra");

        // Create the main frame
        JFrame frame = new JFrame("Vehicle Rental System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the top panel with the title and login button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);
        JLabel titleLabel = new JLabel("Car Rental", SwingConstants.LEFT);
        JButton loginButton = new JButton("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(loginButton, BorderLayout.EAST);

        // Create the center panel (can be left empty or used for other components)
        // Create the center panel for scrolling images 
        // "image/2023.04.22-HONDA-FL5-CIVIC-TYPE-R-2023_1.jpg",
// Create the center panel for scrolling images
JPanel centerPanel = new JPanel(new BorderLayout());
centerPanel.setBackground(Color.WHITE);

// Create a panel to hold the images
JPanel imagePanel = new JPanel();
imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));

// Add images to the imagePanel with resizing
String[] imagePaths = {
        "image/2023.04.22-HONDA-FL5-CIVIC-TYPE-R-2023_1.jpg",
};

// Define the desired image height and width
int imageWidth = 1000;
int imageHeight = 600;

// Add images and duplicate them for seamless looping
for (String path : imagePaths) {
    ImageIcon originalIcon = new ImageIcon(path); // Load the original image
    Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);

    JLabel imageLabel = new JLabel(scaledIcon);
    imagePanel.add(imageLabel);

    // Duplicate the same image
    JLabel duplicateImageLabel = new JLabel(scaledIcon);
    imagePanel.add(duplicateImageLabel);
}

// Add the image panel to a scroll pane
JScrollPane scrollPane = new JScrollPane(imagePanel);
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
scrollPane.getViewport().setOpaque(false);
scrollPane.setOpaque(false);

// Add the scroll pane to the center panel
centerPanel.add(scrollPane, BorderLayout.CENTER);

// Add scrolling animation (right to left)
Timer timer = new Timer(30, new ActionListener() {
    int scrollPosition = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        scrollPosition++;
        if (scrollPosition >= imageWidth) {
            scrollPosition = 0; // Reset to start for seamless loop
        }
        scrollPane.getHorizontalScrollBar().setValue(scrollPosition);
    }
});

// Start the timer for animation
timer.start();
  


        // Create the south panel with buttons
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        southPanel.setBackground(Color.LIGHT_GRAY);

        JButton viewAllButton = new JButton("View all Vehicle");
        JButton myOrderButton = new JButton("My Order");
        JButton rentNowButton = new JButton("Rent Now");
        JButton exitButton = new JButton("Exit");

        // Add action listeners for buttons
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an array to store vehicle options
                String[] vehicleOptions = new String[cars.length + motor.length + trucks.length];

                // Populate the array with vehicle options
                int index = 0;
                for (int i = 0; i < cars.length; i++) {
                    vehicleOptions[index++] = cars[i].getBrand() + " " + cars[i].getModel() + " - [CARS]";
                }
                for (int i = 0; i < motor.length; i++) {
                    vehicleOptions[index++] = motor[i].getBrand() + " " + motor[i].getModel() + " - [MOTOR]";
                }
                for (int i = 0; i < trucks.length; i++) {
                    vehicleOptions[index++] = trucks[i].getBrand() + " " + trucks[i].getModel() + " - [TRUCK]";
                }

                // Display a dialog to select a vehicle
                String selectedVehicle = (String) JOptionPane.showInputDialog(
                        frame,
                        "Select a vehicle to view details:",
                        "View All Vehicles",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        vehicleOptions,
                        vehicleOptions[0]
                );

                // Check if a vehicle was selected
                if (selectedVehicle != null) {
                    StringBuilder vehicleDetails = new StringBuilder();

                    // Find the selected vehicle
                    for (int i = 0; i < cars.length; i++) {
                        if (selectedVehicle.equals(cars[i].getBrand() + " " + cars[i].getModel()+ " - [CARS]")) {
                            // Display car details
                            vehicleDetails.append("Car Details:\n");
                            vehicleDetails.append("Brand: ").append(cars[i].getBrand()).append("\n");
                            vehicleDetails.append("Model: ").append(cars[i].getModel()).append("\n");
                            vehicleDetails.append("Year: ").append(cars[i].getYear()).append("\n");
                            vehicleDetails.append("Price: RM").append(cars[i].getPrice()).append("\n");
                            break;
                        }
                    }
                    for (int i = 0; i < motor.length; i++) {
                        if (selectedVehicle.equals(motor[i].getBrand() + " " + motor[i].getModel()+ " - [MOTOR]")) {
                            // Display motorcycle details
                            vehicleDetails.append("Motorcycle Details:\n");
                            vehicleDetails.append("Brand: ").append(motor[i].getBrand()).append("\n");
                            vehicleDetails.append("Model: ").append(motor[i].getModel()).append("\n");
                            vehicleDetails.append("Year: ").append(motor[i].getYear()).append("\n");
                            vehicleDetails.append("Price: RM").append(motor[i].getPrice()).append("\n");
                            break;
                        }
                    }
                    for (int i = 0; i < trucks.length; i++) {
                        if (selectedVehicle.equals(trucks[i].getBrand() + " " + trucks[i].getModel()+ " - [TRUCK]")) {
                            // Display truck details
                            vehicleDetails.append("Truck Details:\n");
                            vehicleDetails.append("Brand: ").append(trucks[i].getBrand()).append("\n");
                            vehicleDetails.append("Model: ").append(trucks[i].getModel()).append("\n");
                            vehicleDetails.append("Year: ").append(trucks[i].getYear()).append("\n");
                            vehicleDetails.append("Price: RM").append(trucks[i].getPrice()).append("\n");
                            break;
                        }
                    }

                    // Show vehicle details in a message dialog
                    JOptionPane.showMessageDialog(frame, vehicleDetails.toString(), "Vehicle Details", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        myOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to view my orders
                JOptionPane.showMessageDialog(frame, "Viewing my orders...", "My Orders", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        rentNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to rent a vehicle
                JOptionPane.showMessageDialog(frame, "Renting a vehicle...", "Rent Now", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        // Add buttons to the south panel
        southPanel.add(viewAllButton);
        southPanel.add(myOrderButton);
        southPanel.add(rentNowButton);
        southPanel.add(exitButton);

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        // Set frame visibility
        frame.setVisible(true);
    }
}

