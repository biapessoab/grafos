import java.io.*;
import java.util.*;

public class DFS {
    static int t = 0; // global timer
    static Grafo graph; 
    static List<Vertice> vertices = new ArrayList<>();
    static List<Integer> uniqueOrder = new ArrayList<>();

    public DFS(Grafo graph) {
        this.graph = graph;
    }
    public static void main (String[] args) {

        // creates a graph structure
        graph = new Grafo();  
        
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

        // gets the chosen vertice
        System.out.print("Vértice escolhido: ");
        int chosenVertice = inpScanner.nextInt();
        
        // search(vertices.get(0));
    }

    // public static void search(Vertice v) {
    //     t = t++;
    //     v.setTd(t);

    //     for(int i = 0; i<graph.getOriginArray().length; i++) {
    //         if(i == Integer.valueOf(v.toString())) {
                
    //         }
    //     }
    // }

    public void order() {
        int[] originArray = graph.getOriginArray();

        int[] sortedArray = Arrays.copyOf(originArray, originArray.length);

        // ordering with quicksort
        quickSort(sortedArray, 1, sortedArray.length - 1);

        // add vertex only once

        int prevVertice = -1;
        for (int i = 1; i < sortedArray.length; i++) {
            int currentVertice = sortedArray[i];
            if (currentVertice != prevVertice) {
                uniqueOrder.add(currentVertice);
                prevVertice = currentVertice;
            }
        }

        criarVertices(uniqueOrder);
    }

    public static void quickSort(int array[], int start, int end) {
        if (start < end) {
            int pivot = partition(array, start, end);

            quickSort(array, start, pivot - 1);
            quickSort(array, pivot + 1, end);
        }
    }

    public static int partition(int array[], int start, int end) {
        int pivot = array[end];
        int i = (start - 1);

        for (int j = start; j < end; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }

    public void criarVertices(List<Integer> uniqueOrder) {
        for (int i = 1; i < uniqueOrder.size(); i++) {
            Vertice vertice = new Vertice();
            vertices.add(vertice);
        }

        for (Vertice v : vertices) {
            System.out.println(v.getFather());
        }
    }
}