package org.example.UI;

import org.example.UsersDB.UsersDataManipulator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListUsersDialog extends JDialog {

    public ListUsersDialog(JFrame parent) {
        super(parent, "List Users", true);

        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JTextArea userTextArea = new JTextArea(15, 30);
        userTextArea.setEditable(false);

        String allUsersInfo = UsersDataManipulator.selectAll();

        if (!allUsersInfo.isEmpty()) {
            userTextArea.append(allUsersInfo);
        } else {
            userTextArea.setText("No users found.");
        }

        JScrollPane scrollPane = new JScrollPane(userTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
