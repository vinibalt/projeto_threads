import java.io.*; 
import java.util.*;

public class LeitorCsv {

    private File arquivo;

    LeitorCsv(File csv) {
        setLeitorCsv(csv);
    }

    public void setLeitorCsv(File csv) {
        this.arquivo = csv;
    }

    public File getLeitorCsv() {
        return this.arquivo;
    }

    public void leitura() {
        File arquivo = getLeitorCsv();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {

            // Ignora a primeira linha (cabeçalho)
            leitor.readLine(); 

            String linha;
            ArrayList<Float> listaDeTemperaturas = new ArrayList<>();
            int anoDeReferencia = 1995;
            int mesDeReferencia = 1;
            String cidadeLida = "";

            while ((linha = leitor.readLine()) != null) {
                // Divide a linha pelos valores separados por vírgula
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
                        // Calculando temperatura média se a lista não estiver vazia
                        if (!listaDeTemperaturas.isEmpty()) {
                            float soma = 0;
                            for (float temperatura : listaDeTemperaturas) {
                                soma += temperatura;
                            }
                            
                            float temperaturaMedia = soma / listaDeTemperaturas.size();
                            float temperaturaMin = Collections.min(listaDeTemperaturas);
                            float temperaturaMax = Collections.max(listaDeTemperaturas);
                            
                            System.out.printf("Cidade: %-15s | T.Min: %7.2f | T.Med: %7.2f | T.Max: %7.2f | Mês: %2d | Ano: %4d\n", 
                            cidadeLida, temperaturaMin, temperaturaMedia, temperaturaMax, mesDeReferencia, anoDeReferencia);
                        
                        } else {
                            System.out.printf("Nenhuma temperatura válida para a cidade: %s, Mês: %d, Ano: %d%n", 
                                cidadeLida, mesDeReferencia, anoDeReferencia);
                        }

                        // Limpa a lista e atualiza o mês
                        listaDeTemperaturas.clear();
                        mesDeReferencia = mesLidoConvertido;
                        listaDeTemperaturas.add(tempLidaConvertida);
                    }
                } else {
                    // Calcular e imprimir as temperaturas do mês anterior se a lista não estiver vazia
                    if (!listaDeTemperaturas.isEmpty()) {
                        float soma = 0;
                        for (float temperatura : listaDeTemperaturas) {
                            soma += temperatura;
                        }
                        
                        float temperaturaMedia = soma / listaDeTemperaturas.size();
                        float temperaturaMin = Collections.min(listaDeTemperaturas);
                        float temperaturaMax = Collections.max(listaDeTemperaturas);
                        
                        System.out.printf("Cidade: %-15s | T.Min: %7.2f | T.Med: %7.2f | T.Max: %7.2f | Mês: %2d | Ano: %4d\n", 
                        cidadeLida, temperaturaMin, temperaturaMedia, temperaturaMax, mesDeReferencia, anoDeReferencia);
                    
                    }

                    // Atualiza o ano de referência
                    anoDeReferencia = anoLidoConvertido;
                    mesDeReferencia = mesLidoConvertido;
                    listaDeTemperaturas.clear();
                    listaDeTemperaturas.add(tempLidaConvertida); // Adiciona a temperatura atual ao novo mês
                }
            }

            // Impressão das temperaturas do último mês
            if (!listaDeTemperaturas.isEmpty()) {
                float soma = 0;
                for (float temperatura : listaDeTemperaturas) {
                    soma += temperatura;
                }
                
                float temperaturaMedia = soma / listaDeTemperaturas.size();
                float temperaturaMin = Collections.min(listaDeTemperaturas);
                float temperaturaMax = Collections.max(listaDeTemperaturas);

                System.out.printf("Cidade: %-15s | T.Min: %7.2f | T.Med: %7.2f | T.Max: %7.2f | Mês: %2d | Ano: %4d\n", 
                cidadeLida, temperaturaMin, temperaturaMedia, temperaturaMax, mesDeReferencia, anoDeReferencia);
            
            
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
