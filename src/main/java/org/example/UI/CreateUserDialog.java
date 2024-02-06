package org.example.UI;

import org.example.Exceptions.DuplicateUsernameException;
import org.example.Users.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

public class CreateUserDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public CreateUserDialog(JFrame parent) {
        super(parent, "Create User", true);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton createButton = new JButton("Create");

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        panel.add(createButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String userName = usernameField.getText();
                    char[] password = passwordField.getPassword();

                    User.createUser(userName, new String(password));
                    JOptionPane.showMessageDialog(CreateUserDialog.this, "User created successfully!");
                    dispose();

                } catch (DuplicateUsernameException ex) {
                    JOptionPane.showMessageDialog(CreateUserDialog.this, "Username already exists. Please choose a different username.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(CreateUserDialog.this, "Database error: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CreateUserDialog.this, "Error: " + ex.getMessage());
                }
            }
        });

        getRootPane().setDefaultButton(createButton);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
