import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Hello {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        frame.add(createLoginPanel());

        // Display the frame
        frame.setVisible(true);
    }

    private static JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Create username label and field, and add them to the panel
        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        JTextField usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);

        // Create password label and field, and add them to the panel
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        // Create login button, and add it to the panel
        JButton loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        // Add an ActionListener to the login button to handle login button clicks
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the username and password from the fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Perform the actual login process
                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            }
        });

        return panel;
    }

    private static boolean authenticate(String username, String password) {
        // This method should be replaced with the actual authentication process
        return username.equals("user") && password.equals("password");
    }
}