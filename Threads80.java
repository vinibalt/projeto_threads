import java.io.*; 
import java.util.*;

public class Threads80 {
    public static void main(String[] args) {

        String caminhoPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\projeto_threads\\temperaturas";
        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles(); // listFiles retorna os arquivos no diretório

        List<File> arquivosLista = Arrays.asList(arquivos);
        
        int totalThreads = 80; // Defina o número de threads que você deseja
        int arquivosPorThread = arquivosLista.size() / totalThreads;

        List<LeitorCsv> threads = new ArrayList<>(); // Lista para armazenar as threads

        // Dividindo os arquivos entre as threads dinamicamente
        for (int i = 0; i < totalThreads; i++) {
            int inicio = i * arquivosPorThread;
            int fim = (i == totalThreads - 1) ? arquivosLista.size() : inicio + arquivosPorThread; // Tratar o final

            List<File> arquivosParte = arquivosLista.subList(inicio, fim);
            LeitorCsv thread = new LeitorCsv(arquivosParte);
            threads.add(thread);
        }

        long startTime = System.currentTimeMillis(); // Captura o tempo inicial

        // Iniciando as threads
        for (LeitorCsv thread : threads) {
            thread.start();
        }

        // Aguardando todas as threads terminarem
        for (LeitorCsv thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long duration = endTime - startTime; // Calcula a duração em milissegundos

        System.out.println("O tempo de execução foi: " + duration + " milissegundos");
    }
}
