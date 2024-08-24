package models;

import java.util.Date;

public class Vehicle {
    private int vehicleId;
    private int userId;
    private String registrationNumber;
    private String vehicleType;
    private Date registrationDate;
    private String status;

    // Constructor with all fields
    public Vehicle(int vehicleId, int userId, String registrationNumber, String vehicleType, Date registrationDate, String status) {
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    // Default constructor
    public Vehicle() {}

    // Getters and Setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", userId=" + userId +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", registrationDate=" + registrationDate +
                ", status='" + status + '\'' +
                '}';
    }
}
