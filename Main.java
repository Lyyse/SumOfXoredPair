import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // int[] array1 = {2, 4, 5, 7, 10};
        // int[] array2 = {13, 14, 15, 16, 17};

        Scanner reader = new Scanner(System.in);

        int array_length;

        System.out.print("What is the size of the array?: ");

        array_length = Integer.parseInt(reader.nextLine());

        int[] array1 = new int[array_length];
        int[] array2 = new int[array_length];

        for (int i = 0; i < array1.length; i++) {
            System.out.printf("\nFor the first array #%d: ", i + 1);
            array1[i] = Integer.parseInt(reader.nextLine());
        }

        for (int i = 0; i < array1.length; i++) {
            System.out.printf("\nFor the second array #%d: ", i + 1);
            array2[i] = Integer.parseInt(reader.nextLine());
        }

        int[][] matrix = new int[array_length][array_length];

        int hold;
        int max_value_sofar = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                hold = array1[i] ^ array2[j];
                matrix[j][i] = hold;
                if (hold > max_value_sofar) max_value_sofar = hold;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = max_value_sofar - matrix[i][j];
            }
        }

        // System.out.println(arrayToStringMatrixStyle(matrix));

        HungarianAlgorithm solver = new HungarianAlgorithm(matrix);

        int[][] pairs = solver.findOptimalAssignment();

        int sumOfXors = 0;

        for (int[] pair : pairs) {
            // System.out.printf("(%d, %d), ", array1[pair[0]], array2[pair[1]]);
            sumOfXors += array1[pair[0]] ^ array2[pair[1]];
        }

        for (int i = 0; i < pairs.length - 1; i++) {
            System.out.printf("(%d, %d), ", array1[pairs[i][0]], array2[pairs[i][1]]);
        }
        System.out.printf("(%d, %d)", array1[pairs[pairs.length - 1][0]], array2[pairs[pairs.length - 1][1]]);

        System.out.println("\n\nSum: " + sumOfXors);

        reader.close();
    }
}
