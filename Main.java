package trabalho_programação_concorrente;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
	      String caminhoDaPasta = "C:/Users/breno/temperaturas_cidades"; 

	        File pasta = new File(caminhoDaPasta); 
	        File[] arquivos = pasta.listFiles(); //obtêm uma lista de todos os arquivos e subpastas dentro da pasta. Ele retorna um array de objetos File.

	        if (arquivos != null) { 
	            for (File arquivo : arquivos) {
	                if (arquivo.isFile() && arquivo.getName().endsWith(".csv")) { //Verifica se o objeto é um arquivo E verifica se é um arquivo CSV
	                    lerArquivoCSV(arquivo); // Chama o método para ler e processar o arquivo CSV
	                }
	            }
	        } else {
	            System.out.println("A pasta não contém arquivos.");
	        }
	    }

	    private static void lerArquivoCSV(File arquivo) {
	        try (BufferedReader leitorCsv = new BufferedReader(new FileReader(arquivo))) {
	            
	            String linha;

	            System.out.println("Lendo arquivo: " + arquivo.getName()); // Exibe o nome do arquivo sendo lido
	            
	            while ((linha = leitorCsv.readLine()) != null) {
	                String[] valores = linha.split(","); // Divide a linha pelos valores separados por vírgula
	                
	                System.out.println(Arrays.toString(valores)); // Exibe os valores no console
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}

}
