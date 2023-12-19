package UI;

import Modules.Book;

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
                Book.searchForBook(bookName);
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookName = searchTextField.getText();
                Book.searchForBook(bookName);
            }
        });

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }
}
