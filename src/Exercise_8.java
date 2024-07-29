import java.util.Scanner;

public class Exercise_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку: ");
        String inputString = scanner.nextLine();

        System.out.print("Введите число: ");
        int count = scanner.nextInt();

        printStringMultipleText(inputString, count);
    }

    public static void printStringMultipleText(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }
}
