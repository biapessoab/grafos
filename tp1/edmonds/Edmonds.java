import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Edmonds {

    static Graph graph;
    static int t = 0;
    static int chosenVertice = 0;
    static Vertice[] vertex;
    static Edmonds ed;
    static ArrayList<int[]> allEdges = new ArrayList<>();
    static ArrayList<int[]> backEdges = new ArrayList<>();
    static int[] sortedArray;

    public static void main(String[] args) throws FileNotFoundException {

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

            System.out.print("Escolha a raiz: ");
            chosenVertice = sc.nextInt();
        }

        vertex = new Vertice[graph.originArray.length];
        for (int i = 1; i < graph.originArray.length; i++) {
            vertex[i] = new Vertice();
            // System.out.println("vertice " + vertex[i].getTt());
        }

        int currentVertice = 1;
        while (currentVertice != 0) {
            dfs(currentVertice);
            currentVertice = isLeft(vertex);
        }

        sortedArray = graph.getSortedDestinationArray();

        printVerticesNotRoot(chosenVertice);
    }

    // is there any vertice that wasnt visited?
    public static int isLeft(Vertice[] array) {
        boolean achou = false;
        int vertice = 0;
        for (int i = 1; i < array.length - 1 && !achou; i++) {
            // testa se o tempo de descoberta do vertice e 0
            if (array[i].getTd() == 0) {
                // Se encontrou, retorna o vertice e ablica a busca nele
                achou = true;
                vertice = i;
            }
        }
        return vertice;
    }

    // dfs method
    public static void dfs(int v) {
        // System.out.println("comecei a dfs do " + v);
        t++; // increases the global timer
        vertex[v].setTd(t); // sets td

        for (int i = 0; i < graph.originArray.length; i++) {

            if (v == graph.originArray[i]) {

                int w = graph.destinationArray[i];
                int[] edge = { v, w };
                allEdges.add(edge);

                // aresta de árvore
                if (vertex[w].getTd() == 0) { // if td = 0
                    // System.out.println("Aresta de árvore: " + v + " - " + w);

                    vertex[w].setFather(v); // sets the initial vertice as the father of the discovered one
                    dfs(w); // restart dfs with the new vertice
                } else {
                    if (vertex[w].getTt() == 0) { // if tt = 0
                        int[] backEdge = { v, w };
                        allEdges.add(backEdge);
                        // System.out.println("DIVERGENTE: Aresta de retorno: " + v + " - " + w);
                    } else if (vertex[v].getTd() < vertex[w].getTd()) {
                        // aresta de avanço
                        // System.out.println("DIVERGENTE: Aresta de avanço: " + v + " - " + w);
                    } else {
                        // aresta de cruzamento
                        // System.out.println("DIVERGENTE: Aresta de cruzamento: " + v + " - " + w);
                    }
                }

            }
        }
        t++;
        vertex[v].setTt(t);
    }

    public static void printVerticesNotRoot(int root) {
        for (int i = 0; i < sortedArray.length + 1; i++) {
            if (i != root) {
                for (int j = 0; j < graph.destinationArray.length; j++) {
                    if (graph.destinationArray[j] == i) {
                        int sourceVertex = graph.originArray[j];
                        int destinationVertex = graph.destinationArray[j];
                        System.out.println("Aresta: " + sourceVertex + " - " + destinationVertex);
                        

                        // is there a cycle?
                    }
                }
            }
        }
    }
    
    
    
}
