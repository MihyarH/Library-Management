package Modules;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private static List<Book> bookList = new ArrayList<>();

    public static void addBook(Book book) {
        bookList.add(book);
    }

    public static List<Book> getBookList() {
        return bookList;
    }

    public static void saveBooksToFile(List<Book> bookList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Book book : BookManager.bookList) {
                writer.write(book.toString());
                writer.newLine();
            }
            System.out.println("Books saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving books to file: " + e.getMessage());
        }
    }
}
