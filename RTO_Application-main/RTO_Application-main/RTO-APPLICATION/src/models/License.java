package models;

import java.util.Date;

public class License {
    private int licenseId;
    private int userId;
    private String licenseNumber;
    private Date issueDate;
    private Date expiryDate;
    private String status;

    // Constructor with all fields
    public License(int licenseId, int userId, String licenseNumber, Date issueDate, Date expiryDate, String status) {
        this.licenseId = licenseId;
        this.userId = userId;
        this.licenseNumber = licenseNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    // Default constructor
    public License() {}

    // Getters and Setters
    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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
        return "License{" +
                "licenseId=" + licenseId +
                ", userId=" + userId +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", issueDate=" + issueDate +
                ", expiryDate=" + expiryDate +
                ", status='" + status + '\'' +
                '}';
    }
}
