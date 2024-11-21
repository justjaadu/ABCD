import java.util.*;

public class TSPOptimalPath {

    public static int calculatePathDistance(int[][] tspMatrix, List<Integer> chromosome) {
        int totalDistance = 0;
        int n = chromosome.size();

        for (int i = 0; i < n - 1; i++) {
            int from = chromosome.get(i);
            int to = chromosome.get(i + 1);
            int distance = tspMatrix[from][to];

            if (distance == -1) return Integer.MAX_VALUE;
            totalDistance += distance;
        }

        int lastCity = chromosome.get(n - 1);
        int firstCity = chromosome.get(0);
        int returnDistance = tspMatrix[lastCity][firstCity];
        if (returnDistance == -1) return Integer.MAX_VALUE;
        totalDistance += returnDistance;

        return totalDistance;
    }

    public static List<Integer> mutateChromosome(List<Integer> chromosome) {
        Random random = new Random();
        int n = chromosome.size();

        int idx1 = random.nextInt(n);
        int idx2 = random.nextInt(n);

        while (idx1 == idx2) {
            idx2 = random.nextInt(n);
        }

        Collections.swap(chromosome, idx1, idx2);
        return chromosome;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities (n): ");
        int n = scanner.nextInt();

        int[][] tspMatrix = new int[n][n];
        System.out.println("Enter the TSP matrix (-1 for infinity):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tspMatrix[i][j] = scanner.nextInt();
            }
        }

        List<Integer> currentChromosome = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            currentChromosome.add(i);
        }

        List<Integer> bestChromosome = new ArrayList<>(currentChromosome);
        int bestDistance = calculatePathDistance(tspMatrix, bestChromosome);

        System.out.println("Initial Path: " + bestChromosome);
        System.out.println("Initial Distance: " + bestDistance);

        Random random = new Random();
        int iterations = 10000;

        for (int i = 0; i < iterations; i++) {
            List<Integer> mutatedChromosome = new ArrayList<>(currentChromosome);
            mutateChromosome(mutatedChromosome);

            int mutatedDistance = calculatePathDistance(tspMatrix, mutatedChromosome);

            if (mutatedDistance < bestDistance) {
                bestChromosome = new ArrayList<>(mutatedChromosome);
                bestDistance = mutatedDistance;
            }

            if (mutatedDistance > bestDistance && random.nextDouble() < 0.01) {
                bestChromosome = new ArrayList<>(mutatedChromosome);
                bestDistance = mutatedDistance;
            }
        }

        System.out.println("Optimal Path: " + bestChromosome);
        System.out.println("Optimal Distance: " + bestDistance);
    }
}
