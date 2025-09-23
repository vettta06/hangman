package database;

import java.util.*;

public class Loader {
    private static final Random random = new Random();

    public static class WordWithHint {
        public final String word;
        public final String hint;

        public WordWithHint(String word, String hint) {
            this.word = word;
            this.hint = hint;
        }
    }

    public static WordWithHint getRandomWord(String category, String difficulty) {
        Map<String, Map<String, List<String>>> categories = WordBase.getWordsByCategory();
        Map<String, String> hints = WordBase.getHints();

        if (category == null || category.trim().isEmpty()) {
            category = getRandomKey(categories.keySet());
        }

        if (difficulty == null || difficulty.trim().isEmpty()) {
            Map<String, List<String>> categoryMap = categories.get(category);
            if (categoryMap == null) {
                throw new IllegalArgumentException("Категория не найдена: " + category);
            }
            difficulty = getRandomKey(categoryMap.keySet());
        }

        List<String> words = categories.get(category).get(difficulty);
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Нет слов для категории '" + category + "', сложности '" + difficulty + "'");
        }

        String word = words.get(random.nextInt(words.size()));
        String hint = hints.getOrDefault(word, "");

        return new WordWithHint(word, hint);
    }

    private static <T> T getRandomKey(Collection<T> collection) {
        List<T> list = new ArrayList<>(collection);
        return list.get(new Random().nextInt(list.size()));
    }
}