package UI;

import Exceptions.DuplicateBookNameException;
import Modules.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class CreateBookDialog extends JDialog {
    private JTextField bookNameField;
    private JTextField authorField;
    private JSpinner bookYearField;
    private JSpinner bookPagesField;
    private JSpinner bookQuantityField;
    private JTextField bookGenreField;
    private JTextField bookLanguageField;
    private JTextField idField;

    // Keep track of existing book IDs
    private Set<Integer> existingBookIds;

    // Flag to indicate whether a new book was created
    private boolean bookCreated = false;

    // Store the new book ID
    private int newBookId;

    public CreateBookDialog(JFrame parent) {
        super(parent, "Create Book", true);

        // Initialize components
        bookNameField = new JTextField(20);
        authorField = new JTextField(20);
        bookYearField = new JSpinner(new SpinnerNumberModel(2023, 1000, 3000, 1));
        bookPagesField = new JSpinner(new SpinnerNumberModel(200, 1, 10000, 1));
        bookQuantityField = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1));
        bookGenreField = new JTextField(20);
        bookLanguageField = new JTextField(20);
        idField = new JTextField(10);

        JButton createButton = new JButton("Create");

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

        panel.add(createButton);

        // Add action listener for the Create button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the information from the fields and create the book
                    int bookId = Integer.parseInt(idField.getText());
                    String bookName = bookNameField.getText();
                    String author = authorField.getText();
                    int bookYear = (int) bookYearField.getValue();
                    int bookPages = (int) bookPagesField.getValue();
                    int bookQuantity = (int) bookQuantityField.getValue();
                    String bookGenre = bookGenreField.getText();
                    String bookLanguage = bookLanguageField.getText();

                    // Check for duplicate book ID
                    if (existingBookIds.contains(bookId)) {
                        JOptionPane.showMessageDialog(CreateBookDialog.this, "Duplicate book ID. Please try again.");
                    } else {
                        // Assume you have a method like this in your Book class
                        Book.createBook(bookId, bookName, author, bookYear, bookPages, bookQuantity, bookGenre, bookLanguage);
                        JOptionPane.showMessageDialog(CreateBookDialog.this, "Book created successfully!");
                        bookCreated = true;
                        newBookId = bookId;
                        dispose(); // Close the dialog
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CreateBookDialog.this, "Invalid book ID. Please enter a valid number.");
                } catch (DuplicateBookNameException ex) {
                    JOptionPane.showMessageDialog(CreateBookDialog.this, "Duplicate book name. Please try again.");
                }
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }

    // Getter method to check if the book was created
    public boolean isBookCreated() {
        return bookCreated;
    }

    // Getter method to get the new book ID
    public int getNewBookId() {
        return newBookId;
    }

    // Setter method for existing book IDs
    public void setExistingBookIds(Set<Integer> existingBookIds) {
        this.existingBookIds = existingBookIds;
    }
}
