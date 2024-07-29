public class Exercise_14 {
    public static void main(String[] args) {
        int len = 5; // длина массива
        int initialValue = 7; // начальное значение

        int[] array = createArray(len, initialValue);

        // Выводим массив на консоль
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    public static int[] createArray(int len, int initialValue) {
        // Создаем массив длиной len
        int[] array = new int[len];

        // Заполняем массив значением initialValue
        for (int i = 0; i < array.length; i++) {
            array[i] = initialValue;
        }

        // Возвращаем заполненный массив
        return array;
    }
}
