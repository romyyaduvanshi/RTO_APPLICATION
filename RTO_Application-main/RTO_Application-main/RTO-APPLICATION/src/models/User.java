package models;

public class User {
    public static int currentUserId;
    public static String currentRole;
    private String username;
    private String password;
    private String name;
    private String aadhar;
    private String role;
    private String address;

    // Constructor
    public User(String username, String password, String name, String aadhar, String address) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.aadhar = aadhar;
        this.role = role;
        this.address = address;
    }

    // Default constructor (in case you need it)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and Setter methods
    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
    public String getAddress() {
        return address;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public void updateProfile(){

    }
    // To String method for debugging purposes
    @Override
    public String toString() {
        return "User{" +
                "userId=" + currentUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", aadhar='" + aadhar + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
