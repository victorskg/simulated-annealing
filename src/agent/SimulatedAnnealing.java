package agent;

import java.util.Random;

import static java.lang.Math.abs;

public class SimulatedAnnealing {

    public int[] execute(int[] initialTable, float alpha, int maxIter) {
        var currentSolution = initialTable;
        var bestSolution = currentSolution;
        var currentTemperature = checkTemperature(currentSolution);
        var iter = 0;

        while (!isObjective(currentSolution)) {
            while (iter < maxIter) {
                iter++;
                currentSolution = disturb(currentSolution);
                var delta = checkTemperature(currentSolution) - checkTemperature(bestSolution);
                if (delta < 0) bestSolution = currentSolution;
                else {
                    var beta = new Random().nextFloat();
                    if (beta < Math.exp(delta / currentTemperature)) bestSolution = currentSolution;
                }
            }
            iter = 0;
            currentTemperature = currentTemperature * alpha;
        }

        return bestSolution;
    }

    public int[] initializeTableConfiguration(int[] initialState) {
        for (var i = 0; i < initialState.length; i++) {
            initialState[i] = i + 1;
        }

        return initialState;
    }

    private int[] disturb(int[] tableConfiguration) {
        var random = new Random();
        var firstQueen = random.nextInt(8);
        var secondQueen = random.nextInt(8);

        while (firstQueen == secondQueen) {
            secondQueen = random.nextInt(8);
        }

        var aux = tableConfiguration[firstQueen];
        tableConfiguration[firstQueen] = tableConfiguration[secondQueen];
        tableConfiguration[secondQueen] = aux;

        return tableConfiguration;
    }

    private float checkTemperature(int[] tableConfiguration) {
        var temperature = 0;
        for (var i = 0; i < 7; i++) {
            for (var j = i + 1; j < 8; j++) {
                if (abs(i - j) == abs(tableConfiguration[i] - tableConfiguration[j])) {
                    temperature++;
                }
            }
        }

        return temperature;
    }

    private boolean isObjective(int[] tableConfiguration) {
        return checkTemperature(tableConfiguration) == 0;
    }
}
