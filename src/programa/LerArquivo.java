/**
 * 
 */
package programa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author JN
 *
 */
public class LerArquivo {
	static Scanner scannerLinha = null;
	static Scanner scannerAtributo = null;

	private LerArquivo(){
		try {
			scannerLinha = new Scanner(new FileReader("data.txt"))
					.useDelimiter("\\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// passa pela parte do arquivo que não possui informação 'útil' para
	// o cálculo em si
	public static void pularArrobas(){
		// o motivo de ser mais três é pq tem espaço para o tipo, informação
		for (int i = 0; i < Dados.N_ATRIBUTOS + 3; i++){
			scannerLinha.next();
		}
	}

	public static void lerDados(int nAtributo){
		double media = 0;
		int soma = 0;
		int atributo = 0;


		String vetorString;

		while (scannerLinha.hasNext()){
			vetorString = scannerLinha.next();
			System.out.println(vetorString);

			//sacnner atributo vai até o atributo que se deseja, dado uma string
			scannerAtributo = new Scanner(vetorString)
					.useDelimiter(",");

			for (int i = 0; i < nAtributo; i++){
				scannerAtributo.nextInt();
			}

			atributo = scannerAtributo.nextInt();
			System.out.println(atributo);
			soma = soma + atributo;

			//fecha o scannerAtributo
			scannerAtributo.close();
		}
		
		media = soma/Dados.N_ATRIBUTOS;
		System.out.println(media);
	}
	
	public static void main(String[] bayesXAltura){
		LerArquivo la = new LerArquivo();
		pularArrobas();
		lerDados(12);
	}
}
