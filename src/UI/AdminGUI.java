package UI;

import Exceptions.DuplicateUsernameException;
import Users.User;
import Users.UserRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminGUI {
    private static DefaultListModel<String> userListModel; // Model for JList

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);

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

            userListModel = new DefaultListModel<>();
            JList<String> userList = new JList<>(userListModel);
            JScrollPane scrollPane = new JScrollPane(userList);
            gbc.gridy++;
            gbc.insets = new Insets(10, 5, 5, 5);
            panel.add(scrollPane, gbc);

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

            listUsersButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    displayUserList();
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

    private static void displayUserList() {
        List<User> users = User.loadUsersFromFile("users.txt");

        userListModel.clear();

        if (users.isEmpty()) {
            userListModel.addElement("No users found.");
        } else {
            for (User user : users) {
                userListModel.addElement("User ID: " + user.getUserId());
                userListModel.addElement("Username: " + user.getUsername());
                userListModel.addElement("Password: " + user.getPassword());
                userListModel.addElement("Role: " + user.getRole());
                userListModel.addElement("----------------------");
            }
        }
    }
}
