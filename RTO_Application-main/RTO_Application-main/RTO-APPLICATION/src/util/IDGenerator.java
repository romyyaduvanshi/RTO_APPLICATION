package util;

import java.util.Random;

public class IDGenerator {
    public static String generateLicenseNumber() {
        String lNum = "L";
        for(int i = 0; i < 9; i++) {
            Random random = new Random();
            // Generate a random integer between 0 and 9
            int randomNumber = random.nextInt(10);
            String s = Integer.toString(randomNumber);
            lNum = lNum + s;
        }
        return lNum;
    }
    public static String generateVehicleRegistrationNumber() {
        String registrationNumber = "HR";
        Random random = new Random();

        // Generate the middle part (e.g., "26" as a district code)
        int districtCode = 26;  // Generates a number between 10 and 99
        registrationNumber += " " + districtCode;

        char firstLetter = (char) ('A' + random.nextInt(26));  // Generates a letter between A and Z
        char secondLetter = (char) ('A' + random.nextInt(26)); // Generates another letter between A and Z
        String letterSeries = "" + firstLetter + secondLetter;
        registrationNumber += "" + letterSeries;
        // Generate the last part (e.g., "1234" as a unique number)
        int uniqueNumber = 1000 + random.nextInt(9000);  // Generates a number between 1000 and 9999
        registrationNumber += " " + uniqueNumber;

        return registrationNumber;
    }
}
