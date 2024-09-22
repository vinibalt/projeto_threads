import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads14 {

    public static void main(String[] args) {
        String caminhoDaPasta = "C:\\Users\\William\\Desktop\\Faculdade\\5_Quinto Semestre\\Programaçao Concorrente Distributiva\\Trabalho\\temperaturas_cidades\\temperaturas_cidades";
        File pasta = new File(caminhoDaPasta);
        File[] arquivos = pasta.listFiles();

        int numeroThreads = 8;
        ExecutorService executor = Executors.newFixedThreadPool(numeroThreads);
        long tempoInicio = System.currentTimeMillis();

        int numeroCidades = arquivos.length;
        int cidadesPorThread = (int) Math.ceil((double) numeroCidades / numeroThreads);

        for (int i = 0; i < numeroThreads; i++) {
            final int indiceInicial = i * cidadesPorThread;
            final int indiceFinal = Math.min(indiceInicial + cidadesPorThread, numeroCidades);

            executor.submit(() -> {
                processarArquivos(arquivos, indiceInicial, indiceFinal);
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        long tempoFim = System.currentTimeMillis();
        System.out.println("Versão 14 - Tempo médio de execução: " + (tempoFim - tempoInicio) + "ms");
    }

    public static void processarArquivos(File[] arquivos, int indiceInicial, int indiceFinal) {
        for (int i = indiceInicial; i < indiceFinal; i++) {
            lerArquivoCSV(arquivos[i]);
        }
    }

    public static void lerArquivoCSV(File arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            Map<String, Map<Integer, List<Double>>> temperaturasPorAno = new HashMap<>();

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Country")) continue;
                String[] dados = linha.split(",");
                String cidade = dados[1];
                int mes = Integer.parseInt(dados[2]);
                int ano = Integer.parseInt(dados[4]);
                double temperatura = Double.parseDouble(dados[5]);

                temperaturasPorAno
                        .computeIfAbsent(cidade, k -> new HashMap<>())
                        .computeIfAbsent(ano, k -> new ArrayList<>())
                        .add(temperatura);
            }

            ExecutorService executorAno = Executors.newCachedThreadPool();
            for (String cidade : temperaturasPorAno.keySet()) {
                for (Integer ano : temperaturasPorAno.get(cidade).keySet()) {
                    List<Double> temperaturas = temperaturasPorAno.get(cidade).get(ano);
                    executorAno.submit(() -> calcularEImprimir(cidade, ano, temperaturas));
                }
            }
            executorAno.shutdown();
            while (!executorAno.isTerminated()) {
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void calcularEImprimir(String cidade, int ano, List<Double> temperaturas) {
        double min = Collections.min(temperaturas);
        double max = Collections.max(temperaturas);
        double media = temperaturas.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);

        System.out.println("Cidade/Ano: " + cidade + "/" + ano + " | Mínima: " + String.format("%.2f", min) +
                " | Máxima: " + String.format("%.2f", max) + " | Média: " + String.format("%.2f", media));
    }
}