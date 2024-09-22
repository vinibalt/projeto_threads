import java.io.*; 
import java.util.*;

public class Threads16 {
    public static void main(String[] args) {

        String caminhoPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\projeto_threads\\temperaturas";
        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles(); // listFiles retorna os arquivos no diretório

        List<File> arquivosLista = Arrays.asList(arquivos);
        
        // Dividindo os arquivos em duas partes
        List<File> arquivosParte1 = arquivosLista.subList(0, 20);
        List<File> arquivosParte2 = arquivosLista.subList(20, 40);
        List<File> arquivosParte3 = arquivosLista.subList(40, 60);
        List<File> arquivosParte4 = arquivosLista.subList(60, 80);
        List<File> arquivosParte5 = arquivosLista.subList(80, 100);
        List<File> arquivosParte6 = arquivosLista.subList(100, 120);
        List<File> arquivosParte7 = arquivosLista.subList(120, 140);
        List<File> arquivosParte8 = arquivosLista.subList(140, 160);

        List<File> arquivosParte9 = arquivosLista.subList(160, 180);
        List<File> arquivosParte10 = arquivosLista.subList(180, 200);
        List<File> arquivosParte11 = arquivosLista.subList(200, 220);
        List<File> arquivosParte12 = arquivosLista.subList(220, 240);
        List<File> arquivosParte13 = arquivosLista.subList(240, 260);
        List<File> arquivosParte14 = arquivosLista.subList(260, 280);
        List<File> arquivosParte15 = arquivosLista.subList(280, 300);
        List<File> arquivosParte16 = arquivosLista.subList(300, 320);

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

        LeitorCsv thread9 = new LeitorCsv(arquivosParte9);
        LeitorCsv thread10 = new LeitorCsv(arquivosParte10);
        LeitorCsv thread11 = new LeitorCsv(arquivosParte11);
        LeitorCsv thread12 = new LeitorCsv(arquivosParte12);

        LeitorCsv thread13 = new LeitorCsv(arquivosParte13);
        LeitorCsv thread14 = new LeitorCsv(arquivosParte14);
        LeitorCsv thread15 = new LeitorCsv(arquivosParte15);
        LeitorCsv thread16 = new LeitorCsv(arquivosParte16);

        thread1.start();
        thread2.start(); 
        thread3.start();
        thread4.start();

        thread5.start();
        thread6.start(); 
        thread7.start();
        thread8.start();

        thread9.start();
        thread10.start(); 
        thread11.start();
        thread12.start();

        thread13.start();
        thread14.start(); 
        thread15.start();
        thread16.start();

        try {
            thread1.join(); 
            thread2.join(); 
            thread3.join();
            thread4.join();

            thread5.join(); 
            thread6.join(); 
            thread7.join();
            thread8.join();

            thread9.join(); 
            thread10.join(); 
            thread11.join();
            thread12.join();

            thread13.join(); 
            thread14.join(); 
            thread15.join();
            thread16.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long duration = endTime - startTime; // Calcula a duração em milissegundos

        System.out.println("O tempo de execução foi: " + duration + " milissegundos");
    }
}



