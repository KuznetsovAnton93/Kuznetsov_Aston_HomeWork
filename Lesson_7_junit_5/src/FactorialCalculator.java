public class FactorialCalculator {

    // Метод для вычисления факториала числа
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        // Пример использования метода factorial
        int number = 5;
        System.out.println("Factorial of " + number + " is: " + factorial(number));
    }
}