import java.util.List;

public class PasswordResult {

    private int length;
    private boolean uppercase;
    private boolean lowercase;
    private boolean number;
    private boolean specialCharacter;
    private boolean commonPassword;
    private boolean repeatedCharacters;
    private boolean sequentialPattern;
    private int score;
    private String strength;
    private List<String> suggestions;

    public PasswordResult(
            int length,
            boolean uppercase,
            boolean lowercase,
            boolean number,
            boolean specialCharacter,
            boolean commonPassword,
            boolean repeatedCharacters,
            boolean sequentialPattern,
            int score,
            String strength,
            List<String> suggestions) {

        this.length = length;
        this.uppercase = uppercase;
        this.lowercase = lowercase;
        this.number = number;
        this.specialCharacter = specialCharacter;
        this.commonPassword = commonPassword;
        this.repeatedCharacters = repeatedCharacters;
        this.sequentialPattern = sequentialPattern;
        this.score = score;
        this.strength = strength;
        this.suggestions = suggestions;
    }

    public int getLength() {
        return length;
    }

    public boolean hasUppercase() {
        return uppercase;
    }

    public boolean hasLowercase() {
        return lowercase;
    }

    public boolean hasNumber() {
        return number;
    }

    public boolean hasSpecialCharacter() {
        return specialCharacter;
    }

    public boolean isCommonPassword() {
        return commonPassword;
    }

    public boolean hasRepeatedCharacters() {
        return repeatedCharacters;
    }

    public boolean hasSequentialPattern() {
        return sequentialPattern;
    }

    public int getScore() {
        return score;
    }

    public String getStrength() {
        return strength;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}