package org.example.UI;

import org.example.Users.*;
import org.example.UsersDB.UsersDataManipulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserDialog extends JDialog {
    private JTextField usernameField;
    private JTextField userIdField;

    public DeleteUserDialog(JFrame parent) {
        super(parent, "Delete User", true);

        usernameField = new JTextField(15);
        userIdField = new JTextField(5);

        JButton deleteButton = new JButton("Delete");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                int userId = Integer.parseInt(userIdField.getText());

                UsersDataManipulator.delete(userId);
                JOptionPane.showMessageDialog(DeleteUserDialog.this, "User deleted successfully!");
                dispose();
            }
        });
        getRootPane().setDefaultButton(deleteButton);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
