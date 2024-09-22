package TrabalhoJava;

import java.io.*;
import java.util.*;

public class Threads1 {

    public static void main(String[] args) {
        String caminhoDaPasta = "C:\\Users\\William\\Desktop\\Faculdade\\5_Quinto Semestre\\Programaçao Concorrente Distributiva\\Trabalho\\temperaturas_cidades\\temperaturas_cidades";
        File pasta = new File(caminhoDaPasta);
        File[] arquivos = pasta.listFiles();

        long tempoInicio = System.currentTimeMillis();

        processarArquivos(arquivos, 0, arquivos.length);

        long tempoFim = System.currentTimeMillis();
        System.out.println("Tempo médio de execução: " + (tempoFim - tempoInicio) + "ms");
    }

    public static void processarArquivos(File[] arquivos, int indiceInicial, int indiceFinal) {
        for (int i = indiceInicial; i < indiceFinal; i++) {
            lerArquivoCSV(arquivos[i]);
        }
    }

    public static void lerArquivoCSV(File arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            Map<String, List<Double>> temperaturasPorMes = new HashMap<>();

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Country")) continue; // Ignora o cabeçalho
                String[] dados = linha.split(",");
                String cidade = dados[1];
                int mes = Integer.parseInt(dados[2]);
                int ano = Integer.parseInt(dados[4]);
                double temperatura = Double.parseDouble(dados[5]);

                String chave = cidade + "-" + mes + "/" + ano;
                temperaturasPorMes.computeIfAbsent(chave, k -> new ArrayList<>()).add(temperatura);
            }

            for (String chave : temperaturasPorMes.keySet()) {
                List<Double> temperaturas = temperaturasPorMes.get(chave);
                double min = Collections.min(temperaturas);
                double max = Collections.max(temperaturas);
                double media = temperaturas.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);

                System.out.println("Cidade/Mês/Ano: " + chave + " | Mínima: " + String.format("%.2f", min) +
                        " | Máxima: " + String.format("%.2f", max) + " | Média: " + String.format("%.2f", media));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}