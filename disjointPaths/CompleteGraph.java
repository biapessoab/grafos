import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompleteGraph {
    private List<List<Integer>> adjacencies;

    public CompleteGraph(int numVertices) {
        adjacencies = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            List<Integer> neighbors = new ArrayList<>(numVertices);
            adjacencies.add(neighbors);
        }
    
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) { 
                if (i < j) {
                    adjacencies.get(i).add(j);
                    adjacencies.get(j).add(i); 
                }
            }
        }
    }
    
    public void showGraph() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("CompleteGraph" + adjacencies.size() + ".txt"))) {
            int numVertices = adjacencies.size();
            int numEdges = getTotalEdges();

            writer.println(numVertices + " " + numEdges);

            int source = 0, destination = numVertices - 1;
            writer.println(source + " " + destination);

            for (int i = 0; i < adjacencies.size(); i++) {
                for (int j : adjacencies.get(i)) {
                    writer.println(i + " " + j);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getTotalEdges() {
        int totalEdges = 0;
        for (List<Integer> neighbors : adjacencies) {
            totalEdges += neighbors.size();
        }
        return totalEdges / 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices in the graph: ");
        int numVertices = sc.nextInt();

        CompleteGraph graph = new CompleteGraph(numVertices);

        graph.showGraph();
    }
}
