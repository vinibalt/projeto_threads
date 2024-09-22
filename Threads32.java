import java.io.*; 
import java.util.*;

public class Threads32 {
    public static void main(String[] args) {

        String caminhoPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\projeto_threads\\temperaturas";
        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles(); // listFiles retorna os arquivos no diretório

        List<File> arquivosLista = Arrays.asList(arquivos);
        
        // Dividindo os arquivos em 16 partes
        List<File> arquivosParte1 = arquivosLista.subList(0, 10);
        List<File> arquivosParte2 = arquivosLista.subList(10, 20);
        List<File> arquivosParte3 = arquivosLista.subList(20, 30);
        List<File> arquivosParte4 = arquivosLista.subList(30, 40);
        List<File> arquivosParte5 = arquivosLista.subList(40, 50);
        List<File> arquivosParte6 = arquivosLista.subList(50, 60);
        List<File> arquivosParte7 = arquivosLista.subList(60, 70);
        List<File> arquivosParte8 = arquivosLista.subList(70, 80);

        List<File> arquivosParte9 = arquivosLista.subList(80, 90);
        List<File> arquivosParte10 = arquivosLista.subList(90, 100);
        List<File> arquivosParte11 = arquivosLista.subList(100, 110);
        List<File> arquivosParte12 = arquivosLista.subList(110, 120);
        List<File> arquivosParte13 = arquivosLista.subList(120, 130);
        List<File> arquivosParte14 = arquivosLista.subList(130, 140);
        List<File> arquivosParte15 = arquivosLista.subList(140, 150);
        List<File> arquivosParte16 = arquivosLista.subList(150, 160);

        List<File> arquivosParte17 = arquivosLista.subList(160, 170);
        List<File> arquivosParte18 = arquivosLista.subList(170, 180);
        List<File> arquivosParte19 = arquivosLista.subList(180, 190);
        List<File> arquivosParte20 = arquivosLista.subList(190, 200);
        List<File> arquivosParte21 = arquivosLista.subList(200, 210);
        List<File> arquivosParte22 = arquivosLista.subList(210, 220);
        List<File> arquivosParte23 = arquivosLista.subList(220, 230);
        List<File> arquivosParte24 = arquivosLista.subList(230, 240);

        List<File> arquivosParte25 = arquivosLista.subList(240, 250);
        List<File> arquivosParte26 = arquivosLista.subList(250, 260);
        List<File> arquivosParte27 = arquivosLista.subList(260, 270);
        List<File> arquivosParte28 = arquivosLista.subList(270, 280);
        List<File> arquivosParte29 = arquivosLista.subList(280, 290);
        List<File> arquivosParte30 = arquivosLista.subList(290, 300);
        List<File> arquivosParte31 = arquivosLista.subList(300, 310);
        List<File> arquivosParte32 = arquivosLista.subList(310, 320);


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

        LeitorCsv thread17 = new LeitorCsv(arquivosParte17);
        LeitorCsv thread18 = new LeitorCsv(arquivosParte18);
        LeitorCsv thread19 = new LeitorCsv(arquivosParte19);
        LeitorCsv thread20 = new LeitorCsv(arquivosParte20);

        LeitorCsv thread21 = new LeitorCsv(arquivosParte21);
        LeitorCsv thread22 = new LeitorCsv(arquivosParte22);
        LeitorCsv thread23 = new LeitorCsv(arquivosParte23);
        LeitorCsv thread24 = new LeitorCsv(arquivosParte24);

        LeitorCsv thread25 = new LeitorCsv(arquivosParte25);
        LeitorCsv thread26 = new LeitorCsv(arquivosParte26);
        LeitorCsv thread27= new LeitorCsv(arquivosParte27);
        LeitorCsv thread28 = new LeitorCsv(arquivosParte28);

        LeitorCsv thread29 = new LeitorCsv(arquivosParte29);
        LeitorCsv thread30 = new LeitorCsv(arquivosParte30);
        LeitorCsv thread31 = new LeitorCsv(arquivosParte31);
        LeitorCsv thread32 = new LeitorCsv(arquivosParte32);

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

        thread17.start();
        thread18.start(); 
        thread19.start();
        thread20.start();

        thread21.start();
        thread22.start(); 
        thread23.start();
        thread24.start();

        thread25.start();
        thread26.start(); 
        thread27.start();
        thread28.start();

        thread29.start();
        thread30.start(); 
        thread31.start();
        thread32.start();

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

            thread17.join(); 
            thread18.join(); 
            thread19.join();
            thread20.join();

            thread21.join(); 
            thread22.join(); 
            thread23.join();
            thread24.join();

            thread25.join(); 
            thread26.join(); 
            thread27.join();
            thread28.join();

            thread29.join(); 
            thread30.join(); 
            thread31.join();
            thread32.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // Captura o tempo final
        long duration = endTime - startTime; // Calcula a duração em milissegundos

        System.out.println("O tempo de execução foi: " + duration + " milissegundos");
    }
}



