import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    static List<Order> orderList = new ArrayList<>();

    public static void main(String[] args) {
        // Show login screen
        LoginFrame loginFrame = new LoginFrame();

        // Wait for login to succeed
        while (loginFrame.isVisible()) {
            try {
                Thread.sleep(100); // Prevent busy waiting
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        // Check if login was successful
        if (!loginFrame.isLoginSuccessful()) {
            System.exit(0); // Exit if login failed
        }

        // Dummy data for cars, motorcycles, and trucks
        Car[] cars = new Car[5];
        Motorcycle[] motor = new Motorcycle[5];
        Truck[] trucks = new Truck[5];

        cars[0] = new Car(2020, true, 150, "image/kereta/toyota camry (1).jpg", 5, "Toyota", "Camry");
        cars[1] = new Car(2019, false, 120, "image/2023.04.22-HONDA-FL5-CIVIC-TYPE-R-2023_1.jpg", 10, "Honda", "Civic");
        cars[2] = new Car(2021, true, 100, "image/kereta/ford focus (1).jpg", 3, "Ford", "Focus");
        cars[3] = new Car(2018, true, 130, "image/kereta/Chevrolet Malibu (1).jpg", 7, "Chevrolet", "Malibu");
        cars[4] = new Car(2022, false, 120, "image/kereta/Nissan Altima (1).jpg", 2, "Nissan", "Altima");

        motor[0] = new Motorcycle(2020, true, 90, "image/Motorcycle/Harley-Davidson Street 750 (1).jpg", 4,
                "Harley-Davidson", "Street 750");
        motor[1] = new Motorcycle(2019, true, 85, "image/Motorcycle/Kawasaki Ninja 400 (1).jpg", 6, "Kawasaki",
                "Ninja 400");
        motor[2] = new Motorcycle(2021, false, 80, "image/Motorcycle/Yamaha YZF-R3 (1).jpg", 8, "Yamaha", "YZF-R3");
        motor[3] = new Motorcycle(2022, true, 75, "image/Motorcycle/Ducati Panigale V2 (1).jpg", 1, "Ducati",
                "Panigale V2");
        motor[4] = new Motorcycle(2020, true, 86, "image/Motorcycle/HONDA CBR500R (1).jpg", 5, "Honda", "CBR500R");

        trucks[0] = new Truck(2020, true, 200, "image/Truck/FORD F-150 (1).jpg", 10, "Ford", "F-150");
        trucks[1] = new Truck(2021, true, 250, "image/Truck/CHEVROLET SILVERADO (1).jpg", 12, "Chevrolet", "Silverado");
        trucks[2] = new Truck(2022, false, 280, "image/Truck/RAM 1500 (1).jpg", 15, "Ram", "1500");
        trucks[3] = new Truck(2020, true, 200, "image/Truck/TOYOTA TUNDRA (1).jpg", 3, "Toyota", "Tundra");
        trucks[4] = new Truck(2021, true, 230, "image/Truck/GMC SIERRA (1).jpg", 9, "GMC", "Sierra");

        // Create the main frame
        JFrame frame = new JFrame("Vehicle Rental System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the top panel with the title and login button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);
        JLabel titleLabel = new JLabel("Car Rental", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(titleLabel, BorderLayout.WEST);

        // Create the center panel (can be left empty or used for other components)
        // Create the center panel for scrolling images
        // "image/2023.04.22-HONDA-FL5-CIVIC-TYPE-R-2023_1.jpg",
        // Create the center panel for scrolling images
        // Create the center panel for scrolling images
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        // Create a panel to hold the images
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));

        // Add images to the imagePanel with resizing
        String[] imagePaths = {
                "image/2023.04.22-HONDA-FL5-CIVIC-TYPE-R-2023_1.jpg",
                "image/kereta/Nissan Altima (1).jpg",
                "image/kereta/toyota camry (1).jpg",
                "image/kereta/ford focus (1).jpg"
        };

        // Define the desired image width and height
        int imageWidth = 900;
        int imageHeight = 550;

        // Load and scale the initial images
        for (String path : imagePaths) {
            ImageIcon originalIcon = new ImageIcon(path);
            Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel imageLabel = new JLabel(scaledIcon);
            imagePanel.add(imageLabel);
        }

        // Add the image panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        // Add the scroll pane to the center panel
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add scrolling animation (right to left) with changing images
        Timer timer = new Timer(30, new ActionListener() {
            int scrollPosition = 0;
            int currentIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPosition++;

                // If scrolling has covered the width of the current image, switch to the next
                // image
                if (scrollPosition >= imageWidth) {
                    scrollPosition = 0; // Reset the scroll position

                    // Update the image panel with the next image
                    imagePanel.removeAll(); // Clear the current images
                    currentIndex = (currentIndex + 1) % imagePaths.length; // Move to the next image
                    for (int i = 0; i < imagePaths.length; i++) {
                        String path = imagePaths[(currentIndex + i) % imagePaths.length]; // Circular indexing
                        ImageIcon originalIcon = new ImageIcon(path);
                        Image scaledImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight,
                                Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);

                        JLabel imageLabel = new JLabel(scaledIcon);
                        imagePanel.add(imageLabel);
                    }

                    imagePanel.revalidate(); // Refresh the image panel
                    imagePanel.repaint(); // Ensure the new images are displayed
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
        JButton rentNowButton = new JButton("Rent Now");
        JButton exitButton = new JButton("Exit");

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
                        vehicleOptions[0]);

                // Check if a vehicle was selected
                if (selectedVehicle != null) {
                    JPanel detailsPanel = new JPanel(new BorderLayout());
                    JLabel imageLabel = new JLabel();
                    JTextArea detailsTextArea = new JTextArea();
                    detailsTextArea.setEditable(false);
                    detailsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
                    detailsTextArea.setLineWrap(true);
                    detailsTextArea.setWrapStyleWord(true);

                    // Find the selected vehicle and populate details
                    for (int i = 0; i < cars.length; i++) {
                        if (selectedVehicle.equals(cars[i].getBrand() + " " + cars[i].getModel() + " - [CARS]")) {
                            // Set car image and details
                            imageLabel.setIcon(new ImageIcon(new ImageIcon(cars[i].getImagePath())
                                    .getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
                            detailsTextArea.setText("Car Details:\n"
                                    + "Brand: " + cars[i].getBrand() + "\n"
                                    + "Model: " + cars[i].getModel() + "\n"
                                    + "Year: " + cars[i].getYear() + "\n"
                                    + "Price: RM" + cars[i].getPrice());
                            break;
                        }
                    }
                    for (int i = 0; i < motor.length; i++) {
                        if (selectedVehicle.equals(motor[i].getBrand() + " " + motor[i].getModel() + " - [MOTOR]")) {
                            // Set motorcycle image and details
                            imageLabel.setIcon(new ImageIcon(new ImageIcon(motor[i].getImagePath())
                                    .getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
                            detailsTextArea.setText("Motorcycle Details:\n"
                                    + "Brand: " + motor[i].getBrand() + "\n"
                                    + "Model: " + motor[i].getModel() + "\n"
                                    + "Year: " + motor[i].getYear() + "\n"
                                    + "Price: RM" + motor[i].getPrice());
                            break;
                        }
                    }
                    for (int i = 0; i < trucks.length; i++) {
                        if (selectedVehicle.equals(trucks[i].getBrand() + " " + trucks[i].getModel() + " - [TRUCK]")) {
                            // Set truck image and details
                            imageLabel.setIcon(new ImageIcon(new ImageIcon(trucks[i].getImagePath())
                                    .getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
                            detailsTextArea.setText("Truck Details:\n"
                                    + "Brand: " + trucks[i].getBrand() + "\n"
                                    + "Model: " + trucks[i].getModel() + "\n"
                                    + "Year: " + trucks[i].getYear() + "\n"
                                    + "Price: RM" + trucks[i].getPrice());
                            break;
                        }
                    }

                    // Add components to the details panel
                    detailsPanel.add(imageLabel, BorderLayout.NORTH);
                    detailsPanel.add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);

                    // Show vehicle details in a dialog
                    JOptionPane.showMessageDialog(frame, detailsPanel, "Vehicle Details", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        JButton checkOrderButton = new JButton("Check Orders");
 
        // Check Orders Button
        checkOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Debugging: Output the size of the order list
                System.out.println("Order List Size: " + orderList.size());
        
                // Create a dialog to display the orders
                JDialog orderDialog = new JDialog(frame, "All Orders", true);
                orderDialog.setSize(500, 400);
                orderDialog.setLayout(new BorderLayout());
        
                // Create a tabbed pane to hold each order
                JTabbedPane tabbedPane = new JTabbedPane();
        
                // Check if the order list is empty
                if (orderList.isEmpty()) {
                    JPanel emptyPanel = new JPanel();
                    emptyPanel.add(new JLabel("No orders placed yet."));
                    tabbedPane.addTab("No Orders", emptyPanel);
                } else {
                    // Loop through the orderList and create a tab for each order
                    for (int i = 0; i < orderList.size(); i++) {
                        Order order = orderList.get(i);
        
                        // Panel to display the details of the current order
                        JPanel orderPanel = new JPanel(new BorderLayout());
        
                        String orderDetails = "Order ID: " + order.getOrderId() + "\n" +
                                "Vehicle: " + order.getVehicle() + "\n" +
                                "Days: " + order.getDays() + "\n" +
                                "Total Price: $" + order.getTotalPrice() + "\n";
        
                        JTextArea orderTextArea = new JTextArea(orderDetails);
                        orderTextArea.setEditable(false);
                        orderTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
                        orderTextArea.setLineWrap(true);
                        orderTextArea.setWrapStyleWord(true);
        
                        JScrollPane scrollPane = new JScrollPane(orderTextArea);
                        orderPanel.add(scrollPane, BorderLayout.CENTER);
        
                        tabbedPane.addTab("Order " + (i + 1), orderPanel);
                    }
                }
        
                // Add the tabbed pane to the dialog
                orderDialog.add(tabbedPane, BorderLayout.CENTER);
        
                // Close button
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        orderDialog.dispose(); // Close the dialog
                    }
                });
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(closeButton);
                orderDialog.add(buttonPanel, BorderLayout.SOUTH);
        
                // Set dialog properties and show it
                orderDialog.setLocationRelativeTo(frame); // Center relative to the main frame
                orderDialog.setVisible(true);
            }
        });
        

        // Rent Now Button
        rentNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a dialog to display the rental form
                JDialog rentalDialog = new JDialog(frame, "Rent Now", true);
                rentalDialog.setSize(400, 300);
                rentalDialog.setLayout(new BorderLayout());

                // Add the rental form to the dialog
                RentalForm rentalForm = new RentalForm(order -> {
                    // Debugging: Output the order being placed
                    System.out.println("Order placed: " + order.getOrderId() + ", Vehicle: " + order.getVehicle());

                    orderList.add(order); // Add the new order to the list

                    // Show success message
                    JOptionPane.showMessageDialog(frame, "Order placed successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    rentalDialog.dispose(); // Close the dialog
                });

                rentalDialog.add(rentalForm, BorderLayout.CENTER);

                // Add a close button to the dialog
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        rentalDialog.dispose(); // Close the dialog
                    }
                });
                buttonPanel.add(closeButton);
                rentalDialog.add(buttonPanel, BorderLayout.SOUTH);

                // Set dialog properties and show it
                rentalDialog.setLocationRelativeTo(frame); // Center relative to main frame
                rentalDialog.setVisible(true);
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
        southPanel.add(checkOrderButton);
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
