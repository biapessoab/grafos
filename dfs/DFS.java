import java.io.*;
import java.util.*;

public class DFS {
    static int t = 0;
    static int chosenVertice = 0;
    static Graph graph;
    static Vertice[] vertex;

    public static void main(String[] Args) throws FileNotFoundException {
        Graph graph = new Graph();

        File file;

        try (Scanner sc = new Scanner(System.in)) {
            // gets the file
            System.out.println("Arquivo lido: (1: 100 vértices ou 2: 50000 vértices)");
            int fileChoice = sc.nextInt();

            if (fileChoice == 1) {
                file = new File("./graph-test-100.txt");
            } else if (fileChoice == 2) {
                file = new File("./graph-test-50000.txt");
            } else {
                System.out.println("Escolha inválida.");
                sc.close();
                return;
            }

            graph.read(file);

            System.out.print("Escolha um vértice: ");
            chosenVertice = sc.nextInt();
        }

        // int[] sortedArray = graph.getSortedDestinationArray();

        // for (int i : sortedDestinationArray) {
        // System.out.println(i);
        // }

        vertex = new Vertice[graph.originArray.length];
        for (int i = 1; i < graph.originArray.length; i++) {
            vertex[i] = new Vertice();
            // System.out.println("vertice " + vertex[i].getTt());
        }

        // is there any vertice that wasnt visited?
        int currentVertice = isMissing(vertex);
        while (currentVertice != -1) {
            dfs(currentVertice);
            currentVertice = isMissing(vertex);
        }

    }

    public static int isMissing(Vertice[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            // checks if the vertex wasnt visitedd
            if (array[i].getTd() == 0) {
                return i; 
            }
        }
        return -1;
    }

    // dfs method
    public static void dfs(int v) {
        // System.out.println("dfs do " + v);
        t++; // increases the global timer
        vertex[v].setTd(t); // sets td

        for (int i = 0; i < graph.originArray.length; i++) {

            if (v == graph.originArray[i]) {

                int w = graph.destinationArray[i];

                // aresta de árvore
                if (vertex[w].getTd() == 0) { // if td = 0
                    System.out.println("Aresta de árvore: " + v + " - " + w);

                    vertex[w].setFather(v); // sets the initial vertice as the father of the discovered one
                    dfs(w); // restart dfs with the new vertice
                } else {
                    if (vertex[w].getTt() == 0) { // if tt = 0
                        // aresta de retorno
                        if (v == chosenVertice) { // if the current vertice is the chosen one
                            System.out.println("DIVERGENTE: Aresta de retorno: " + v + " - " + w);
                        }
                    } else if (vertex[v].getTd() < vertex[w].getTd()) {
                        // aresta de avanço
                        if (v == chosenVertice) { // if the current vertice is the chosen one
                            System.out.println("DIVERGENTE: Aresta de avanço: " + v + " - " + w);
                        }
                    } else {
                        // aresta de cruzamento
                        if (v == chosenVertice) { // if the current vertice is the chosen one
                            System.out.println("DIVERGENTE: Aresta de cruzamento: " + v + " - " + w);
                        }
                    }
                }

            }
        }
        t++; // increases timer
        vertex[v].setTt(t); // sets tt
    }
}