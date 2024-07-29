import java.util.Arrays;

public class Exercise_10 {
    public static void main(String[] args) {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        System.out.println("Исходный массив: " + Arrays.toString(array));

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 0 ? 1 : 0;
        }

        System.out.println("Измененный массив: " + Arrays.toString(array));
    }
}
