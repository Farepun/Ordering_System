import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private Container cp;
    private boolean loginSuccess;
    private List<Customer> customers;
    

    public LoginFrame() {
        super.setTitle("Please Login");
        super.setSize(300, 150);
        cp = super.getContentPane();

        // Load customer data
        customers = Customer.loadCustomersFromFile("Customer_Details.txt");

        prepareCenterPanel();
        prepareSouthPanel();

        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    private void prepareSouthPanel() {
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);

        JPanel pnlSouth = new JPanel();
        pnlSouth.setLayout(new GridLayout(1, 2));

        JLabel space = new JLabel("");
        pnlSouth.add(space);
        pnlSouth.add(btnLogin);

        pnlSouth.setBorder(new EmptyBorder(5, 0, 5, 0));
        cp.add(pnlSouth, BorderLayout.SOUTH);
    }

    private void prepareCenterPanel() {
        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        JPanel pnlCenter = new JPanel();
        pnlCenter.setLayout(new GridLayout(2, 2));

        pnlCenter.add(lblUsername);
        pnlCenter.add(txtUsername);
        pnlCenter.add(lblPassword);
        pnlCenter.add(txtPassword);

        cp.add(pnlCenter, BorderLayout.CENTER);
    }

    public boolean isLoginSuccessful() {
        return loginSuccess;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText();
            char[] passwordArr = txtPassword.getPassword();
            String password = String.valueOf(passwordArr);

            // Validate credentials
            boolean found = false;
            for (Customer customer : customers) {
                if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                loginSuccess = true;
                JOptionPane.showMessageDialog(this, "Login Success!");
                this.dispose(); // Close the login frame
            } else {
                loginSuccess = false;
                JOptionPane.showMessageDialog(this, "Invalid username/password");
            }
        }
    }
}
