package org.example.UI;

import org.example.BooksDB.BooksDataManipulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateBookDialog extends JDialog {
    private JTextField bookNameField;
    private JTextField authorField;
    private JTextField bookYearField;
    private JTextField bookPriceField;
    private JTextField bookQuantityField;
    private JTextField bookLanguageField;

    public CreateBookDialog(JFrame parent) {
        super(parent, "Create Book", true);

        bookNameField = new JTextField(20);
        authorField = new JTextField(20);
        bookYearField = new JTextField(5);
        bookPriceField = new JTextField(5);
        bookQuantityField = new JTextField(5);
        bookLanguageField = new JTextField(20);

        JButton createButton = new JButton("Create");

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10)); // Reduce rows to match the number of fields
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

        panel.add(createButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (areFieldsEmpty()) {
                    JOptionPane.showMessageDialog(CreateBookDialog.this, "Please fill in all fields.");
                    return;
                }

                String bookName = bookNameField.getText();
                String author = authorField.getText();
                int bookYear = Integer.parseInt(bookYearField.getText());
                int bookPrice = Integer.parseInt(bookPriceField.getText());
                int bookQuantity = Integer.parseInt(bookQuantityField.getText());
                String bookLanguage = bookLanguageField.getText();

                try {
                    Boolean isBookExist = BooksDataManipulator.checkIfBookExists(bookName);
                    if (isBookExist) {
                        JOptionPane.showMessageDialog(CreateBookDialog.this, "Book already exists.");
                        return;
                    }

                    BooksDataManipulator.insert(bookName, author, bookQuantity, bookPrice, bookLanguage, bookYear);
                    JOptionPane.showMessageDialog(CreateBookDialog.this, "Book created successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CreateBookDialog.this, "Please enter valid numbers for year, price, and quantity.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(CreateBookDialog.this, "SQL Error: " + ex.getMessage());
                }
            }
        });

        getRootPane().setDefaultButton(createButton);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }

    private boolean areFieldsEmpty() {
        return bookNameField.getText().trim().isEmpty() ||
                authorField.getText().trim().isEmpty() ||
                bookYearField.getText().trim().isEmpty() ||
                bookPriceField.getText().trim().isEmpty() ||
                bookQuantityField.getText().trim().isEmpty() ||
                bookLanguageField.getText().trim().isEmpty();
    }
}
