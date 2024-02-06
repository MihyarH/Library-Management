package org.example.UI;

import org.example.BooksDB.BooksDataManipulator;
import org.example.Modules.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBookDialog extends JDialog {
    private JTextField searchTextField;
    private JButton searchButton;

    public SearchBookDialog(JFrame parent) {
        super(parent, "Search for a Book", true);
        setLayout(new FlowLayout());

        searchTextField = new JTextField(20);
        searchButton = new JButton("Search");

        add(new JLabel("Enter Book Name:"));
        add(searchTextField);
        add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = searchTextField.getText();
                try {
                    boolean isBookFound = BooksDataManipulator.checkIfBookExists(bookName);
                    if (isBookFound) {
                        JOptionPane.showMessageDialog(SearchBookDialog.this, "Book found successfully!");
                        displayBookInfo(bookName);
                    } else {
                        JOptionPane.showMessageDialog(SearchBookDialog.this, "Book not found.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SearchBookDialog.this, "An error occurred: " + ex.getMessage());
                }
            }
        });

        getRootPane().setDefaultButton(searchButton);
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    private void displayBookInfo(String bookName) {
        JDialog bookInfoDialog = new JDialog(this, "Book Information", true);
        bookInfoDialog.setLayout(new BorderLayout());

        // Assuming printBookInfo returns a String with the book details
        String bookInfo = BooksDataManipulator.printBookInfo(bookName);

        JTextArea textArea = new JTextArea(bookInfo);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        bookInfoDialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> bookInfoDialog.dispose());
        bookInfoDialog.add(closeButton, BorderLayout.SOUTH);

        bookInfoDialog.setSize(400, 300);
        bookInfoDialog.setLocationRelativeTo(this);
        bookInfoDialog.setVisible(true);
    }
}
