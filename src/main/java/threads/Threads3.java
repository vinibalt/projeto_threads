package threads;

import java.io.*;
import java.util.*;

public class Threads3 {

    public static void main(String[] args) {
        String caminhoDaPasta = "out/Dados";
        int numeroRodadas = 10;
        long[] temposDeExecucao = new long[numeroRodadas];

        for (int rodada = 0; rodada < numeroRodadas; rodada++) {
            long inicio = System.currentTimeMillis();

            File pasta = new File(caminhoDaPasta);
            File[] arquivos = pasta.listFiles();

            if (arquivos != null && arquivos.length >= 320) {
                Thread thread1 = new Thread(() -> processarArquivos(Arrays.copyOfRange(arquivos, 0, 80)));
                Thread thread2 = new Thread(() -> processarArquivos(Arrays.copyOfRange(arquivos, 80, 160)));
                Thread thread3 = new Thread(() -> processarArquivos(Arrays.copyOfRange(arquivos, 160, 240)));
                Thread thread4 = new Thread(() -> processarArquivos(Arrays.copyOfRange(arquivos, 240, 320)));

                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();

                try {
                    thread1.join();
                    thread2.join();
                    thread3.join();
                    thread4.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("A pasta não contém arquivos suficientes.");
            }

            long fim = System.currentTimeMillis();
            temposDeExecucao[rodada] = fim - inicio;
            System.out.println("Rodada " + (rodada + 1) + " concluída em " + temposDeExecucao[rodada] + " ms.");
        }

        long somaTempos = 0;
        for (long tempo : temposDeExecucao) {
            somaTempos += tempo;
        }
        long tempoMedio = somaTempos / numeroRodadas;

        System.out.println("Tempo médio de execução: " + tempoMedio + " ms.");
        salvarTempoEmArquivo("versao_3.txt", temposDeExecucao, tempoMedio);
    }

    private static void processarArquivos(File[] arquivos) {
        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().endsWith(".csv")) {
                lerArquivoCSV(arquivo);
            }
        }
    }

    private static void lerArquivoCSV(File arquivo) {
        try (BufferedReader leitorCsv = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = leitorCsv.readLine()) != null) {
                String[] valores = linha.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salvarTempoEmArquivo(String nomeArquivo, long[] temposDeExecucao, long tempoMedio) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nomeArquivo))) {
            escritor.println("Tempos de execução (ms) por rodada:");
            for (int i = 0; i < temposDeExecucao.length; i++) {
                escritor.println("Rodada " + (i + 1) + ": " + temposDeExecucao[i] + " ms");
            }
            escritor.println("Tempo médio de execução: " + tempoMedio + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}