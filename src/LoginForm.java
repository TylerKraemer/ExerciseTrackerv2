import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * This writes the login form and reads if the username and password are correct to know if the user is logged in or not
 */
public class LoginForm extends JDialog {
    private boolean loggedIn;
    private String username;
    private String password;
    public LoginForm(JFrame owner, String title, boolean modal, String username, String password) {
        super(owner, title, modal);
        this.username = username;
        this.password = password;
        loggedIn = false;
        setupGUI();
    }
    private void setupGUI() {
        setBounds(300, 300, 300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panCenter = new JPanel();
        panCenter.setLayout(new GridLayout(2, 2));
        panCenter.add(new JLabel("Username: "));
        JTextField txtUsername = new JTextField(10);
        panCenter.add(txtUsername);
        panCenter.setLayout(new FlowLayout());
        panCenter.add(new JLabel("Password: "));
        JPasswordField txtPassword = new JPasswordField(10);
        panCenter.add(txtPassword);
        c.add(panCenter, BorderLayout.CENTER);

        JPanel panSouth = new JPanel();
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String enteredUsername = txtUsername.getText();
                                        String enteredPassword = new String(txtPassword.getPassword());
                                        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                                            loggedIn = true;
                                            setVisible(false);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Invalid username/password combination.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                                            loggedIn = false;
                                        }
                                    }
        });
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panSouth.setLayout(new FlowLayout());
        panSouth.add(btnOK);
        panSouth.add(btnCancel);
        c.add(panSouth, BorderLayout.SOUTH);
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
