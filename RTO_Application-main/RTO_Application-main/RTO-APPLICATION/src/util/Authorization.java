package util;
import models.*;
import db.*;
import java.sql.*;
public class Authorization {
    public boolean checkUser(User user){
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User.currentUserId = resultSet.getInt("user_id");  // Store the user ID
                User.currentRole = resultSet.getString("role");
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean registerUser(User user){
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO user (username, password, name, aadhar, address, role) VALUES (?, ?, ?, ?, ?, 'USER')";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getAadhar());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.executeUpdate();
            System.out.println("Registration successful!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
