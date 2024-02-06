package org.example.UI;

import org.example.BooksDB.BooksDataManipulator;

import javax.swing.*;
import java.awt.*;

public class AllBooksDialog extends JDialog {

    public AllBooksDialog(JFrame parent) {
        super(parent, "All Books", true);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        String allBooksInfo = BooksDataManipulator.getAllBooks();
        textArea.setText(allBooksInfo);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setSize(500, 400);
        setLocationRelativeTo(parent);
    }
}
