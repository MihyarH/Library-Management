package UI;

import Exceptions.DuplicateBookNameException;
import Exceptions.DuplicateUsernameException;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoginManagerGUI {
    private static boolean loginStatus;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login Manager");
            frame.setSize(400, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // Add padding

            JTextField usernameField = new JTextField(15);
            JPasswordField passwordField = new JPasswordField(15);

            JButton loginButton = new JButton("Login");
            JButton exitButton = new JButton("Exit");

            // Add components with GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Username:"), gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(usernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Password:"), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(loginButton, gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            panel.add(exitButton, gbc);

            frame.add(panel);

            InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = panel.getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "submit");
            actionMap.put("submit", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginButton.doClick();
                }
            });

            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String inputUsername = usernameField.getText();
                    String inputPassword = new String(passwordField.getPassword());

                    User loggedInUser = User.getUserByUsername(inputUsername);

                    if (loggedInUser != null && User.checkLogin(inputUsername, inputPassword)) {
                        JOptionPane.showMessageDialog(frame, "Login successful!\nWelcome Back " + inputUsername);

                        if (loggedInUser.isAdmin()) {
                            JOptionPane.showMessageDialog(frame, "You are an admin. Perform admin actions.");
                            AdminGUI.main(null);
                        } else if (loggedInUser.isStaff()) {
                            JOptionPane.showMessageDialog(frame, "You are a staff member. Perform staff actions.");
                            StaffGUI.main(null);
                        }

                        loginStatus = true;
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Login failed. Please check your username and password.");
                        loginStatus = false;
                    }
                }
            });

            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Goodbye!");
                    frame.dispose();
                }
            });

            frame.setVisible(true);
        });
    }
}
