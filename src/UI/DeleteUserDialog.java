package UI;

import Users.User;

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

        // Layout
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(deleteButton);

        // Add action listener for the Delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the information from the fields
                String username = usernameField.getText();
                int userId = Integer.parseInt(userIdField.getText());

                // Delete the user
                User.deleteUser(userId);
                JOptionPane.showMessageDialog(DeleteUserDialog.this, "User deleted successfully!");
                dispose();
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
