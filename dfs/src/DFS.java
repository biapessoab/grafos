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

    public static void search(int startIndex, int chosen) {
        t++;
        vertices.get(startIndex).setTd(t);
    
        for (int i = 0; i < graph.getOriginArray().length; i++) {
            if (graph.getOriginArray()[i] == startIndex) {
                int destination = graph.getDestinationArray()[i];
    
                if (vertices.get(destination).getTd() == 0) {
                    // tree edge
                    System.out.println("Aresta de árvore (" + startIndex + ", " + destination + ")");
                    search(destination, chosen);
                } 
                if (startIndex == chosen) {
                    // all types of edges
                    if (vertices.get(destination).getTt() == 0) {
                        System.out.println("Aresta de retorno (" + startIndex + ", " + destination + ")");
                    } else if (vertices.get(startIndex).getTd() < vertices.get(destination).getTd()) {
                        System.out.println("Aresta de avanço (" + startIndex + ", " + destination + ")");
                    } else {
                        System.out.println("Aresta de cruzamento (" + startIndex + ", " + destination + ")");
                    }
                }
            }
        }
    
        t++;
        vertices.get(startIndex).setTt(t);
    }
    

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
    }
}