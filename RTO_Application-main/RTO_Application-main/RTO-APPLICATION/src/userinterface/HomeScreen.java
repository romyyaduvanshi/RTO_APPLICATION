package userinterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import models.User;
public class HomeScreen implements Screen{
    @Override
    public void show(Screen currentScreen) throws NoSuchAlgorithmException, SQLException {
        User.setCurrentUserId(0);
        User.currentRole = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Home Screen ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                currentScreen = new LoginScreen();
                currentScreen.show(currentScreen);
                break;
            case 2:
                currentScreen = new RegisterScreen();
                currentScreen.show(currentScreen);
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}
