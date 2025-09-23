
import Hangman;
import database.Loader;

import java.util.Arrays;

public class NonInteractiveMode {

    public static void run(String secretWord, String guessedLetters) {
        if (secretWord == null || secretWord.trim().isEmpty()) {
            System.out.println("Ошибка ввода слова");
            return;
        }

        secretWord = secretWord.toLowerCase();
        guessedLetters = guessedLetters.toLowerCase();

        Hangman game = new Hangman(secretWord, 6);

        // Обрабатываем ввод: либо через запятую, либо как строку
        if (guessedLetters.contains(",")) {
            String[] letters = guessedLetters.split(",");
            for (String letter : letters) {
                if (!letter.trim().isEmpty()) {
                    game.attemptGuess(letter.trim().charAt(0));
                }
            }
        } else {
            for (char c : guessedLetters.toCharArray()) {
                if (Character.isLetter(c)) {
                    game.attemptGuess(c);
                }
            }
        }

        String display = game.printWord();
        String result = game.isWin() ? "win" : "lose";
        System.out.println(display + ";" + result);
    }
}