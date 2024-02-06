package org.example.UI;

import org.example.BooksDB.BooksDataManipulator;
import org.example.Exceptions.*;
import org.example.Modules.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateBookDialog extends JDialog {
    private JTextField idField;
    private JTextField bookNameField;
    private JTextField authorField;
    private JSpinner bookYearField;
    private JSpinner bookPriceField;
    private JSpinner bookQuantityField;
    private JTextField bookLanguageField;

    public UpdateBookDialog(JFrame parent) {
        super(parent, "Update Book", true);

        idField = new JTextField(10);
        bookNameField = new JTextField(20);
        authorField = new JTextField(20);
        bookYearField = new JSpinner(new SpinnerNumberModel(2023, 1000, 3000, 1)); // Default: 2023, Range: 1000-3000
        bookPriceField = new JSpinner(new SpinnerNumberModel(70, 1, 10000, 1)); // Default: 200, Range: 1-10000
        bookQuantityField = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1)); // Default: 10, Range: 1-1000
        bookLanguageField = new JTextField(20);

        JButton updateButton = new JButton("Update");

        // Layout
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.add(new JLabel("Book ID:"));
        panel.add(idField);
        panel.add(new JLabel("Book Name:"));
        panel.add(bookNameField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Year:"));
        panel.add(bookYearField);
        panel.add(new JLabel("Price:"));
        panel.add(bookPriceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(bookQuantityField);
        panel.add(new JLabel("Language:"));
        panel.add(bookLanguageField);

        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(idField.getText());
                    String bookName = bookNameField.getText();
                    String author = authorField.getText();
                    int bookYear = (int) bookYearField.getValue();
                    int bookPrice = (int) bookPriceField.getValue();
                    int bookQuantity = (int) bookQuantityField.getValue();
                    String bookLanguage = bookLanguageField.getText();

                    BooksDataManipulator.update(bookId,bookName,author,bookQuantity,bookPrice,bookLanguage,bookYear);
                    JOptionPane.showMessageDialog(UpdateBookDialog.this, "Book updated successfully!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UpdateBookDialog.this, "Invalid book ID. Please enter a valid number.");
                }
            }
        });

        getRootPane().setDefaultButton(updateButton);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
