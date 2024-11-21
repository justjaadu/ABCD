import java.util.Scanner;
import java.util.Arrays;

public class TSPCrossover {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the TSP matrix (n*n): ");
        int n = scanner.nextInt();

        int[][] tspMatrix = new int[n][n];
        System.out.println("Enter the TSP matrix values (-1 for infinity):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tspMatrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the first parent chromosome (comma-separated, 0-indexed): ");
        int[] parent1 = Arrays.stream(scanner.next().split(","))
                              .mapToInt(Integer::parseInt)
                              .toArray();

        System.out.println("Enter the second parent chromosome (comma-separated, 0-indexed): ");
        int[] parent2 = Arrays.stream(scanner.next().split(","))
                              .mapToInt(Integer::parseInt)
                              .toArray();

        int[] child1 = new int[n];
        int[] child2 = new int[n];
        crossover(parent1, parent2, child1, child2);

        int distance1 = calculateTotalDistance(tspMatrix, child1);
        int distance2 = calculateTotalDistance(tspMatrix, child2);

        System.out.println("Child Chromosome 1: " + Arrays.toString(child1));
        System.out.println("Child Chromosome 1 Distance: " + distance1);
        System.out.println("Child Chromosome 2: " + Arrays.toString(child2));
        System.out.println("Child Chromosome 2 Distance: " + distance2);

        if (distance1 < distance2) {
            System.out.println("Optimal Path: " + Arrays.toString(child1) + " with distance: " + distance1);
        } else {
            System.out.println("Optimal Path: " + Arrays.toString(child2) + " with distance: " + distance2);
        }
    }

    public static void crossover(int[] parent1, int[] parent2, int[] child1, int[] child2) {
        int n = parent1.length;
        int start = (int) (Math.random() * n);
        int end = (int) (Math.random() * n);

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        Arrays.fill(child1, -1);
        Arrays.fill(child2, -1);

        for (int i = start; i <= end; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
        }

        for (int i = start; i <= end; i++) {
            fixCrossover(child1, parent2, i, start, end);
            fixCrossover(child2, parent1, i, start, end);
        }

        fillRemaining(child1, parent2, start, end);
        fillRemaining(child2, parent1, start, end);
    }

    private static void fixCrossover(int[] child, int[] parent, int index, int start, int end) {
        int value = parent[index];
        while (contains(child, value, start, end)) {
            value = parent[getIndexOf(parent, value)];
        }
        child[index] = value;
    }

    private static void fillRemaining(int[] child, int[] parent, int start, int end) {
        int n = child.length;
        for (int i = 0; i < n; i++) {
            if (child[i] == -1) {
                child[i] = parent[i];
            }
        }
    }

    private static boolean contains(int[] array, int value, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    private static int getIndexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int calculateTotalDistance(int[][] tspMatrix, int[] path) {
        int totalDistance = 0;
        for (int i = 0; i < path.length - 1; i++) {
            int from = path[i];
            int to = path[i + 1];
            if (tspMatrix[from][to] == -1) {
                return Integer.MAX_VALUE;
            }
            totalDistance += tspMatrix[from][to];
        }
        totalDistance += tspMatrix[path[path.length - 1]][path[0]];
        return totalDistance;
    }
}
