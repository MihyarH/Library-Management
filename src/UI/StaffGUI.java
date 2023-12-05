package UI;

import Exceptions.DuplicateBookNameException;
import Modules.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 1));

            JButton createButton = new JButton("Create Book");
            JButton updateButton = new JButton("Update Book");
            JButton listButton = new JButton("List Books");
            JButton deleteButton = new JButton("Delete Book");
            JButton exitButton = new JButton("Exit");

            panel.add(createButton);
            panel.add(updateButton);
            panel.add(listButton);
            panel.add(deleteButton);
            panel.add(exitButton);

            frame.add(panel);

            createButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Book.createBook();
                    } catch (DuplicateBookNameException ex) {
                        JOptionPane.showMessageDialog(frame, "Duplicate book name. Please try again.");
                    }
                }
            });

            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Book.updateBook();
                }
            });

            listButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Book.listBooks();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Book.deleteBook();
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
