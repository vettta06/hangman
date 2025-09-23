package database;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

public class WordBase {
    private static final String PROPERTIES_FILE = "/words_data.properties";
    private static Properties properties = new Properties();

    static {
        try (InputStream input = WordBase.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("Не найден файл: " + PROPERTIES_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки слов: " + e.getMessage(), e);
        }
    }

    public static Map<String, List<String>> getWordsByCategory() {
        Map<String, List<String>> categories = new HashMap<>();
        Pattern categoryPattern = Pattern.compile("category\\.(\\w+)\\.(\\w+)");

        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("category.")) {
                java.util.regex.Matcher matcher = categoryPattern.matcher(key);
                if (matcher.matches()) {
                    String category = matcher.group(1);
                    String difficulty = matcher.group(2);
                    String[] words = properties.getProperty(key).split(",");
                    List<String> wordList = Arrays.asList(words);

                    categories.computeIfAbsent(category, k -> new HashMap<>())
                            .computeIfAbsent(difficulty, d -> new ArrayList<>())
                            .addAll(wordList);
                }
            }
        }
        return categories;
    }

    public static Map<String, String> getHints() {
        Map<String, String> hints = new HashMap<>();
        Pattern hintPattern = Pattern.compile("hint\\.(.+)");
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("hint.")) {
                String word = key.substring(5);
                hints.put(word, properties.getProperty(key));
            }
        }
        return hints;
    }
}