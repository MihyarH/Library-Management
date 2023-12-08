package UI;

import Exceptions.DuplicateBookNameException;
import Modules.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class StaffGUI {
    private static JTextArea bookListTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridLayout(5, 1, 20, 20));

            JButton createButton = new JButton("Create Book");
            JButton updateButton = new JButton("Update Book");
            JButton listButton = new JButton("List Books");
            JButton deleteButton = new JButton("Delete Book");
            JButton exitButton = new JButton("Exit");

            Font buttonFont = new Font("Arial", Font.PLAIN, 16);
            createButton.setFont(buttonFont);
            updateButton.setFont(buttonFont);
            listButton.setFont(buttonFont);
            deleteButton.setFont(buttonFont);
            exitButton.setFont(buttonFont);

            panel.add(createButton);
            panel.add(updateButton);
            panel.add(listButton);
            panel.add(deleteButton);
            panel.add(exitButton);

            bookListTextArea = new JTextArea(10, 30);
            bookListTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(bookListTextArea);

            frame.add(panel, BorderLayout.WEST);
            frame.add(scrollPane, BorderLayout.CENTER);

            InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = panel.getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "create");
            actionMap.put("create", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the CreateBookDialog when the 'Create Book' button is pressed
                    CreateBookDialog createBookDialog = new CreateBookDialog(frame);
                    createBookDialog.setVisible(true);

                    // Refresh the book list when the dialog is closed
                    createBookDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            refreshBookList();
                        }
                    });
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0), "update");
            actionMap.put("update", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the UpdateBookDialog when the 'Update Book' button is pressed
                    UpdateBookDialog updateBookDialog = new UpdateBookDialog(frame);
                    updateBookDialog.setVisible(true);

                    // Refresh the book list when the dialog is closed
                    updateBookDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            refreshBookList();
                        }
                    });
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "delete");
            actionMap.put("delete", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the DeleteBookDialog when the 'Delete Book' button is pressed
                    DeleteBookDialog deleteBookDialog = new DeleteBookDialog(frame);
                    deleteBookDialog.setVisible(true);

                    // Refresh the book list when the dialog is closed
                    deleteBookDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            refreshBookList();
                        }
                    });
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "list");
            actionMap.put("list", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshBookList();
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "exit");
            actionMap.put("exit", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // This part is now handled by the CreateBookDialog
                    new CreateBookDialog(frame).setVisible(true);
                }
            });

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // This part is now handled by the UpdateBookDialog
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
                    refreshBookList();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            frame.setVisible(true);
        });
    }

    private static void refreshBookList() {
        bookListTextArea.setText(getBooksAsString());
    }

    private static String getBooksAsString() {
        List<Book> books = Book.loadBooksFromFile("books.txt");
        StringBuilder stringBuilder = new StringBuilder();

        for (Book book : books) {
            stringBuilder.append(book.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}
