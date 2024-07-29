import java.util.Arrays;

public class Exercise_11 {
    public static void main(String[] args) {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        System.out.println("Заполненный массив: " + Arrays.toString(array));
    }
}
