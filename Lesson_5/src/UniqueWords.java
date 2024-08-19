import java.util.*;

public class UniqueWords {
    public static void main(String[] args) {
        // Создаем массив с набором слов
        String[] words = {
                "apple", "orange", "banana", "apple", "lemon",
                "grape", "apple", "orange", "banana", "peach",
                "grape", "melon", "melon", "banana", "apple",
                "lemon", "banana", "orange", "lemon", "peach"
        };

        // Используем HashSet для хранения уникальных слов
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        // Выводим список уникальных слов
        System.out.println("Уникальные слова: " + uniqueWords);

        // Используем HashMap для подсчета количества вхождений каждого слова
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Выводим количество вхождений для каждого слова
        System.out.println("Количество вхождений каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}