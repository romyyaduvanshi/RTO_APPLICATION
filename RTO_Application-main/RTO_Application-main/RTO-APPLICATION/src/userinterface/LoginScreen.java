package userinterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Objects;
import java.util.Scanner;
import java.io.*;
import models.*;
import util.*;

public class LoginScreen implements Screen {

    public String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");


        byte[] hashedPassword = md.digest(password.getBytes());


        return Base64.getEncoder().encodeToString(hashedPassword);
    }


    @Override
    public void show(Screen currentScreen) throws NoSuchAlgorithmException, SQLException {

        String username="";
        String hashedPass="";
        Validation val = new Validation();
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== Login Screen ===");
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            if (!val.validateEmail(username)) {
                System.out.println("Invalid email format.");
                currentScreen.show(currentScreen);
            }
//            System.out.print("Enter password: ");
            Console console = System.console();


            if (console == null) {
                throw new Exception("No console available");
            }

            char[] passwordArray = console.readPassword("Enter your password: ");
            String password = new String(passwordArray);
//            String password = scanner.nextLine();
            if (!val.validatePassword(password)) {
                System.out.println("Password must be at least 8 characters long, contain one digit, one uppercase letter, one lowercase letter, and one special character.");
                currentScreen.show(currentScreen);
            }
            hashedPass = hashPassword(password);

//            java.util.Arrays.fill(passwordArray, ' ');

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        User user = new User(username, hashedPass);






        Authorization auth = new Authorization();



        boolean isAuthenticated = auth.checkUser(user);
        if (isAuthenticated) {
            System.out.println("Login successful! ");

            if(Objects.equals(User.currentRole, "user"))
            {
                currentScreen = new UserDashboard();
                currentScreen.show(currentScreen);
            }
            if(Objects.equals(User.currentRole, "admin"))
            {
                currentScreen = new AdminDashboard();
                currentScreen.show(currentScreen);
            }


        } else {
            currentScreen.show(currentScreen);
            System.out.println("Invalid credentials, please try again.");
        }
    }
}
