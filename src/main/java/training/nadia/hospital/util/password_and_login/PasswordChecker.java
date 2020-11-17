package training.nadia.hospital.util.password_and_login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

    private static final int MIN_NUMBER_OF_SYMBOLS_IN_PASSWORD = 8;

    private PasswordChecker() {
    }

    public static boolean passwordIsSafe(String password) {

        return passwordLengthIsCorrect(password) &&
                isPasswordContainsLettersAndNumbers(password);
    }

    private static boolean passwordLengthIsCorrect(String password) {
        return password.length() >= MIN_NUMBER_OF_SYMBOLS_IN_PASSWORD;
    }

    private static boolean isPasswordContainsLettersAndNumbers(String password) {

        Pattern pattern = Pattern.compile("(.*\\d.*)(.*[a-zA-Z].*)");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
