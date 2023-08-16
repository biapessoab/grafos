import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

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

        // gets the first line: number of vertices and edges
        int vertices = sc.nextInt();
        int edges = sc.nextInt();

        // creates the origin and destination arrays
        int[] originArray = new int[edges + 1];
        int[] destinationArray = new int[edges + 1];

        int index = 1;

        // adds each origin and destination to the arrays
        while (sc.hasNext()) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            originArray[index] = start;
            destinationArray[index] = end;

            index++;
        }

        int[] outDegree = new int[vertices + 1];

        for (int i = 1; i <= edges; i++) {
            outDegree[originArray[i]]++;
        }

        int maxOutDegreeVertex = 1;
        int maxOutDegree = outDegree[1];

        // checks whats the max degree vertex
        for (int i = 2; i <= vertices; i++) {
            if (outDegree[i] > maxOutDegree) {
                maxOutDegree = outDegree[i];
                maxOutDegreeVertex = i;
            }
        }

        int[] successors = new int[edges + 1];
        int cont = 1;

        // knowing the max degree vertex, checks the successors
        for (int i = 1; i <= edges; i++) {
            if (originArray[i] == maxOutDegreeVertex) {
                successors[cont] = destinationArray[i];
                cont++;
            }
        }

        // sysout
        System.out.println("O vértice com o maior grau de saída é o vértice " + maxOutDegreeVertex + " com grau " + maxOutDegree);
        System.out.println("Sucessores: ");
        for (int i = 1; i < cont; i++) {
            System.out.print(successors[i] + " ");
        }

        sc.close();
        inpScanner.close();
    }
}
