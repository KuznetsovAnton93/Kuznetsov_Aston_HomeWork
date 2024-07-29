import java.util.Scanner;

public class Exercise_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int a = scanner.nextInt();

        System.out.print("Введите второе число: ");
        int b = scanner.nextInt();

        boolean result = SumInRange(a, b);
        System.out.println("Сумма чисел " + a + " и " + b + " лежит в пределах от 10 до 20 (включительно)?: " + result);
    }

    public static boolean SumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }
}
