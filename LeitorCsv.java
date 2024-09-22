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

        try(BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            leitor.readLine(); // Ignora a primeira linha (cabeçalho)

            ArrayList<Float> listaDeTemp = new ArrayList<>();

            while((linha = leitor.readLine()) != null) {
                String[] valores = linha.split(","); // Divide a linha pelos valores separados por vírgula
                
                String temp = valores[5]; // Pegando a temperatura

                listaDeTemp.add(Float.parseFloat(temp)); // Adiciona a temperatura convertida para Float à lista
            }

            //Cálculo da temperatura mínima e máxima
            float temperaturaMin = Collections.min(listaDeTemp);
            float temperaturaMax = Collections.max(listaDeTemp);

            //Cálculo da média
            float soma = 0;
            for(float temperatura : listaDeTemp) {
                soma += temperatura;
            }

            float temperaturaMedia = soma / listaDeTemp.size();

            System.out.printf("Temperatura mínima: %f\n", temperaturaMin);
            System.out.printf("Temperatura máxima: %f\n", temperaturaMax);
            System.out.printf("Temperatura média: %f\n", temperaturaMedia);

        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            System.out.println("Erro ao converter temperatura para número: " + e.getMessage());
        }
    }
}
