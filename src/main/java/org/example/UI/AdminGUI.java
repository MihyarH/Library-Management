package org.example.UI;

import org.example.Users.User;
import org.example.UsersDB.UsersDataManipulator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AdminGUI {
    private static DefaultListModel<String> userListModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 2, 2);

            JButton createUserButton = new JButton("Create User");
            JButton updateUserButton = new JButton("Update User");
            JButton listUsersButton = new JButton("List Users");
            JButton deleteUserButton = new JButton("Delete User");
            JButton exitButton = new JButton("Exit");

            panel.add(createUserButton, gbc);
            gbc.gridy++;
            panel.add(updateUserButton, gbc);
            gbc.gridy++;
            panel.add(listUsersButton, gbc);
            gbc.gridy++;
            panel.add(deleteUserButton, gbc);
            gbc.gridy++;
            panel.add(exitButton, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(10, 5, 5, 5);

            frame.add(panel);

            createUserButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new CreateUserDialog(frame).setVisible(true);
                }
            });

            updateUserButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int userId = 123;
                    new UpdateUserDialog(frame, userId).setVisible(true);
                }
            });

            // In AdminGUI, inside the ActionListener for the listUsersButton
            listUsersButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ListUsersDialog listUsersDialog = new ListUsersDialog(frame);
                    listUsersDialog.setVisible(true);
                }
            });


            deleteUserButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new DeleteUserDialog(frame).setVisible(true);
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