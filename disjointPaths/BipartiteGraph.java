import java.io.*;
import java.util.*;

public class BipartiteGraph {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of vertex: ");
        int numVertices = scanner.nextInt();

        List<List<Integer>> graph = generateRandomBipartiteGraph(numVertices);

        // for (int i = 0; i < numVertices; i++) {
            // System.out.print("Vertex " + i + ": ");
            // for (int neighbor : graph.get(i)) {
            //     System.out.print(neighbor + " ");
            // }
            // System.out.println();
        // }

        String fileName = "BipartiteGraph" + numVertices + ".txt";
        int numEdges = numVertices * 2;

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            printWriter.println(numVertices + 1 + " " + numEdges);
            int source = 0, destination = numVertices - 1;
            printWriter.println(source + " " + destination);

            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < graph.get(i).size(); j++) {
                    printWriter.println("       " + i + "      " + graph.get(i).get(j));
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        scanner.close();
    }

    public static List<List<Integer>> generateRandomBipartiteGraph(int numVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        Random random = new Random();

        int half = numVertices / 2;
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < half; i++) {
            for (int j = half; j < numVertices; j++) {
                if (random.nextBoolean()) {
                    graph.get(i).add(j);
                } else {
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }
}
