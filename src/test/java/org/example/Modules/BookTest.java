package org.example.Modules;
import org.example.BooksDB.BooksDataManipulator;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
class BookTest {
    @Test
    void createBook() {
        Book book = new Book(1,"Test Book","Test Author",2022,10,
                "English");
        assertEquals("Test Book", book.getBookName());
        assertEquals("Test Author", book.getBookAuthor());
        assertEquals(2022, book.getBookYear());
        assertEquals(10, book.getBookQuantity());
        assertEquals("English", book.getBookLanguage());
        assertEquals(1, book.getBookId());
    }
    @Test
    void testUpdateBookInDatabase() {
        try (MockedStatic<BooksDataManipulator> mockedStatic = Mockito.mockStatic(BooksDataManipulator.class)) {
            mockedStatic.when(() -> BooksDataManipulator.checkIfBookExists("Test Book")).thenReturn(true);
            Book.updateBook(1, "New Book Name", "New Author", 10, 100,
                    "Romanian",2023);
        }
    }
    @Test
    void testListBooksInDatabase() {
        try (MockedStatic<BooksDataManipulator> mockedStatic = Mockito.mockStatic(BooksDataManipulator.class)) {
            Book.listBooks();
        }
    }
    @Test
    void testDeleteBookInDatabase() {
        try (MockedStatic<BooksDataManipulator> mockedStatic = Mockito.mockStatic(BooksDataManipulator.class)) {
            mockedStatic.when(() -> BooksDataManipulator.checkIfBooksExistsUsingId(1)).thenReturn(true);
            Book.deleteBook(1);
        }
    }
}
