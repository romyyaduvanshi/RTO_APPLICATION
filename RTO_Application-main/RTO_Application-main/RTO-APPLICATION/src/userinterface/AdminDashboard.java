package userinterface;

import services.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminDashboard implements Screen {

    @Override
    public void show(Screen currentScreen) throws NoSuchAlgorithmException, SQLException {
        Scanner scanner = new Scanner(System.in);
        LicenseService licenseservice = new LicenseService();
        ChallanService challanService = new ChallanService();
        VehicleService vehicleService = new VehicleService();
        System.out.println("=== Admin Dashboard ===");
        System.out.println("1. Approve License");
        System.out.println("2. View Pending Licenses");
        System.out.println("3. Issue Challan");
        System.out.println("4. Check Pending Vehicle Registration Status");
        System.out.println("5. Approve Vehicle Registration");

        System.out.println("0. Logout");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                licenseservice.approveLicense();
                break;
            case 2:
                licenseservice.viewPendingLicenses();
                break;
            case 3:
                challanService.addChallan();
                break;
            case 4:
                vehicleService.viewPendingRegistrations();
                break;
            case 5:
                vehicleService.approveVehicleRegistration();
                break;
            case 0:
                System.out.println("Logging out...");
                // Redirect to login screen
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                currentScreen.show(currentScreen);
                break;
        }
        int c=-1;
        while(c!=0 && c!=1) {
            System.out.println("Enter 1 to continue and 0 to Exit");
            c = scanner.nextInt();
            if (c == 1) {
                currentScreen.show(currentScreen);
            }
            if (c == 0) {
                currentScreen = new HomeScreen();
                currentScreen.show(currentScreen);
            }
        }
    }
}
