package UI;


import Exceptions.DuplicateBookNameException;
import Exceptions.DuplicateUsernameException;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginManagerGUI{
    private static boolean loginStatus;

    public static void main(String[] args)  {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login Manager");
            frame.setSize(400, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 2));

            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();

            JButton loginButton = new JButton("Login");
            JButton exitButton = new JButton("Exit");

            panel.add(new JLabel("Username:"));
            panel.add(usernameField);
            panel.add(new JLabel("Password:"));
            panel.add(passwordField);
            panel.add(loginButton);
            panel.add(exitButton);

            frame.add(panel);

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
