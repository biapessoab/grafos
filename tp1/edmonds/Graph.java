import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {
    // gets file
    static File file;
    static int vertices;
    static int[] originArray;
    static int[] destinationArray;
    private int vertexCount;
    public static void main(String[] args) throws Exception {

        // checks the out degree
        int[] outDegrees = new int[vertices + 1];

        for (int i : originArray) {
            outDegrees[i]++;
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

        int count = 0;

        for (int edge : originArray) {
            if (edge == maxOutDegreeVertex) {
                successors.add(destinationArray[count]);
            }
            count++;
        }

        // checks the in degree
        int[] inDegrees = new int[vertices + 1];

        for (int i : destinationArray) {
            inDegrees[i]++;
        }

        int maxInDegreeVertex = 1;
        int maxInDegree = inDegrees[1];

        for (int i = 2; i <= vertices; i++) {
            if (inDegrees[i] > maxInDegree) {
                maxInDegreeVertex = i;
                maxInDegree = inDegrees[i];
            }
        }

        // gets the successors
        ArrayList<Integer> predecessors = new ArrayList<Integer>();

        int c = 1;
        for (int edge : destinationArray) {
            if (edge == maxInDegreeVertex) {
                predecessors.add(originArray[c]);
            }
            c++;
        }

        System.out.println("O vértice " + maxOutDegreeVertex + " tem o maior grau de saída (" + maxOutDegree + ")\nConjunto de sucessores: " + successors);
        System.out.println("O vértice " + maxInDegreeVertex + " tem o maior grau de entrada (" + maxInDegree + ")\nConjunto de predecessores: " + predecessors);

    }

    public static void read(File file) throws FileNotFoundException{
        // System.out.println("leu");

        Scanner sc = new Scanner(file);

        // gets total number of vertices and edges
        vertices = sc.nextInt();
        int edges = sc.nextInt();

        originArray = new int[edges+1];
        destinationArray = new int[edges+1];

        // creates the edges array
        int cont = 1;

        // reads the file and adds the vertices to the edges array
        while (sc.hasNext()) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            originArray[cont] = start;
            destinationArray[cont] = end;
            cont++;
        }

        // System.out.println("leuuuuuu");

        sc.close();
    }
    
    public static int[] getSortedDestinationArray() {
        int[] uniqueDestinations = new int[destinationArray.length];
        int uniqueCount = 0;
    
        for (int i = 1; i < originArray.length - 1; i++) {
            int inicio = originArray[i];
            int fim = originArray[i + 1];
    
            int[] subArray = Arrays.copyOfRange(destinationArray, inicio, fim);
            Arrays.sort(subArray);
    
            if (subArray.length > 0) {
                uniqueDestinations[uniqueCount] = subArray[0];
                uniqueCount++;
            }
        }
    
        int[] resultArray = Arrays.copyOf(uniqueDestinations, uniqueCount);
        return resultArray;
    }
    

     public void Graph(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}