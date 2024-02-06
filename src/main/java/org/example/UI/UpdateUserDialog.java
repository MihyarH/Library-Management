package org.example.UI;

import org.example.Users.User;
import org.example.Exceptions.*;
import org.example.UsersDB.UsersDataManipulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserDialog extends JDialog {
    private JTextField userIdField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public UpdateUserDialog(JFrame parent, int userId) {
        super(parent, "Update User", true);
        userIdField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton updateButton = new JButton("Update");

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("New Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("New Password:"));
        panel.add(passwordField);

        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(userIdField.getText());
                    String username = usernameField.getText();
                    char[] password = passwordField.getPassword();

                    UsersDataManipulator.update(id, username, new String(password));
                    JOptionPane.showMessageDialog(UpdateUserDialog.this, "User updated successfully!");
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UpdateUserDialog.this, "Invalid ID format. Please enter a valid number.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(UpdateUserDialog.this, "Error: " + ex.getMessage());
                    // Handle other exceptions, potentially related to updating the user
                }
            }
        });

        getRootPane().setDefaultButton(updateButton);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);

    }
}
