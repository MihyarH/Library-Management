package Modules;

import Exceptions.DuplicateBookNameException;
import Modules.Interfaces.Borrowable;
import Modules.Interfaces.Reservable;


import java.io.*;
import java.util.*;

import static UI.StaffGUI.bookListTextArea;

public class Book implements Borrowable , Reservable {
    private static final String FILE_PATH = "books.txt";
    private int bookId;
    private static String bookName;
    private int bookYear;
    private int bookQuantity;
    private String BookAuthor;
    private String BookGenre;
    private String BookLanguage;
    private int BookPages;

    public Book(int bookId, String bookName, String BookAuthor, int bookYear, int bookQuantity, String BookGenre, String BookLanguage, int BookPages) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookYear = bookYear;
        this.bookQuantity = bookQuantity;
        this.BookAuthor = BookAuthor;
        this.BookGenre = BookGenre;
        this.BookLanguage = BookLanguage;
        this.BookPages = BookPages;
    }

    public Book(String bookName, String BookAuthor, int bookYear, int bookQuantity, String BookGenre, String BookLanguage, int BookPages) {
        this.bookName = bookName;
        this.bookYear = bookYear;
        this.bookQuantity = bookQuantity;
        this.BookAuthor = BookAuthor;
        this.BookGenre = BookGenre;
        this.BookLanguage = BookLanguage;
        this.BookPages = BookPages;
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

    public String getBookGenre() {
        return BookGenre;
    }

    public String getBookLanguage() {
        return BookLanguage;
    }

    public int getBookPages() {
        return BookPages;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }
    private static boolean checkIfBookNameExists(int bookId) {
        List<Book> books = loadBooksFromFile("books.txt");
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return true;
            }
        }
        return false;
    }

    public static void createBook(int bookId, String bookName, String author, int bookYear, int bookPages, int bookQuantity, String bookGenre, String bookLanguage) throws DuplicateBookNameException {
        if (!checkIfBookNameExists(bookName)) {
            Book newBook = new Book(bookId, bookName, author, bookYear, bookQuantity, bookGenre, bookLanguage, bookPages);
            List<Book> bookList = loadBooksFromFile(FILE_PATH);
            bookList.add(newBook);
            saveBooksToFile(bookList, FILE_PATH);
            System.out.println("Book created successfully.");
        } else {
            throw new DuplicateBookNameException("Error: Book name already exists.");
        }
    }

    private static boolean checkIfBookNameExists(String bookName) {
        List<Book> books = loadBooksFromFile(FILE_PATH);
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                return true;
            }
        }
        return false;
    }


    private static String validateBookAuthor(Scanner scanner) {
        String bookAuthor;
        do {
            System.out.print("Enter the author of the book: ");
            bookAuthor = scanner.nextLine().trim();
            if (bookAuthor.isEmpty()) {
                System.out.println("Book author cannot be empty. Please enter a valid author.");
            }
        } while (bookAuthor.isEmpty());
        return bookAuthor;
    }

    private static int validateBookYear(Scanner scanner) {
        int bookYear;
        do {
            System.out.print("Enter the year of the book: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for the book year.");
                scanner.next();
            }
            bookYear = scanner.nextInt();
        } while (bookYear <= 0);
        return bookYear;
    }

    private static int validateBookPages(Scanner scanner) {
        int bookPages;
        do {
            System.out.print("Enter the number of pages of the book: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for the book pages.");
                scanner.next(); // Consume invalid input
            }
            bookPages = scanner.nextInt();
        } while (bookPages <= 0);
        return bookPages;
    }

    private static int validateBookQuantity(Scanner scanner) {
        int bookQuantity;
        do {
            System.out.print("Enter the quantity of the book: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for the book quantity.");
                scanner.next(); // Consume invalid input
            }
            bookQuantity = scanner.nextInt();
        } while (bookQuantity <= 0);
        return bookQuantity;
    }

    private static String validateBookGenre(Scanner scanner) {
        String bookGenre;
        do {
            System.out.print("Enter the genre of the book: ");
            bookGenre = scanner.nextLine().trim();
            if (bookGenre.isEmpty()) {
                System.out.println("Book genre cannot be empty. Please enter a valid genre.");
            }
        } while (bookGenre.isEmpty());
        return bookGenre;
    }

    private static String validateBookLanguage(Scanner scanner) {
        String bookLanguage;
        do {
            System.out.print("Enter the language of the book: ");
            bookLanguage = scanner.nextLine().trim();
            if (bookLanguage.isEmpty()) {
                System.out.println("Book language cannot be empty. Please enter a valid language.");
            }
        } while (bookLanguage.isEmpty());
        return bookLanguage;
    }
    public static void updateBook(int bookId, String bookName, String author, int bookYear, int bookPages, int bookQuantity, String bookGenre, String bookLanguage) {
        List<Book> books = loadBooksFromFile("books.txt");
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.setBookName(bookName);
                bookFound = true;
                break;
            }
        }
        if (bookFound) {
            saveBooksToFile(books, "books.txt");
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static int validateBookId(Scanner scanner) {
        int bookId;
        do {
            System.out.print("Enter the ID of the book: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for the book ID.");
                scanner.next();
            }
            bookId = scanner.nextInt();
        } while (bookId <= 0);
        return bookId;
    }

    private static String validateBookName(Scanner scanner) {
        String bookName;
        do {
            System.out.print("Enter the name of the book: ");
            bookName = scanner.nextLine().trim();
            if (bookName.isEmpty()) {
                System.out.println("Book name cannot be empty. Please enter a valid name.");
            }
        } while (bookName.isEmpty());
        return bookName;
    }

    private void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public static void saveBooksToFile(List<Book> books, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath ))) {
            for (Book book : books) {
                writer.write(book.toString() + "\n");
            }
            System.out.println("Books saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save books to file: " + e.getMessage());
        }
    }

    public static List<Book> loadBooksFromFile(String filePath) {
        List<Book> books = new ArrayList<>();
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length == 8) {
                    try {
                        int bookId = Integer.parseInt(bookData[0]);
                        String bookName = bookData[1];
                        String bookAuthor = bookData[2];
                        int bookYear = Integer.parseInt(bookData[6]);
                        int bookQuantity = Integer.parseInt(bookData[7]);
                        String bookGenre = bookData[3];
                        String bookLanguage = bookData[4];
                        int bookPages = Integer.parseInt(bookData[5]);

                        Book book = new Book(bookId, bookName, bookAuthor, bookYear, bookQuantity, bookGenre, bookLanguage, bookPages);
                        books.add(book);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing data for a book. Skipping line: " + line);
                    }
                } else {
                    System.err.println("Invalid number of fields for a book. Skipping line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read from file " + filePath + ": " + e.getMessage());
        }

        return books;
    }


    public static void searchForBook(String bookName) {
        boolean bookFound = false;
        StringBuilder resultText = new StringBuilder();

        List<Book> books = Book.loadBooksFromFile("books.txt");
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                resultText.append("Book ").append(book.getBookName()).append(" exists and there are ").append(book.getBookQuantity()).append(" copies available");
                bookFound = true;
                break;
            }
        }

        if (!bookFound) {
            resultText.append("Book not found");
        }

        bookListTextArea.setText(resultText.toString());
    }




    public static void listBooks() {
        List<Book> books = loadBooksFromFile("books.txt");

        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("List of Books:");
            for (Book book : books) {
                System.out.println("ID: " + book.getBookId());
                System.out.println("Name: " + book.getBookName());
                System.out.println("Author: " + book.getBookAuthor());
                System.out.println("Genre: " + book.getBookGenre());
                System.out.println("Language: " + book.getBookLanguage());
                System.out.println("Pages: " + book.getBookPages());
                System.out.println("Year: " + book.getBookYear());
                System.out.println("Quantity: " + book.getBookQuantity());
                System.out.println("----------------------");
            }
        }
    }

    public static void deleteBook(int bookId) {

        List<Book> books = loadBooksFromFile("books.txt");
        boolean bookFound = false;

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookId() == bookId) {
                iterator.remove();
                bookFound = true;
                break;
            }
        }
        if (bookFound) {
            saveBooksToFile(books, "books.txt");
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found. Deletion failed.");
        }
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
    public String toString() {
        return bookId + "," + bookName + "," + BookAuthor + "," + BookGenre + "," + BookLanguage + "," + BookPages + "," + bookYear + "," + bookQuantity;
    }

    private static boolean isBookIdExistsInFile(int bookId, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length > 0) {
                    try {
                        int existingBookId = Integer.parseInt(bookData[0]);
                        if (existingBookId == bookId) {
                            return true;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to check if book ID exists in file: " + e.getMessage());
        }
        return false;
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
        if (isBookIdExistsInFile(bookId, FILE_PATH)) {
            System.out.println("Book already reserved.");
        } else {
            bookQuantity++;
            System.out.println("Book reserved.");
        }
        scanner.close();
    }}