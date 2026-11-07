import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("      PASSWORD SECURITY ANALYZER");
        System.out.println("======================================");

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try {
            CommonPasswordChecker checker =
                    new CommonPasswordChecker("data/common_passwords.txt");

            PasswordAnalyzer analyzer =
                    new PasswordAnalyzer(checker);

            PasswordResult result = analyzer.analyze(password);

            System.out.println("\n========== ANALYSIS RESULT ==========");

            System.out.println("Password Length       : " + result.getLength());
            System.out.println("Uppercase Letter      : " + result.hasUppercase());
            System.out.println("Lowercase Letter      : " + result.hasLowercase());
            System.out.println("Number                : " + result.hasNumber());
            System.out.println("Special Character     : " + result.hasSpecialCharacter());
            System.out.println("Common Password       : " + result.isCommonPassword());
            System.out.println("Repeated Characters   : " + result.hasRepeatedCharacters());

            System.out.println("--------------------------------------");
            System.out.println("Security Score        : " + result.getScore() + "/10");
            System.out.println("Password Strength     : " + result.getStrength());

            System.out.println("\n========== RECOMMENDATIONS ==========");

            if (result.getSuggestions().isEmpty()) {
                System.out.println("Your password satisfies all basic checks!");
            } else {
                for (String suggestion : result.getSuggestions()) {
                    System.out.println("- " + suggestion);
                }
            }

            System.out.println("======================================");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}