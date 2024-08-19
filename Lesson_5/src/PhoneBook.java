import java.util.*;

public class PhoneBook {
    // Используем HashMap для хранения фамилий и соответствующих номеров телефонов
    private Map<String, List<String>> phoneBook;

    // Конструктор инициализирует телефонный справочник
    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    // Метод для добавления записи в телефонный справочник
    public void add(String lastName, String phoneNumber) {
        // Если фамилия уже есть в справочнике, добавляем номер телефона в список
        if (phoneBook.containsKey(lastName)) {
            phoneBook.get(lastName).add(phoneNumber);
        } else {
            // Если фамилии нет, создаем новую запись с фамилией и номером телефона
            List<String> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(phoneNumber);
            phoneBook.put(lastName, phoneNumbers);
        }
    }

    // Метод для получения списка номеров по фамилии
    public List<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, new ArrayList<>());
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Добавляем записи в телефонный справочник
        phoneBook.add("Ivanov", "123456");
        phoneBook.add("Petrov", "234567");
        phoneBook.add("Ivanov", "345678");
        phoneBook.add("Sidorov", "456789");
        phoneBook.add("Ivanov", "567890");

        // Ищем и выводим номера телефонов по фамилии
        System.out.println("Ivanov: " + phoneBook.get("Ivanov"));
        System.out.println("Petrov: " + phoneBook.get("Petrov"));
        System.out.println("Sidorov: " + phoneBook.get("Sidorov"));
        System.out.println("Smirnov: " + phoneBook.get("Smirnov")); // Фамилия, которой нет в справочнике
    }
}