
import java.io.*; 
import java.util.*;

public class Threads4 {
    public static void main(String[] args) {

        String caminhoPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\projeto_threads\\temperaturas";
        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles(); // listFiles retorna os arquivos no diretório

        List<File> arquivosLista = Arrays.asList(arquivos);
        
        // Dividindo os arquivos em duas partes
        List<File> arquivosParte1 = arquivosLista.subList(0, 80);
        List<File> arquivosParte2 = arquivosLista.subList(80, 160);
        List<File> arquivosParte3 = arquivosLista.subList(160, 240);
        List<File> arquivosParte4 = arquivosLista.subList(240, 320);

        long startTime = System.currentTimeMillis(); // Captura o tempo inicial

        // Criando duas threads
        LeitorCsv thread1 = new LeitorCsv(arquivosParte1);
        LeitorCsv thread2 = new LeitorCsv(arquivosParte2);
        LeitorCsv thread3 = new LeitorCsv(arquivosParte3);
        LeitorCsv thread4 = new LeitorCsv(arquivosParte4);

        thread1.start(); // Inicia a primeira thread
        thread2.start(); // Inicia a segunda thread
        thread3.start();
        thread4.start();

        try {
            thread1.join(); // Espera a thread1 terminar
            thread2.join(); // Espera a thread2 terminar
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long duration = endTime - startTime; // Calcula a duração em milissegundos

        System.out.println("O tempo de execução foi: " + duration + " milissegundos");
    }
}

