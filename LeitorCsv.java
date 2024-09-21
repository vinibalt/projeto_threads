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

            while((linha = leitor.readLine()) != null) {
                String[]valores = linha.split(","); // Divide a linha pelos valores separados por vírgula
                System.out.println(Arrays.toString(valores)); // Exibe os valores no console
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    
    
}
