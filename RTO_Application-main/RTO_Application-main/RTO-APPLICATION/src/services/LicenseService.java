package services;
import db.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import java.util.Scanner;
import util.*;
import models.*;
public class LicenseService implements LicenseLayer{



   @Override
    public void applyForLicense(int userId) {
        try (Connection connection = DatabaseConnection.getConnection()) {

            String checkQuery = "SELECT * FROM license WHERE user_id = ? AND status IN ('pending', 'active')";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, User.getCurrentUserId());
            ResultSet checkResult = checkStmt.executeQuery();

            if (checkResult.next()) {
                System.out.println("You already have a pending or approved license application.");
                return;
            }


            String insertQuery = "INSERT INTO license (user_id, status) VALUES (?, 'pending')";
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setInt(1, User.getCurrentUserId());
            insertStmt.executeUpdate();

            System.out.println("License application submitted successfully! Your application is now pending approval.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method for checking the status of a license application
    @Override
    public void checkLicenseStatus() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT status, license_number FROM license WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, User.getCurrentUserId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String status = resultSet.getString("status");
                String license_number = resultSet.getString("license_number");
                System.out.println("License Status: " + status);
                if (status.equals("active")) {
                    System.out.println("License Number issued: " + license_number);
                }
            } else {
                System.out.println("No license application found. Please apply for a license first.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void viewPendingLicenses() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT user_id, license_id FROM license WHERE status = 'pending'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Pending License Applications:");
            System.out.println("-----------------------------");

            boolean hasPendingLicenses = false;

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int licenseId = resultSet.getInt("license_id");
                System.out.println("User ID: " + userId);
                System.out.println("License_ID: " + licenseId);
                System.out.println("-----------------------------");

                hasPendingLicenses = true;
            }

            if (!hasPendingLicenses) {
                System.out.println("No pending license applications found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void approveLicense() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.print("Enter the License ID to approve license: ");
            Scanner scanner = new Scanner(System.in);
            int licenseId = scanner.nextInt();

            // Check if there is a pending license application for the given user ID
            String checkQuery = "SELECT * FROM license WHERE license_id = ? AND status = 'pending'";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, licenseId);
            ResultSet checkResult = checkStmt.executeQuery();

            if (checkResult.next()) {
                // Generate license number
                String licenseNumber = IDGenerator.generateLicenseNumber();

                // Set issue date as current date
                Date issueDate = new Date(System.currentTimeMillis());

                // Calculate expiry date (15 years from issue date)
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(issueDate);
                calendar.add(Calendar.YEAR, 15);
                Date expiryDate = new Date(calendar.getTimeInMillis());

                // Update license status to 'ACTIVE'
                String updateQuery = "UPDATE license SET status = 'active', license_number = ?, issue_date = ?, expiry_date = ? WHERE license_id = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery);

                updateStmt.setString(1, licenseNumber);
                updateStmt.setDate(2, issueDate);
                updateStmt.setDate(3, expiryDate);
                updateStmt.setInt(4, licenseId);
                updateStmt.executeUpdate();

                System.out.println("License approved successfully!");
                System.out.println("License Number: " + licenseNumber);
                System.out.println("Issue Date: " + issueDate);
                System.out.println("Expiry Date: " + expiryDate);
            } else {
                System.out.println("No pending license application found for the given User ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}