import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class MaxFlow {
  int numVertices = 0;
  int sourceVertex = 0;
  int destinationVertex = 0;
  ArrayList<LinkedList<Integer>> adjacents;

  int maxFlow(ArrayList<LinkedList<Integer>> graph, int source, int destination) {
    int u, v;
    ArrayList<LinkedList<Integer>> residualGraph = new ArrayList<>(numVertices);

    for (u = 0; u < numVertices; u++) {
      residualGraph.add(new LinkedList<>());
      residualGraph.get(u).addAll(graph.get(u));
    }

    int parent[] = new int[numVertices];

    int maxFlow = 0;

    ArrayList<String> paths = new ArrayList<>();

    while (search(residualGraph, source, destination, parent)) {
      int pathFlow = Integer.MAX_VALUE;

      for (v = destination; v != source; v = parent[v]) {
        u = parent[v];
        int residualCapacity = residualGraph.get(u).indexOf(v);
        pathFlow = Math.min(pathFlow, residualCapacity);
      }

      for (v = destination; v != source; v = parent[v]) {
        u = parent[v];
        residualGraph.get(u).remove((Integer) v);
        residualGraph.get(v).add(u);
      }

      String path = "" + destination;

      for (v = destination; v != source; v = parent[v]) {
        u = parent[v];
        path += " - " + u;
      }
      paths.add(path);
      maxFlow += pathFlow;
    }

    System.out.println("Number of disjoint paths: " + maxFlow);
    System.out.println("Paths:");
    for (String currentPath : paths) {
      System.out.println(currentPath);
    }
    return maxFlow;
  }

  boolean search(ArrayList<LinkedList<Integer>> graph, int source, int destination, int parent[]) {

    boolean visited[] = new boolean[numVertices];
    java.util.Arrays.fill(visited, false);

    LinkedList<Integer> list = new LinkedList<>();
    list.add(source);
    visited[source] = true;
    parent[source] = Integer.MIN_VALUE;

    while (!list.isEmpty()) {
      int currentVertex = list.poll();

      for (int neighbor : graph.get(currentVertex)) {
        if (!visited[neighbor]) {
          list.add(neighbor);
          parent[neighbor] = currentVertex;
          visited[neighbor] = true;
        }
      }
    }
    return visited[destination];
  }

  public void readGraph(String filename) throws FileNotFoundException {

    Scanner scanner = new Scanner(new File(filename));

    int numVertices = scanner.nextInt();
    int numEdges = scanner.nextInt();
    this.numVertices = numVertices;
    adjacents = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjacents.add(new LinkedList<>());
    }

    sourceVertex = scanner.nextInt();
    destinationVertex = scanner.nextInt();

    while (scanner.hasNextInt()) {
      int from = scanner.nextInt();
      int to = scanner.nextInt();
      adjacents.get(from).add(to);
      adjacents.get(to).add(from);
    }
  }

  public static void main(String[] args) throws java.lang.Exception {
    MaxFlow maxFlow = new MaxFlow();

    long start = System.currentTimeMillis();

    maxFlow.readGraph("CompleteGraph10.txt");
    maxFlow.maxFlow(maxFlow.adjacents, maxFlow.sourceVertex, maxFlow.destinationVertex);

    long end = System.currentTimeMillis();
    long executionTime = end - start;

    System.out.println("Time spent: " + executionTime + " mills");
  }
}
