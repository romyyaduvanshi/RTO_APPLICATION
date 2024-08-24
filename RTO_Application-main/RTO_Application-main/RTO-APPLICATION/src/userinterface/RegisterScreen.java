package userinterface;

import models.User;
import util.Authorization;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.io.*;
import util.*;
public class RegisterScreen implements Screen {


    public String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");


        byte[] hashedPassword = md.digest(password.getBytes());


        return Base64.getEncoder().encodeToString(hashedPassword);
    }
    @Override
    public void show(Screen currentScreen) throws NoSuchAlgorithmException, SQLException {
        String name="";
        String username="";
        String hashedPass="";
        String aadhar="";
        String address="";
        Validation val = new Validation();
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== Register Screen ===");
            System.out.print("Enter your name: ");
            name = scanner.nextLine();

            System.out.print("Enter username: ");
            username = scanner.nextLine();
            if (!val.validateEmail(username)) {
                System.out.println("Invalid email format.");
                currentScreen.show(currentScreen);
            }
            System.out.print("Enter your address: ");
            address = scanner.nextLine();
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
            System.out.print("Enter Aadhar: ");
            aadhar = scanner.nextLine();
            if (!val.validateAadhar(aadhar)) {
                System.out.println("Invalid Aadhar Number.");
                currentScreen.show(currentScreen);
            }
//            java.util.Arrays.fill(passwordArray, ' ');

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        User user = new User(username, hashedPass, name, aadhar, address);




        Authorization auth = new Authorization();

        boolean isRegistered = auth.registerUser(user);
        if (isRegistered) {
            System.out.println("Registration successful! You can now log in.");
            currentScreen = new HomeScreen();
            currentScreen.show(currentScreen);
        } else {

            System.out.println("Username already exists. Please try again.");
            currentScreen.show(currentScreen);
        }
    }
}
