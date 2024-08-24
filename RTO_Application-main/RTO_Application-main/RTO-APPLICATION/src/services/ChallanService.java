package services;


import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

import db.DatabaseConnection;
import models.User;

public class ChallanService implements ChallanLayer{

    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void addChallan() {

        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.print("Enter User ID: ");
            int user_id = scanner.nextInt();
            System.out.print("Enter Challan Amount: ");
            double amount = scanner.nextDouble();
            System.out.println("Enter Challan Description");
            scanner.nextLine();
            String desc = scanner.nextLine();
            Date issueDate = new Date(System.currentTimeMillis());
            String sql = "INSERT INTO Challan (user_id, amount, challan_date, description, status) VALUES (?, ?, ?, ?, 'unpaid')";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user_id);
            stmt.setBigDecimal(2, BigDecimal.valueOf(amount));
            stmt.setDate(3,issueDate);
            stmt.setString(4, desc);
            stmt.executeUpdate();

            System.out.println("Challan added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding challan: " + e.getMessage());
        }
    }
    @Override
    public void payChallan() {
        try  (Connection connection = DatabaseConnection.getConnection()) {
            System.out.print("Enter Challan ID: ");
            int challan_id = scanner.nextInt();

            String checkStatusSql = "SELECT status FROM challan WHERE challan_id = ?";
            PreparedStatement checkStatusStmt = connection.prepareStatement(checkStatusSql);
            checkStatusStmt.setInt(1, challan_id);
            ResultSet rs = checkStatusStmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                if ("paid".equalsIgnoreCase(status)) {
                    System.out.println("This challan has already been paid.");
                } else {
                    String updateSql = "UPDATE challan SET status = 'paid' WHERE challan_id = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                    updateStmt.setInt(1, challan_id);
                    updateStmt.executeUpdate();
                    System.out.println("Challan paid successfully.");
                }
            } else {
                System.out.println("Challan not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error paying challan: " + e.getMessage());
        }
    }
    @Override
    public void checkChallanStatus() {
        try (Connection connection = DatabaseConnection.getConnection()) {


            String sql = "SELECT * FROM Challan WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, User.getCurrentUserId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int challanId = rs.getInt("challan_id");
                double amount = rs.getBigDecimal("amount").doubleValue();
                String status = rs.getString("status");
                java.util.Date issueDate = rs.getDate("challan_date");
                String desc = rs.getString("description");

                System.out.println("Challan ID: " + challanId);
                System.out.println("Amount: " + amount);
                System.out.println("Status: " + status);
                System.out.println("Issue Date: " + issueDate);
                System.out.println("Description: " + desc);

                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error checking challan status: " + e.getMessage());
        }
    }
}

