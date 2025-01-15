import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private Container cp; // content pane

    public LoginFrame() {
        super.setTitle("Please Login");
        super.setSize(300, 150);
        cp = super.getContentPane();

        prepareCenterPanel();
        prepareSouthPanel();

        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    private void prepareSouthPanel() {
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);

        // create a JPanel using GridLayout, 1x2
        JPanel pnlSouth = new JPanel();
        pnlSouth.setLayout(new GridLayout(1, 2));

        // add elements to pnlSouth
        JLabel space = new JLabel("");
        pnlSouth.add(space);
        pnlSouth.add(btnLogin);

        // add pnlSouth to Content Pane
        pnlSouth.setBorder(new EmptyBorder(5, 0, 5, 0));
        cp.add(pnlSouth, BorderLayout.SOUTH);
    }

    private void prepareCenterPanel() {
        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        // create a JPanel with GridLayout
        JPanel pnlCenter = new JPanel();
        pnlCenter.setLayout(new GridLayout(2, 2));

        // add elements to pnlCenter
        pnlCenter.add(lblUsername);
        pnlCenter.add(txtUsername);
        pnlCenter.add(lblPassword);
        pnlCenter.add(txtPassword);

        // add pnlCenter to Content Pane
        cp.add(pnlCenter, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            // verify username and password
            String username = txtUsername.getText();
            char[] passwordArr = txtPassword.getPassword();
            String password = String.valueOf(passwordArr);

            if (username.equals("admin") && password.equals("secret")) {
                JOptionPane.showMessageDialog(this,
                        "Login Success!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Invalid username/password");
                JOptionPane.showMessageDialog(this, "Login clicked!");
            }
        }
    }
}
