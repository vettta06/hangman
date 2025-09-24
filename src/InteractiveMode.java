import database.Loader;
import database.WordBase;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InteractiveMode {
    public static String selectCategory(Map<String, Map<String, List<String>>> categories) {
        System.out.print("Выберите категорию или оставьте эту ячейку пустой: ");
        String category = Game.scanner.nextLine().trim();
        if (category.isEmpty() || !categories.containsKey(category)) {
            category = getRandomKey(categories.keySet());
            System.out.println("Выбрана случайная категория: '" + category + "'");
        }
        return category;
    }


    public static String selectDifficulty(Map<String, List<String>> categoryMap) {
        List<String> difficulties = List.copyOf(categoryMap.keySet());
        Game.printCategories("Доступные уровни сложности:", difficulties);
        System.out.print("Выберите сложность или оставьте пустым: ");
        String difficulty = Game.scanner.nextLine().trim();
        if (difficulty.isEmpty() || !categoryMap.containsKey(difficulty)) {
            difficulty = getRandomKey(difficulties);
            System.out.println("Выбран случайный уровень сложности: '" + difficulty + "'");
        }
        return difficulty;
    }


    public static String getRandomKey(java.util.Collection<String> collection) {
        java.util.List<String> list = java.util.List.copyOf(collection);
        return list.get((int) (Math.random() * list.size()));
    }


    public static void run() {
        Map<String, Map<String, List<String>>> categories = WordBase.getWordsByCategory();
        Game.printHead("Игра «Виселица»");
        Game.printCategories("Доступные категории:", List.copyOf(categories.keySet()));

        String category = selectCategory(categories);
        String difficulty = selectDifficulty(categories.get(category));

        Loader.WordWithHint wordInfo = Loader.getRandomWord(category, difficulty);

        System.out.println("\nВыбрано: категория '" + category + "', сложность '" + difficulty + "'");
        Game.showHint(wordInfo.hint);

        Hangman game = new Hangman(wordInfo.word, 6);
        System.out.println("У вас есть " + game.getCurrentAttempts() + " попыток.\n");
        Game.printState(game);

        while (!game.isWin() && !game.isLose()) {
            String guess = Game.getUserInput();
            if (guess == null) continue;

            char letter = guess.charAt(0);
            if (game.getGuessedLetters().contains(letter)) {
                System.out.println("Эта буква уже угадана!\n");
            } else {
                boolean correct = game.attemptGuess(letter);
                if (correct) {
                    System.out.println("Верно!\n");
                } else {
                    int wrongAnswers = 6 - game.getCurrentAttempts();
                    Ui.draw(wrongAnswers);
                }
            }
            Game.printState(game);
        }


        if (game.isLose()) {
            int wrongAnswers = 6 - game.getCurrentAttempts();
            Ui.draw(wrongAnswers);
        }
        Game.printResult(game.getSecretWord(), game.isWin());
    }
}