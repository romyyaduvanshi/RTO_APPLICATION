package userinterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

import models.User;
import services.*;
public class UserDashboard implements Screen {


    @Override
    public void show(Screen currentScreen) throws NoSuchAlgorithmException, SQLException {
        Scanner scanner = new Scanner(System.in);
        LicenseLayer licenseService = new LicenseService();
        ChallanLayer challanService = new ChallanService();
        VehicleLayer vehicleService = new VehicleService();
        System.out.println("=== User Dashboard ===");
        System.out.println("1. Check License Status");
        System.out.println("2. Apply for License");
        System.out.println("3. Register Vehicle");
        System.out.println("4. Check Vehicle Registration Status");
        System.out.println("5. Check Challans");
        System.out.println("6. Pay Challans");
        System.out.println("7. Update Profile");
        System.out.println("0. Logout");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                licenseService.checkLicenseStatus();
                break;
            case 2:
                licenseService.applyForLicense(User.getCurrentUserId());
                break;
            case 3:
                vehicleService.applyForVehicleRegistration();
                break;
            case 4:
                vehicleService.checkVehicleRegistrationStatus();
                break;
            case 5:
                challanService.checkChallanStatus();
                break;
            case 6:
                challanService.payChallan();
                break;
            case 7:
                currentScreen = new UpdateProfile();
                currentScreen.show(currentScreen);
                break;
            case 0:
                System.out.println("Logging out...");
                currentScreen = new HomeScreen();
                currentScreen.show(currentScreen);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                currentScreen.show(currentScreen);
                break;
        }
        System.out.print("\n\n\n\n");
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
