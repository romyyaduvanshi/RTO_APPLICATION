package models;
import java.util.Date;

public class Challan {
    private int challanId;
    private int userId;
    private Date challanDate;
    private double amount;
    private String status;
    private String description;

    // Constructor with all fields
    public Challan(int challanId, int userId, Date challanDate, double amount, String status, String description) {
        this.challanId = challanId;
        this.userId = userId;
        this.challanDate = challanDate;
        this.amount = amount;
        this.status = status;
        this.description = description;
    }

    // Default constructor
    public Challan() {}

    // Getters and Setters
    public int getChallanId() {
        return challanId;
    }

    public void setChallanId(int challanId) {
        this.challanId = challanId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getChallanDate() {
        return challanDate;
    }

    public void setChallanDate(Date challanDate) {
        this.challanDate = challanDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Challan{" +
                "challanId=" + challanId +
                ", userId=" + userId +
                ", challanDate=" + challanDate +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
