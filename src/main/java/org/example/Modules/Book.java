package org.example.Modules;

import org.example.BooksDB.BooksDataManipulator;
import org.example.Exceptions.DuplicateBookNameException;
import org.example.Modules.Interfaces.Borrowable;
import org.example.Modules.Interfaces.Reservable;
import java.sql.SQLException;
import java.util.*;


public class Book implements Borrowable , Reservable {
    private int bookId;
    private String bookName;
    private int bookYear;
    private int bookQuantity;
    private String BookAuthor;
    private String BookLanguage;

    public Book(int bookId, String bookName, String BookAuthor, int bookYear, int bookQuantity, String BookLanguage) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookYear = bookYear;
        this.bookQuantity = bookQuantity;
        this.BookAuthor = BookAuthor;
        this.BookLanguage = BookLanguage;
    }
    public int getBookId() {return bookId;}
    public String getBookAuthor() {
        return BookAuthor;
    }
    public String getBookName() {
        return bookName;
    }
    public int getBookYear() {
        return bookYear;
    }


    public String getBookLanguage() {
        return BookLanguage;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }
    private static boolean checkIfBookNameExists(String bookName) {
        if (BooksDataManipulator.checkIfBookExists(bookName)) {
            System.out.println("Book name already exists.");
            return true;
        }
        return false;
    }

    public static void createBook(String bookName, String author, int bookQuantity, int bookPrice, String bookLanguage, int bookYear) throws DuplicateBookNameException, SQLException {
        if (!checkIfBookNameExists(bookName)) {
            BooksDataManipulator.insert(bookName, author,bookQuantity,bookPrice,bookLanguage,bookYear);
            System.out.println("Book created successfully.");
        } else {
            throw new DuplicateBookNameException("Error: Book name already exists.");
        }
    }

    public static void updateBook(int bookId, String bookName, String author, int bookQuantity, int bookPrice, String bookLanguage, int bookYear) {
        if (BooksDataManipulator.checkIfBookExists(bookName)) {
            BooksDataManipulator.update(bookId,bookName,author,bookQuantity,bookPrice,bookLanguage,bookYear);
        }else {
            System.out.println("Book not found.");
        }
    }

    public static void searchForBook(String bookName) {
        BooksDataManipulator.search(bookName);
    }


    public static void listBooks() {
        BooksDataManipulator.selectAll();
    }

    public static void deleteBook(int bookId) {
        if (BooksDataManipulator.checkIfBooksExistsUsingId(bookId)) {
            BooksDataManipulator.delete(bookId);
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void getBookIdByName(String bookName) {
        BooksDataManipulator.getBookIdByName(bookName);
    }
    @Override
    public void isBorrowable() {
        boolean itemAvailable = bookQuantity > 0;
        if (itemAvailable) {
            System.out.println("The book is available, and we have " + bookQuantity + " in stock.");
        } else {
            System.out.println("The book is not available.");
        }
    }

    @Override
    public void borrowItem() {
        if (bookQuantity > 0) {
            bookQuantity--;
            System.out.println("Book borrowed.");
        } else {
            System.out.println("Book not available for borrowing.");
        }
    }

    @Override
    public void returnItem(int itemId) {
        if (this.bookId == itemId) {
            bookQuantity++;
            System.out.println("Book returned.");
        } else {
            System.out.println("Book not found for the given ID.");
        }
    }


    @Override
    public void reserveItem() {
        Scanner scanner = new Scanner(System.in);
        int bookId;
        while (true) {
            try {
                System.out.print("Enter the ID of the book to reserve: ");
                bookId = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
        if (BooksDataManipulator.checkIfBooksExistsUsingId(bookId)) {
            System.out.println("Book already reserved.");
        } else {
            bookQuantity++;
            System.out.println("Book reserved.");
        }
        scanner.close();
    }
}