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
		//n�o se pode instanicar a classe LerArquivo porr fora da classe
	}

	// passa pela parte do arquivo que n�o possui informa��o '�til' para
	// o c�lculo em si
	private static void pularArrobas(){
		// o motivo de ser mais tr�s � pq tem espa�o para o tipo, informa��o
		for (int i = 0; i < Dados.N_ATRIBUTOS + 3; i++){
			scannerLinha.next();
		}
	}
	
	private static int acharValorCoordenadaNoVetor(int coordenadaVetor){
		String vetorString;
		
		vetorString = scannerLinha.next();
		System.out.println(vetorString);

		//sacnner atributo vai at� o atributo que se deseja, dado uma string
		scannerAtributo = new Scanner(vetorString)
				.useDelimiter(",");

		//passa pelos atributos da string que n�o s�o o atributo que se deseja
		for (int i = 0; i < coordenadaVetor; i++){
			scannerAtributo.nextInt();
		}
		
		//pega o atributo que se deseja
		return scannerAtributo.nextInt();
	}

	public static void lerDados(int coordenadaVetor){
		int valorAtributoNoVetor = 0;
		try {
			scannerLinha = new Scanner(new FileReader("data.txt"))
					.useDelimiter("\\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pularArrobas();
		while (scannerLinha.hasNext()){
			valorAtributoNoVetor = acharValorCoordenadaNoVetor(coordenadaVetor);
			//fecha o scannerAtributo
			
			scannerAtributo.close();
			System.out.println(valorAtributoNoVetor);
			//menos 1 porque o array come�a com pos 0
			AlgoritmoBayes.calcularSoma(coordenadaVetor - 1, valorAtributoNoVetor);
		}
		scannerLinha.close();
	}
	
	public static void main(String[] bayesXAltura){
		Dados dados = new Dados();
		LerArquivo.lerDados(5);
	}
}
