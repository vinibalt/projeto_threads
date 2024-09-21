import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String caminhoPasta = "C:\\Users\\breno\\OneDrive\\Área de Trabalho\\projeto_threads";
        File pasta = new File(caminhoPasta);
        File[] arquivos = pasta.listFiles(); //listFiles retorna os arquivos no diretório


        for(File arquivo:arquivos) {

            if(arquivo.getName().endsWith(".csv")) {
                LeitorCsv leitor = new LeitorCsv(arquivo);
                leitor.leitura();
            }

        }
    }
}