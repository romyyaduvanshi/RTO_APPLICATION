
package services;
import db.DatabaseConnection;
import models.User;
import util.Validation;

import javax.xml.crypto.Data;
import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;

public class UserProfileService {

    public String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");


        byte[] hashedPassword = md.digest(password.getBytes());


        return Base64.getEncoder().encodeToString(hashedPassword);
    }



    public void updatePassword() throws SQLException {
            Scanner scanner = new Scanner(System.in);
        Validation val = new Validation();
            try(Connection connection = DatabaseConnection.getConnection()){
//                System.out.print("Enter old password: ");
                Console console = System.console();
                if (console == null) {
                    throw new Exception("No console available");
                }
                char[] passwordArray = console.readPassword("Enter old password: ");
                String oldPassword = new String(passwordArray);
//                String oldPassword = scanner.nextLine();
                String hashOldPassword = hashPassword(oldPassword);


//                System.out.print("Enter new password: ");
                passwordArray = console.readPassword("Enter new password: ");
                String newPassword = new String(passwordArray);
//                String oldPassword = scanner.nextLine();
//                String newPassword="";
                while(!val.validatePassword(newPassword)) {
                    newPassword = scanner.nextLine();
                    System.out.println("Password must be at least 8 characters long, contain one digit, one uppercase letter, one lowercase letter, and one special character.");

                }
                String hashNewPassword = hashPassword(newPassword);


                String checkPasswordQuery = "SELECT password FROM user WHERE user_id = ?";
                PreparedStatement checkStmt = connection.prepareStatement(checkPasswordQuery);
                checkStmt.setInt(1, User.getCurrentUserId());
                ResultSet resultSet = checkStmt.executeQuery();

                if (resultSet.next()) {
                    String currentPassword = resultSet.getString("password");

                    if (currentPassword.equals(hashOldPassword)) {

                        String updatePasswordQuery = "UPDATE user SET password = ? WHERE user_id = ?";
                        PreparedStatement updateStmt = connection.prepareStatement(updatePasswordQuery);
                        updateStmt.setString(1, hashNewPassword);
                        updateStmt.setInt(2, User.getCurrentUserId());
                        int rowsUpdated = updateStmt.executeUpdate();

                        if (rowsUpdated > 0) {
                            System.out.println("Password updated successfully!");
                        } else {
                            System.out.println("Failed to update password.");
                        }
                    } else {
                        System.out.println("Old password is incorrect.");
                    }
                }
            }catch(SQLException e){
                System.out.println(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }


    public void updateAddress() throws SQLException {



        Scanner scanner = new Scanner(System.in);
        try(Connection connection = DatabaseConnection.getConnection()){
            System.out.print("Enter new address: ");
            String newAddress = scanner.nextLine();

            String updateAddressQuery = "UPDATE user SET address = ? WHERE user_id = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateAddressQuery);
            updateStmt.setString(1, newAddress);
            updateStmt.setInt(2, User.getCurrentUserId());
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Address updated successfully!");
            } else {
                System.out.println("Failed to update address.");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
