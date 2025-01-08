import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    public static void main(String[] args) {
        // Create a list of vehicles
        Vehicle[] cars = new Vehicle[5];
        cars[0] = new Vehicle("Honda", "Civic", 2023, true, 150);
        cars[1] = new Vehicle("Toyota", "Corolla", 2022, true, 130);
        cars[2] = new Vehicle("Ford", "Focus", 2021, false, 140);
        cars[3] = new Vehicle("Chevrolet", "Malibu", 2020, true, 160);
        cars[4] = new Vehicle("Nissan", "Altima", 2019, true, 120);

        // Create the main frame
        JFrame frame = new JFrame("Car Rental System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the top panel with light grey background
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.LIGHT_GRAY); // Set light grey background

        JLabel titleLabel = new JLabel("Car Rental");
        JButton loginButton = new JButton("Login");

        // Style the title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Add components to the top panel
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(loginButton, BorderLayout.EAST);

        // Add a border to the top panel (Optional)
        topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Welcome to Car Rental", TitledBorder.LEFT, TitledBorder.TOP));

        // Center panel with CardLayout for images
        JPanel imagePanel = new JPanel(); 
        CardLayout cardLayout = new CardLayout();
        imagePanel.setLayout(cardLayout); // Use CardLayout here

        // Set a lighter grey background for the imagePanel
        imagePanel.setBackground(new Color(220, 220, 220)); 

        for (int i = 0; i < cars.length; i++) {
            // Construct the file path for the image
            String imagePath = "image/2023.04.22-HONDA-FL5-CIVIC-TYPE-R-2023_1.jpg"; // Update the file name as needed

            // Create ImageIcon from file path
            ImageIcon carImageIcon = new ImageIcon(imagePath);
            
            // Scale the image to fit the JLabel size (550x400 to have a clearer and resized image)
            Image img = carImageIcon.getImage(); // Get the Image object
            Image scaledImg = img.getScaledInstance(550, 400, Image.SCALE_SMOOTH); // Resize the image
            
            // Create a new ImageIcon with the scaled image
            ImageIcon scaledIcon = new ImageIcon(scaledImg);

            // Create a JLabel with the scaled image
            JLabel carImage = new JLabel(scaledIcon);
            carImage.setPreferredSize(new Dimension(800, 400)); // Ensure the image fills the center area
            carImage.setHorizontalAlignment(SwingConstants.CENTER);

            // Add action to display car details when the image is clicked
            int carIndex = i;
            carImage.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    JOptionPane.showMessageDialog(frame,
                            "Car Details:\n" +
                                    "Brand: " + cars[carIndex].getBrand() + "\n" +
                                    "Model: " + cars[carIndex].getModel() + "\n" +
                                    "Year: " + cars[carIndex].getYear() + "\n" +
                                    "Available: " + (cars[carIndex].isAvailable() ? "Yes" : "No") + "\n" +
                                    "Price: $" + cars[carIndex].getPrice() + " per day\n"
                    );
                }
            });

            imagePanel.add(carImage, "Card" + i); // Add each image as a card
        }

        // Left and Right arrow buttons
        JButton leftArrow = new JButton("<");
        JButton rightArrow = new JButton(">");

        leftArrow.setFont(new Font("Arial", Font.BOLD, 20));
        rightArrow.setFont(new Font("Arial", Font.BOLD, 20));

        leftArrow.setFocusPainted(false);
        rightArrow.setFocusPainted(false);

        leftArrow.setOpaque(false);
        rightArrow.setOpaque(false);

        leftArrow.setContentAreaFilled(false);
        rightArrow.setContentAreaFilled(false);

        leftArrow.setBorderPainted(false);
        rightArrow.setBorderPainted(false);

        // Add navigation functionality
        leftArrow.addActionListener(new ActionListener() {
            int currentIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex - 1 + cars.length) % cars.length;
                cardLayout.show(imagePanel, "Card" + currentIndex);
            }
        });

        rightArrow.addActionListener(new ActionListener() {
            int currentIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % cars.length;
                cardLayout.show(imagePanel, "Card" + currentIndex);
            }
        });

        // Center panel to design image scrolling that use arrow
        // Overlay panel for arrows
        JPanel arrowPanel = new JPanel(null);
        arrowPanel.setOpaque(false);
        arrowPanel.setLayout(null);

        // Position the arrows
        leftArrow.setBounds(10, 200, 50, 50);
        rightArrow.setBounds(740, 200, 50, 50);

        arrowPanel.add(leftArrow);
        arrowPanel.add(rightArrow);

        // Combine the image panel and arrows into one layered panel
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 400));
        imagePanel.setBounds(0, 0, 800, 400);
        arrowPanel.setBounds(0, 0, 800, 400);

        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(arrowPanel, JLayeredPane.PALETTE_LAYER);

        // Panel for buttons (with FlowLayout for centering)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY); // Set light grey background

        // South Panel
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
        frame.add(layeredPane, BorderLayout.CENTER); // Add the carousel to the center
        frame.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel

        // Add button actions
        loginButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Later change here for login function"));
        viewAllButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Feature: View All Vehicles"));
        checkOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Feature: Check Order ID"));
        rentVehicleButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Feature: Rent a Vehicle"));
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Exiting the system. Goodbye!");
            System.exit(0);
        });

        // Show frame
        frame.setVisible(true);
    }
}
