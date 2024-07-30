public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Petrov Petr", "Manager", "petrov@mailbox.com", "892312313", 35000, 35);
        employees[2] = new Employee("Sidorov Sidr", "Director", "sidorov@mailbox.com", "892312314", 40000, 40);
        employees[3] = new Employee("Alexeev Alex", "Developer", "alexeev@mailbox.com", "892312315", 45000, 28);
        employees[4] = new Employee("Sergeev Serg", "Analyst", "sergeev@mailbox.com", "892312316", 50000, 32);

        // Вывод информации о каждом сотруднике
        for (Employee employee : employees) {
            employee.printInfo();
            System.out.println();
        }
    }
}