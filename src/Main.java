import Exceptions.DuplicateBookNameException;
import Exceptions.DuplicateUsernameException;
import Modules.Book;
import Modules.Interfaces.Reservable;
import Users.LoginManager;
import Users.User;
import Users.UserRole;

public class Main {
    public static void main(String[] args) throws DuplicateUsernameException, DuplicateBookNameException {
        LoginManager.login();
    }
}