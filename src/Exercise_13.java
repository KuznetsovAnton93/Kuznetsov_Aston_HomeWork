public class Exercise_13 {
    public static void main(String[] args) {
        int size = 5; // Размер квадратного массива
        int[][] matrix = new int[size][size];

        // Заполняем главную диагональ единицами
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }

        // Выводим массив на консоль
        printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
