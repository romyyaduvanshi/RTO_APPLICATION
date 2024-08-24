package userinterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import services.*;
public class UpdateProfile implements Screen {


    // Method to update the user's profile (password and address)
    public void show(Screen currentScreen) throws NoSuchAlgorithmException, SQLException {

            System.out.println("Update Profile Details:");
            UserProfileService ups = new UserProfileService();
            // Option to update password
            System.out.println("1. Update Password");
            System.out.println("2. Update Address");
            System.out.println("3. Cancel");
            System.out.print("Choose an option: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ups.updatePassword();
                    currentScreen = new UserDashboard();
                    currentScreen.show(currentScreen);
                    break;
                case 2:
                    ups.updateAddress();
                    currentScreen = new UserDashboard();
                    currentScreen.show(currentScreen);
                    break;
                case 3:
                    System.out.println("Update cancelled.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }


    }
}




