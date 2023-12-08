package UI;

import Users.User;
import Exceptions.DuplicateUsernameException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public UpdateUserDialog(JFrame parent, int userId) {
        super(parent, "Update User", true);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton updateButton = new JButton("Update");

        // Layout
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                User.updateUser( username, new String(password));

                JOptionPane.showMessageDialog(UpdateUserDialog.this, "User updated successfully!");
                dispose();
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
