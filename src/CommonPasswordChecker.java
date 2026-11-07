import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CommonPasswordChecker {

    private Set<String> commonPasswords;

    public CommonPasswordChecker(String filePath) throws IOException {

        commonPasswords = new HashSet<>();

        BufferedReader reader = new BufferedReader(
                new FileReader(filePath)
        );

        String line;

        while ((line = reader.readLine()) != null) {

            line = line.trim().toLowerCase();

            if (!line.isEmpty()) {
                commonPasswords.add(line);
            }
        }

        reader.close();
    }

    public boolean isCommonPassword(String password) {

        return commonPasswords.contains(
                password.toLowerCase()
        );
    }
}