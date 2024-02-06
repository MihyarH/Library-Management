package org.example.UI;

import org.example.BooksDB.BooksDataManipulator;
import org.example.Modules.Book;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;


public class StaffGUI {
    public static JTextArea bookListTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridLayout(6, 1, 2, 2));
            JButton createButton = new JButton("Create Book");
            JButton searchButton = new JButton("Search for a Book");
            JButton updateButton = new JButton("Update Book");
            JButton listButton = new JButton("List Books");
            JButton deleteButton = new JButton("Delete Book");
            JButton exitButton = new JButton("Exit");

            Font buttonFont = new Font("Arial", Font.PLAIN, 16);
            createButton.setFont(buttonFont);
            searchButton.setFont(buttonFont);
            updateButton.setFont(buttonFont);
            listButton.setFont(buttonFont);
            deleteButton.setFont(buttonFont);
            exitButton.setFont(buttonFont);

            panel.add(createButton);
            panel.add(searchButton);
            panel.add(updateButton);
            panel.add(listButton);
            panel.add(deleteButton);
            panel.add(exitButton);


            frame.add(panel);
            InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = panel.getActionMap();


            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CreateBookDialog(frame).setVisible(true);
                }
            });
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new SearchBookDialog(frame).setVisible(true);
                }
            });

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UpdateBookDialog(frame).setVisible(true);
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new DeleteBookDialog(frame).setVisible(true);
                }
            });

            listButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AllBooksDialog(frame).setVisible(true);
                }
            });

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });

            frame.setVisible(true);
        });
    }
}
