import java.io.*; 
import java.util.*;

public class Threads2 {
    public static void main(String[] args) {

        String caminhoPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\projeto_threads\\temperaturas";
        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles(); // listFiles retorna os arquivos no diretório

        List<File> arquivosLista = Arrays.asList(arquivos);
        
        // Dividindo os arquivos em duas partes
        List<File> arquivosParte1 = arquivosLista.subList(0, 160);
        List<File> arquivosParte2 = arquivosLista.subList(160, 320);

        long startTime = System.currentTimeMillis(); // Captura o tempo inicial

        // Criando duas threads
        LeitorCsv thread1 = new LeitorCsv(arquivosParte1);
        LeitorCsv thread2 = new LeitorCsv(arquivosParte2);

        thread1.start(); // Inicia a primeira thread
        thread2.start(); // Inicia a segunda thread

        try {
            thread1.join(); // Espera a thread1 terminar
            thread2.join(); // Espera a thread2 terminar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long duration = endTime - startTime; // Calcula a duração em milissegundos

        System.out.println("O tempo de execução foi: " + duration + " milissegundos");
    }
}
