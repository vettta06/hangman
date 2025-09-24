import java.util.Scanner;

public class Game {
    public static final Scanner scanner = new Scanner(System.in);

    public static String getUserInput() {
        System.out.print("Введите букву: ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Введите одну букву");
            return null;
        }
        return input;
    }


    public static void printState(Hangman game) {
        System.out.println("\nСлово: " + game.printWord());
        System.out.println("Осталось попыток: " + game.getCurrentAttempts());
    }


    public static void showHint(String hint) {
        if (hint != null && !hint.trim().isEmpty()) {
            System.out.println("💡 Подсказка: " + hint);
        }
    }


    public static void printHead(String text) {
        int width = 100;
        String padded = String.format("%" + (width / 2 + text.length() / 2) + "s", text);
        System.out.println(padded);
    }


    public static void printCategories(String title, java.util.List<String> items) {
        System.out.println(title + ":");
        String formatted = String.join(" • ", items);
        System.out.println("  " + formatted);
    }


    public static void printResult(String word, boolean win) {
        if (win) {
            System.out.println("\nПоздравляем, вы угадали слово " + word + "!");
        } else {
            System.out.println("\nВы проиграли, загаданное слово было " + word);
        }
    }


    public static void close() {
        scanner.close();
    }
}