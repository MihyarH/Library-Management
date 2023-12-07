import Exceptions.DuplicateBookNameException;
import Exceptions.DuplicateUsernameException;
import UI.LoginManagerGUI;
import Users.LoginManager;

public class Main {
    public static void main(String[] args) throws DuplicateUsernameException, DuplicateBookNameException {
//        LoginManager.login();
        LoginManagerGUI.main(args);
    }
}