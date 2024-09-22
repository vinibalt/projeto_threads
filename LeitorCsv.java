import java.io.*; 
import java.util.*;

public class LeitorCsv extends Thread {

    private List<File> arquivos;

    LeitorCsv(List<File> arquivos) {
        this.arquivos = arquivos;
    }
    
    public void leitura() {
        String nomeDaThread = Thread.currentThread().getName(); // Pega o nome da thread atual
        for (File arquivo : arquivos) {
            try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {

                // Ignora a primeira linha (cabeçalho)
                leitor.readLine(); 

                String linha;
                ArrayList<Float> listaDeTemperaturas = new ArrayList<>();
                int anoDeReferencia = 1995;
                int mesDeReferencia = 1;
                String cidadeLida = "";

                while ((linha = leitor.readLine()) != null) {
                    String[] valores = linha.split(","); 

                    cidadeLida = valores[1];
                    String anoLido = valores[4];
                    String mesLido = valores[2];
                    String temperaturaLida = valores[5];

                    int anoLidoConvertido = Integer.parseInt(anoLido);
                    int mesLidoConvertido = Integer.parseInt(mesLido);
                    float tempLidaConvertida = Float.parseFloat(temperaturaLida);

                    if (anoDeReferencia == anoLidoConvertido) {
                        if (mesDeReferencia == mesLidoConvertido) {
                            listaDeTemperaturas.add(tempLidaConvertida);
                        } else {
                            if (!listaDeTemperaturas.isEmpty()) {
                                float soma = 0;
                                for (float temperatura : listaDeTemperaturas) {
                                    soma += temperatura;
                                }
                                float temperaturaMedia = soma / listaDeTemperaturas.size();
                                float temperaturaMin = Collections.min(listaDeTemperaturas);
                                float temperaturaMax = Collections.max(listaDeTemperaturas);

                                // Incluindo o nome da thread na saída
                                System.out.printf("Thread: %s | Cidade: %-15s | T.Min: %7.2f | T.Med: %7.2f | T.Max: %7.2f | Mês: %2d | Ano: %4d\n", 
                                nomeDaThread, cidadeLida, temperaturaMin, temperaturaMedia, temperaturaMax, mesDeReferencia, anoDeReferencia);
                            }
                            listaDeTemperaturas.clear();
                            mesDeReferencia = mesLidoConvertido;
                            listaDeTemperaturas.add(tempLidaConvertida);
                        }
                    } else {
                        if (!listaDeTemperaturas.isEmpty()) {
                            float soma = 0;
                            for (float temperatura : listaDeTemperaturas) {
                                soma += temperatura;
                            }
                            float temperaturaMedia = soma / listaDeTemperaturas.size();
                            float temperaturaMin = Collections.min(listaDeTemperaturas);
                            float temperaturaMax = Collections.max(listaDeTemperaturas);

                            System.out.printf("Thread: %s | Cidade: %-15s | T.Min: %7.2f | T.Med: %7.2f | T.Max: %7.2f | Mês: %2d | Ano: %4d\n", 
                            nomeDaThread, cidadeLida, temperaturaMin, temperaturaMedia, temperaturaMax, mesDeReferencia, anoDeReferencia);
                        }
                        anoDeReferencia = anoLidoConvertido;
                        mesDeReferencia = mesLidoConvertido;
                        listaDeTemperaturas.clear();
                        listaDeTemperaturas.add(tempLidaConvertida);
                    }
                }

                if (!listaDeTemperaturas.isEmpty()) {
                    float soma = 0;
                    for (float temperatura : listaDeTemperaturas) {
                        soma += temperatura;
                    }
                    float temperaturaMedia = soma / listaDeTemperaturas.size();
                    float temperaturaMin = Collections.min(listaDeTemperaturas);
                    float temperaturaMax = Collections.max(listaDeTemperaturas);

                    System.out.printf("Thread: %s | Cidade: %-15s | T.Min: %7.2f | T.Med: %7.2f | T.Max: %7.2f | Mês: %2d | Ano: %4d\n", 
                    nomeDaThread, cidadeLida, temperaturaMin, temperaturaMedia, temperaturaMax, mesDeReferencia, anoDeReferencia);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        leitura();
    }
}
