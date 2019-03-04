package execution;

import agent.SimulatedAnnealing;

public class Execution {
    public static void main(String[] args) {
        var sa = new SimulatedAnnealing();
        var initialState = new int[8];
        int[] otimo = {1, 5, 8, 6, 3, 7, 2, 4};
        initialState = sa.initializeTableConfiguration(initialState);

        print(sa.execute(initialState, Float.valueOf("0.9"), 5));
    }

    public static void print(int[] matrix) {
        System.out.print("\t");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + 1 + "\t");
        }
        System.out.println();
        System.out.print("\t");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("-\t");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + 1 + " |\t");
            for (int j = 1; j <= matrix.length; j++) {
                if (matrix[i] == j) {
                    System.out.print("X\t");
                } else {
                    System.out.print("0\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.print("Vetor solução: ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + "\t");
        }
    }
}
