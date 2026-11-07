import java.util.ArrayList;
import java.util.List;

public class PasswordAnalyzer {

    private CommonPasswordChecker commonPasswordChecker;

    public PasswordAnalyzer(CommonPasswordChecker commonPasswordChecker) {
        this.commonPasswordChecker = commonPasswordChecker;
    }

    public PasswordResult analyze(String password) {

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            }

            if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            }

            if (Character.isDigit(ch)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(ch)) {
                hasSpecialCharacter = true;
            }
        }

        boolean commonPassword =
                commonPasswordChecker.isCommonPassword(password);

        boolean repeatedCharacters =
                containsRepeatedCharacters(password);

        boolean sequentialPattern =
                containsSequentialPattern(password);

        int score = calculateScore(
                password,
                hasUppercase,
                hasLowercase,
                hasNumber,
                hasSpecialCharacter,
                commonPassword,
                repeatedCharacters,
                sequentialPattern
        );

        String strength = determineStrength(score);

        List<String> suggestions =
                generateSuggestions(
                        password,
                        hasUppercase,
                        hasLowercase,
                        hasNumber,
                        hasSpecialCharacter,
                        commonPassword,
                        repeatedCharacters,
                        sequentialPattern
                );

        return new PasswordResult(
                password.length(),
                hasUppercase,
                hasLowercase,
                hasNumber,
                hasSpecialCharacter,
                commonPassword,
                repeatedCharacters,
                sequentialPattern,
                score,
                strength,
                suggestions
        );
    }


    private int calculateScore(
            String password,
            boolean uppercase,
            boolean lowercase,
            boolean number,
            boolean specialCharacter,
            boolean commonPassword,
            boolean repeatedCharacters,
            boolean sequentialPattern) {

        int score = 0;

        if (password.length() >= 8) {
            score += 2;
        }

        if (password.length() >= 12) {
            score += 1;
        }

        if (uppercase) {
            score += 1;
        }

        if (lowercase) {
            score += 1;
        }

        if (number) {
            score += 1;
        }

        if (specialCharacter) {
            score += 2;
        }

        if (commonPassword) {
            score -= 3;
        }

        if (repeatedCharacters) {
            score -= 1;
        }

        if (sequentialPattern) {
            score -= 1;
        }

        if (score < 0) {
            score = 0;
        }

        if (score > 10) {
            score = 10;
        }

        return score;
    }


    private String determineStrength(int score) {

        if (score <= 2) {
            return "VERY WEAK";
        }

        if (score <= 4) {
            return "WEAK";
        }

        if (score <= 6) {
            return "MODERATE";
        }

        if (score <= 8) {
            return "STRONG";
        }

        return "VERY STRONG";
    }


    private boolean containsRepeatedCharacters(String password) {

        if (password.length() < 2) {
            return false;
        }

        for (int i = 1; i < password.length(); i++) {

            if (password.charAt(i) == password.charAt(i - 1)) {
                return true;
            }
        }

        return false;
    }


    private boolean containsSequentialPattern(String password) {

        if (password.length() < 3) {
            return false;
        }

        for (int i = 0; i < password.length() - 2; i++) {

            char first =
                    Character.toLowerCase(password.charAt(i));

            char second =
                    Character.toLowerCase(password.charAt(i + 1));

            char third =
                    Character.toLowerCase(password.charAt(i + 2));

            if (second == first + 1 &&
                    third == second + 1) {

                return true;
            }

            if (second == first - 1 &&
                    third == second - 1) {

                return true;
            }
        }

        return false;
    }


    private List<String> generateSuggestions(
            String password,
            boolean uppercase,
            boolean lowercase,
            boolean number,
            boolean specialCharacter,
            boolean commonPassword,
            boolean repeatedCharacters,
            boolean sequentialPattern) {

        List<String> suggestions = new ArrayList<>();

        if (password.length() < 8) {
            suggestions.add(
                    "Use at least 8 characters."
            );
        }

        if (password.length() < 12) {
            suggestions.add(
                    "For better security, consider using 12 or more characters."
            );
        }

        if (!uppercase) {
            suggestions.add(
                    "Add at least one uppercase letter."
            );
        }

        if (!lowercase) {
            suggestions.add(
                    "Add at least one lowercase letter."
            );
        }

        if (!number) {
            suggestions.add(
                    "Add at least one number."
            );
        }

        if (!specialCharacter) {
            suggestions.add(
                    "Add at least one special character."
            );
        }

        if (commonPassword) {
            suggestions.add(
                    "Avoid commonly used passwords."
            );
        }

        if (repeatedCharacters) {
            suggestions.add(
                    "Avoid consecutive repeated characters."
            );
        }

        if (sequentialPattern) {
            suggestions.add(
                    "Avoid predictable sequential patterns such as 123 or abc."
            );
        }

        return suggestions;
    }
}