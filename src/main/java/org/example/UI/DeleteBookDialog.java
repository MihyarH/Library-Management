package org.example.UI;

import org.example.BooksDB.BooksDataManipulator;
import org.example.Modules.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBookDialog extends JDialog {
    private JTextField idField;

    public DeleteBookDialog(JFrame parent) {
        super(parent, "Delete Book", true);

        // Initialize components
        idField = new JTextField(10);
        JButton deleteButton = new JButton("Delete");

        // Layout
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Book ID:"));
        panel.add(idField);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(idField.getText());

                    if (BooksDataManipulator.checkIfBooksExistsUsingId(bookId)){
                        BooksDataManipulator.delete(bookId);
                    }else{
                        System.out.println("Book not found.");
                        JOptionPane.showMessageDialog(DeleteBookDialog.this, "Book not found.");
                    }
                    JOptionPane.showMessageDialog(DeleteBookDialog.this, "Book deleted successfully!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DeleteBookDialog.this, "Invalid book ID. Please enter a valid number.");
                }
            }
        });

        getRootPane().setDefaultButton(deleteButton);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
    }
}
