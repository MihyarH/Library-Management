package UI;

import Exceptions.DuplicateBookNameException;
import Modules.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateBookDialog extends JDialog {
    private JTextField idField;
    private JTextField bookNameField;
    private JTextField authorField;
    private JSpinner bookYearField;
    private JSpinner bookPagesField;
    private JSpinner bookQuantityField;
    private JTextField bookGenreField;
    private JTextField bookLanguageField;

    public UpdateBookDialog(JFrame parent) {
        super(parent, "Update Book", true);

        // Initialize components
        idField = new JTextField(10);
        bookNameField = new JTextField(20);
        authorField = new JTextField(20);
        bookYearField = new JSpinner(new SpinnerNumberModel(2023, 1000, 3000, 1)); // Default: 2023, Range: 1000-3000
        bookPagesField = new JSpinner(new SpinnerNumberModel(200, 1, 10000, 1)); // Default: 200, Range: 1-10000
        bookQuantityField = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1)); // Default: 10, Range: 1-1000
        bookGenreField = new JTextField(20);
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
        panel.add(new JLabel("Pages:"));
        panel.add(bookPagesField);
        panel.add(new JLabel("Quantity:"));
        panel.add(bookQuantityField);
        panel.add(new JLabel("Genre:"));
        panel.add(bookGenreField);
        panel.add(new JLabel("Language:"));
        panel.add(bookLanguageField);

        panel.add(updateButton);

        // Add action listener for the Update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the information from the fields and update the book
                    int bookId = Integer.parseInt(idField.getText());
                    String bookName = bookNameField.getText();
                    String author = authorField.getText();
                    int bookYear = (int) bookYearField.getValue();
                    int bookPages = (int) bookPagesField.getValue();
                    int bookQuantity = (int) bookQuantityField.getValue();
                    String bookGenre = bookGenreField.getText();
                    String bookLanguage = bookLanguageField.getText();

                    // Assume you have a method like this in your Book class
                    Book.updateBook(bookId, bookName, author, bookYear, bookPages, bookQuantity, bookGenre, bookLanguage);

                    JOptionPane.showMessageDialog(UpdateBookDialog.this, "Book updated successfully!");
                    dispose(); // Close the dialog
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UpdateBookDialog.this, "Invalid book ID. Please enter a valid number.");
                }
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
