import java.io.*; 
import java.util.*;

public class Threads8 {
    public static void main(String[] args) {

        String caminhoPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\projeto_threads\\temperaturas";
        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles(); // listFiles retorna os arquivos no diretório

        List<File> arquivosLista = Arrays.asList(arquivos);
        
        // Dividindo os arquivos em duas partes
        List<File> arquivosParte1 = arquivosLista.subList(0, 40);
        List<File> arquivosParte2 = arquivosLista.subList(40, 80);
        List<File> arquivosParte3 = arquivosLista.subList(80, 120);
        List<File> arquivosParte4 = arquivosLista.subList(120, 160);
        List<File> arquivosParte5 = arquivosLista.subList(160, 200);
        List<File> arquivosParte6 = arquivosLista.subList(200, 240);
        List<File> arquivosParte7 = arquivosLista.subList(240, 280);
        List<File> arquivosParte8 = arquivosLista.subList(280, 320);

        long startTime = System.currentTimeMillis(); // Captura o tempo inicial

        // Criando duas threads
        LeitorCsv thread1 = new LeitorCsv(arquivosParte1);
        LeitorCsv thread2 = new LeitorCsv(arquivosParte2);
        LeitorCsv thread3 = new LeitorCsv(arquivosParte3);
        LeitorCsv thread4 = new LeitorCsv(arquivosParte4);

        LeitorCsv thread5 = new LeitorCsv(arquivosParte5);
        LeitorCsv thread6 = new LeitorCsv(arquivosParte6);
        LeitorCsv thread7 = new LeitorCsv(arquivosParte7);
        LeitorCsv thread8 = new LeitorCsv(arquivosParte8);

        thread1.start();
        thread2.start(); 
        thread3.start();
        thread4.start();

        thread5.start();
        thread6.start(); 
        thread7.start();
        thread8.start();

        try {
            thread1.join(); 
            thread2.join(); 
            thread3.join();
            thread4.join();

            thread5.join(); 
            thread6.join(); 
            thread7.join();
            thread8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long duration = endTime - startTime; // Calcula a duração em milissegundos

        System.out.println("O tempo de execução foi: " + duration + " milissegundos");
    }
}


