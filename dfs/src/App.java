import java.io.File;
import java.util.Scanner;

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
        
    }
}