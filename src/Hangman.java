
import java.util.HashSet;
import java.util.Set;

public class Hangman {
    private final String secretWord;
    private final int maxAttempts;
    private int attemptsLeft;
    private final Set<Character> guessedLetters = new HashSet<>();

    public Hangman(String word, int maxAttempts) {
        this.secretWord = word.toLowerCase();
        this.maxAttempts = maxAttempts;
        this.attemptsLeft = maxAttempts;
    }

    public boolean attemptGuess(char letter) {
        char lowerLetter = Character.toLowerCase(letter);
        if (guessedLetters.contains(lowerLetter)) {
            return true; // уже угадана — не тратим попытку
        }

        guessedLetters.add(lowerLetter);
        if (secretWord.indexOf(lowerLetter) >= 0) {
            return true; // угадал
        } else {
            attemptsLeft--;
            return false; // не угадал
        }
    }

    public String printWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            sb.append(guessedLetters.contains(c) ? c : '*');
        }
        return sb.toString();
    }

    public boolean isWin() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isLose() {
        return attemptsLeft <= 0;
    }

    public int getCurrentAttempts() {
        return attemptsLeft;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public Set<Character> getGuessedLetters() {
        return new HashSet<>(guessedLetters);
    }
}