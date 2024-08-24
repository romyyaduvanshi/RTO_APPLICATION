import userinterface.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {

        Screen currentScreen;
        currentScreen = new HomeScreen();
        currentScreen.show(currentScreen);

    }
}