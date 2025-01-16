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
    private Container cp;
    private boolean loginSuccess;

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

            if (username.equals("admin") && password.equals("secret")) {
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
