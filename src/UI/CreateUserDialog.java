package UI;

import Exceptions.DuplicateUsernameException;
import Users.User;
import Users.UserRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserDialog extends JDialog {
    private JTextField userIdField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<UserRole> roleComboBox;

    public CreateUserDialog(JFrame parent) {
        super(parent, "Create User", true);

        userIdField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        roleComboBox = new JComboBox<>(UserRole.values());

        JButton createButton = new JButton("Create");

        // Layout
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleComboBox);

        panel.add(createButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Dynamically retrieve userId from the userIdField
                    int userId = Integer.parseInt(userIdField.getText());
                    String username = usernameField.getText();
                    char[] password = passwordField.getPassword();
                    UserRole role = (UserRole) roleComboBox.getSelectedItem();

                    User.createUser(userId, username, new String(password), role);

                    JOptionPane.showMessageDialog(CreateUserDialog.this, "User created successfully!");
                    dispose();
                } catch (DuplicateUsernameException ex) {
                    JOptionPane.showMessageDialog(CreateUserDialog.this, "Duplicate username. Please try again.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CreateUserDialog.this, "Invalid user ID. Please enter a valid number.");
                }
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
