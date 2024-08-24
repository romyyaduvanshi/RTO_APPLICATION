package services;

import db.DatabaseConnection;
import models.User;
import util.IDGenerator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class VehicleService implements VehicleLayer{


    @Override
    public void applyForVehicleRegistration() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Check if the user has an active license
            String licenseQuery = "SELECT status FROM license WHERE user_id = ? AND status = 'active'";
            PreparedStatement licenseStmt = connection.prepareStatement(licenseQuery);
            licenseStmt.setInt(1, User.getCurrentUserId());
            ResultSet licenseResult = licenseStmt.executeQuery();

            Scanner scanner = new Scanner(System.in);
            if (licenseResult.next()) {
                // Insert a new vehicle registration application with status 'PENDING'
                System.out.println("Enter type of Vehicle");
                String vehicleType = scanner.nextLine();
                String insertQuery = "INSERT INTO vehicle (user_id, vehicle_type, status) VALUES (?, ?, 'pending')";
                PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
                insertStmt.setInt(1, User.getCurrentUserId());
                insertStmt.setString(2, vehicleType);
                insertStmt.executeUpdate();

                System.out.println("Vehicle registration application submitted successfully! Your application is now pending approval.");
            } else {
                System.out.println("You need an active license to apply for vehicle registration.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method for checking vehicle registration status by user
    @Override
    public void checkVehicleRegistrationStatus() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT vehicle_type, registration_number, status, registration_date FROM vehicle WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, User.getCurrentUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isRegistered = false;
            while (resultSet.next()) {
                String vehicleType = resultSet.getString("vehicle_type");
                String status = resultSet.getString("status");
                String registrationNumber = resultSet.getString("registration_number");
                Date registrationDate = resultSet.getDate("registration_date");

                System.out.println("Vehicle: " + vehicleType);
                System.out.println("Registration Status: " + status);
                if(status.equals("active"))
                {
                    System.out.println("Registration Date: " + registrationDate);
                    System.out.println("Registration Number: " + registrationNumber);
                }

                isRegistered = true;
            }
            if(!isRegistered){
                System.out.println("No vehicles registered");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method for viewing pending vehicle registration applications by admin
    @Override
    public void viewPendingRegistrations() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT vehicle_id, user_id, vehicle_type FROM vehicle WHERE status = 'pending'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Pending Vehicle Registration Applications:");
            System.out.println("------------------------------------------");

            boolean hasPendingRegistrations = false;

            while (resultSet.next()) {
                int id = resultSet.getInt("vehicle_id");
                int userId = resultSet.getInt("user_id");
                String vehicleType = resultSet.getString("vehicle_type");

                System.out.println("Vehicle Application ID: " + id);
                System.out.println("User ID: " + userId);
                System.out.println("Vehicle Details: " + vehicleType);
                System.out.println("------------------------------------------");

                hasPendingRegistrations = true;
            }

            if (!hasPendingRegistrations) {
                System.out.println("No pending vehicle registration applications found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method for approving vehicle registration by admin
    @Override
    public void approveVehicleRegistration() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Vehicle ID to approve vehicle registration: ");
            int vehicleId = scanner.nextInt();

            // Check if the application is pending
            String checkQuery = "SELECT * FROM vehicle WHERE vehicle_id = ? AND status = 'pending'";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, vehicleId);
            ResultSet checkResult = checkStmt.executeQuery();

            if (checkResult.next()) {
                // Set approval date as current date
                Date registrationDate = new Date(System.currentTimeMillis());
                String registrationNumber= IDGenerator.generateVehicleRegistrationNumber();

                String updateQuery = "UPDATE vehicle SET status = 'active', registration_date = ?, registration_number = ? WHERE vehicle_id = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                updateStmt.setDate(1, registrationDate);
                updateStmt.setString(2, registrationNumber);
                updateStmt.setInt(3, vehicleId);
                updateStmt.executeUpdate();

                System.out.println("Vehicle registration approved successfully!");
            } else {
                System.out.println("No pending vehicle registration application found with the given Application ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
