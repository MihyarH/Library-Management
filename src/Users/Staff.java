package Users;

import UI.CreateBookDialog;
import Modules.Book;
import Exceptions.DuplicateBookNameException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Staff {
    public static void main(String[] args) throws DuplicateBookNameException {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

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

            frame.add(panel, BorderLayout.NORTH);

            createButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new CreateBookDialog(frame).setVisible(true);
                }
            });

            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Assume you have the necessary information to update a book
                    Book.updateBook(1, "Updated Book", "Updated Author", 2023, 300, 20, "Fiction", "English");
                    refreshBookList();
                }
            });

            listButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Book.listBooks(); // You may want to display the list or perform other actions
                    refreshBookList();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Assume you have the book ID to delete
                    Book.deleteBook(1);
                    refreshBookList();
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

    private static void refreshBookList() {
        // Logic to refresh the book list, if needed
    }
}
