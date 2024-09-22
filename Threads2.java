

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads2 {

    public static void main(String[] args) {
        String caminhoDaPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\Trabalho das Temperaturas\\temperaturas_cidades\\temperaturas_cidades";
        File pasta = new File(caminhoDaPasta);
        File[] arquivos = pasta.listFiles();

        if (arquivos != null) {
            long tempoTotal = 0;

            for (int rodada = 0; rodada < 10; rodada++) {
                long tempoInicio = System.currentTimeMillis();
                
                int meio = arquivos.length / 2;
                File[] parte1 = Arrays.copyOfRange(arquivos, 0, meio);
                File[] parte2 = Arrays.copyOfRange(arquivos, meio, arquivos.length);

                ExecutorService executor = Executors.newFixedThreadPool(2);

                executor.execute(() -> processarArquivos(parte1));
                executor.execute(() -> processarArquivos(parte2));

                executor.shutdown();
                while (!executor.isTerminated()) {
                }

                long tempoFim = System.currentTimeMillis();
                tempoTotal += (tempoFim - tempoInicio);
            }

            System.out.println("Tempo médio de execução: " + (tempoTotal / 10) + "ms");
        } else {
            System.out.println("A pasta não contém arquivos.");
        }
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

            leitorCsv.readLine();

            HashMap<String, HashMap<String, List<Double>>> dados = new HashMap<>();

            while ((linha = leitorCsv.readLine()) != null) {
                String[] valores = linha.split(",");
                String cidade = valores[1];
                String mesAno = valores[2] + "/" + valores[4];
                double tempMedia = Double.parseDouble(valores[5]);

                dados.putIfAbsent(cidade, new HashMap<>());
                dados.get(cidade).putIfAbsent(mesAno, new ArrayList<>());
                dados.get(cidade).get(mesAno).add(tempMedia);
            }

            for (String cidade : dados.keySet()) {
                HashMap<String, List<Double>> dadosCidade = dados.get(cidade);

                for (String mesAno : dadosCidade.keySet()) {
                    List<Double> temps = dadosCidade.get(mesAno);
                    double min = Collections.min(temps);
                    double max = Collections.max(temps);
                    double media = temps.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

                    System.out.printf("Cidade: %s | Mês/Ano: %s | Mínima: %.2f | Máxima: %.2f | Média: %.2f%n",
                            cidade, mesAno, min, max, media);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}