/**
 * 
 */
package programa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
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
	/*
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
			scannerLinha = new Scanner(new FileReader("bancoTreinamento.txt"))
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
			Dados.setarAtributoDaCoordenadaDoVetor(valorAtributoNoVetor);
		}
		scannerLinha.close();
	}
	*/
	
	public static void lerDados(){
		int nExemplos = 0;
		Integer adicionarUm = new Integer(1);
		Hashtable<String,Number> nExemplosPorClasse = 
				new Hashtable<String,Number>();
		
		//
		for(int i = 0; i < Dados.N_CLASSES; i++){
			nExemplosPorClasse.put(Dados.NOMES_CLASSES[i], 0);
		}
		
		String vetorString;
		int valorAtributoNoVetor = 0;
		
		String classeVetor;
		
		try {
			scannerLinha = new Scanner(new FileReader("bancoTreinamento.txt"))
					.useDelimiter("\\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pularArrobas();
		while (scannerLinha.hasNext()){	
			nExemplos++;
			vetorString = scannerLinha.next();
			System.out.println(vetorString);

			//sacnner atributo vai at� o atributo que se deseja, dado uma string
			scannerAtributo = new Scanner(vetorString)
					.useDelimiter(",");

			//passa pelos atributos da string que n�o s�o o atributo que se deseja
			//menos 1 pq tem o �ltimo que � uma string.
			for (int i = 0; i <= Dados.N_ATRIBUTOS - 1; i++){
				valorAtributoNoVetor = scannerAtributo.nextInt();
				Dados.setarAtributoDaCoordenadaDoVetor(valorAtributoNoVetor);
				System.out.print(valorAtributoNoVetor + "   ");
			}
			
			//ap�s passar todos os atributos num�ricos, chega ao da classe
			classeVetor = scannerAtributo.next();
			System.out.println(classeVetor + "   ");
			
			for (int i = 0; i <  Dados.N_CLASSES; i++){
				
				//verifica se a classe no qual o vetor est� classificado
				//cont�m o nome da classe
				if (classeVetor.contains(Dados.NOMES_CLASSES[i])){
					nExemplosPorClasse.put(Dados.NOMES_CLASSES[i],nExemplosPorClasse.
							get(Dados.NOMES_CLASSES[i]).intValue() + adicionarUm.intValue());
					
					//insere a classe convertido em n�mero para o ArrayList
					Dados.setarAtributoDaCoordenadaDoVetor(i);
					break;
				}
			}
			//fecha o scannerAtributo			
			scannerAtributo.close();
		}
		Dados.nExemplos = nExemplos;
		Dados.setarProbabilidadesAPriori(nExemplosPorClasse);
		System.out.println("\n\n" + nExemplos);
		
		//fecha o scannerLinha
		scannerLinha.close();
	}
	
	public static void main(String[] bayesXAltura){
		Dados dados = new Dados();

		//for (int i = 1; i <= Dados.N_ATRIBUTOS; i++){
			//LerArquivo.lerDados(i);
		//}
		LerArquivo.lerDados();

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Dados.nExemplos);
		System.out.println(Dados.getAtributosDaCoordenada().size());
		
		//AlgoritmoBayes.calcularMedias();
		//AlgoritmoBayes.calcularDesviosPadrao();
	}
}
