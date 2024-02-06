import Exceptions.DuplicateBookNameException;
import Exceptions.DuplicateUsernameException;
import Modules.Book;
import UI.LoginManagerGUI;

public class Main {
    public static void main(String[] args) throws DuplicateUsernameException, DuplicateBookNameException {
//        LoginManagerGUI.main(args);
//        Book.listBooks();
        Book.searchForBook("Book5");
    }
}