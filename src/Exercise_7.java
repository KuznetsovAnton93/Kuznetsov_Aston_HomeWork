import java.util.Scanner;

public class Exercise_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        boolean result = isNegative(number);
        System.out.println("Число отрицательное: " + result);
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }
}
