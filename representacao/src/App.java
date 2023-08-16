import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        // gets file
        File file;

        Scanner inpScanner = new Scanner(System.in);

        // gets the file
        System.out.println("Arquivo lido: (1: 100 vértices ou 2: 50000 vértices");
        int fileChoice = inpScanner.nextInt();

        if (fileChoice == 1) {
            file = new File("src/graph-test-100.txt");
        } else if (fileChoice == 2) {
            file = new File("src/graph-test-50000.txt");
        } else {
            System.out.println("Escolha inválida.");
            inpScanner.close();
            return;
        }

        Scanner sc = new Scanner(file);

        // gets total number of vertices and edges
        int vertices = sc.nextInt();
        int edges = sc.nextInt();

        // creates the edges array
        ArrayList<Aresta> edgesArray = new ArrayList<Aresta>();

        // reads the file and adds the vertices to the edges array
        while (sc.hasNext()) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            Aresta edge = new Aresta(start, end);

            edgesArray.add(edge);
        }

        // checks the out degree
        int[] outDegrees = new int[vertices + 1];

        for (Aresta edge : edgesArray) {
            outDegrees[edge.getStart()]++;
        }

        int maxOutDegreeVertex = 1;
        int maxOutDegree = outDegrees[1];

        for (int i = 2; i <= vertices; i++) {
            if (outDegrees[i] > maxOutDegree) {
                maxOutDegreeVertex = i;
                maxOutDegree = outDegrees[i];
            }
        }

        // gets the successors
        ArrayList<Integer> successors = new ArrayList<Integer>();

        for (Aresta edge : edgesArray) {
            if (edge.getStart() == maxOutDegreeVertex) {
                successors.add(edge.getEnd());
            }
        }

        // checks the in degree
        int[] inDegrees = new int[vertices + 1];

        for (Aresta edge : edgesArray) {
            inDegrees[edge.getEnd()]++;
        }

        int maxInDegreeVertex = 1;
        int maxInDegree = inDegrees[1];

        for (int i = 2; i <= vertices; i++) {
            if (inDegrees[i] > maxInDegree) {
                maxInDegreeVertex = i;
                maxInDegree = inDegrees[i];
            }
        }

        // gets the predecessors
        ArrayList<Integer> predecessor = new ArrayList<Integer>();

        for (Aresta edge : edgesArray) {
            if (edge.getEnd() == maxInDegreeVertex) {
                predecessor.add(edge.getStart());
            }
        }

        System.out.println("O vértice " + maxOutDegreeVertex + " tem o maior grau de saída (" + maxOutDegree + ")\nConjunto de sucessores: " + successors);
        System.out.println("O vértice " + maxInDegreeVertex + " tem o maior grau de entrada (" + maxInDegree + ")\nConjunto de sucessores: " + predecessor);

    }
}
