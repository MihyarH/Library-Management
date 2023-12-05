package UI;

import Exceptions.DuplicateUsernameException;
import Users.User;
import Users.UserRole;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 1));

            JButton createUserButton = new JButton("Create User");
            JButton updateUserButton = new JButton("Update User");
            JButton listUsersButton = new JButton("List Users");
            JButton deleteUserButton = new JButton("Delete User");
            JButton exitButton = new JButton("Exit");

            panel.add(createUserButton);
            panel.add(updateUserButton);
            panel.add(listUsersButton);
            panel.add(deleteUserButton);
            panel.add(exitButton);

            frame.add(panel);

            createUserButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        User.createUser(UserRole.USER);
                    } catch (DuplicateUsernameException ex) {
                        JOptionPane.showMessageDialog(frame, "Duplicate username. Please try again.");
                    }
                }
            });

            updateUserButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    User.updateUser();
                }
            });

            listUsersButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    User.listUsers();
                }
            });

            deleteUserButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    User.deleteUser();
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
