import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String caminhoPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\projeto_threads\\temperaturas";
        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles(); //listFiles retorna os arquivos no diretório

        long startTime = System.currentTimeMillis(); // Captura o tempo inicial

        for(File arquivo:arquivos) {
            if(arquivo.getName().endsWith(".csv")) {
                LeitorCsv leitor = new LeitorCsv(arquivo);
                leitor.leitura();
            }
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long duration = endTime - startTime; // Calcula a duração em milissegundos

        System.out.println("O tempo de execução foi: " + duration + " milissegundos");
    }
}
