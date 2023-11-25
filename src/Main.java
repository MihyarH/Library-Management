import Exceptions.DuplicateBookNameException;
import Exceptions.DuplicateUsernameException;
import Users.LoginManager;

public class Main {
    public static void main(String[] args) throws DuplicateUsernameException, DuplicateBookNameException {
        LoginManager.login();
    }
}