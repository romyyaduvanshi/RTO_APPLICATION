
package util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation{


    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";


    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private static final String AADHAR_PATTERN =
            "^[2-9]{1}[0-9]{11}$";

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }


    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public boolean validateAadhar(String aadhar) {
        Pattern pattern = Pattern.compile(AADHAR_PATTERN);
        Matcher matcher = pattern.matcher(aadhar);
        return matcher.matches();
    }
}
