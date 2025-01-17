import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BillAppGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Bill Processing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create components
        JLabel label = new JLabel("Select a Bill File:");
        JButton browseButton = new JButton("Browse");
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(outputArea); // Add scrollable functionality
        JButton processButton = new JButton("Process");
        
        // Panel for top layout
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(label);
        topPanel.add(browseButton);
        panel.add(topPanel, BorderLayout.NORTH);

        // Add the scroll pane to the center
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the process button at the bottom
        panel.add(processButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // Initialize variables for file handling
        final File[] selectedFile = new File[1]; // Store the selected file

        // ActionListener for the Browse button
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                label.setText("Selected File: " + selectedFile[0].getName());
            }
        });

        // ActionListener for the Process button
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile[0] == null) {
                    JOptionPane.showMessageDialog(frame, "Please select a file first!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Scanner to read the input file
                    Scanner sc = new Scanner(selectedFile[0]);

                    // Create the output file
                    File outputFile = new File("above.txt");
                    PrintWriter pw = new PrintWriter(outputFile);

                    // Write the header to the output file
                    pw.println("Payment Greater than 1000");
                    pw.println("");
                    pw.println("Record No\tUser Id\t\tAmount");
                    pw.println("=====================");

                    int CntBelow1000 = 0; // Count records with payment <= 1000
                    StringBuilder resultBuilder = new StringBuilder();

                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String[] token = line.split(",");

                        String RecordNo = token[0];
                        String UserID = token[1];
                        Double amount = Double.parseDouble(token[2]);

                        if (amount > 1000) {
                            pw.println(RecordNo + "\t" + UserID + "\t" + amount);
                            resultBuilder.append("Record No: ").append(RecordNo).append(", User ID: ").append(UserID).append(", Amount: ").append(amount).append("\n");
                        } else {
                            CntBelow1000++;
                        }
                    }

                    // Write footer to output file
                    pw.println("=====================");
                    pw.close();
                    sc.close();

                    // Display results in the text area
                    resultBuilder.append("\nNumber of payments less than 1000: ").append(CntBelow1000);
                    outputArea.setText(resultBuilder.toString());

                    // Notify user of completion
                    JOptionPane.showMessageDialog(frame, "Processing complete! Output saved to 'above.txt'.", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(frame, "Error reading file: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
