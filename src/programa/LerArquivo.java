/**
 * 
 */
package programa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * @author JN
 *
 */
public class LerArquivo {
	static Scanner scannerLinha = null;
	static Scanner scannerAtributo = null;

	private LerArquivo(){
		//não se pode instanicar a classe LerArquivo porr fora da classe
	}

	// passa pela parte do arquivo que não possui informação 'útil' para
	// o cálculo em si
	private static void pularArrobas(){
		// o motivo de ser mais três é pq tem espaço para o tipo, informação
		for (int i = 0; i < Dados.N_ATRIBUTOS + 3; i++){
			scannerLinha.next();
		}
	}
	
	@Ignore
	private static int acharValorCoordenadaNoVetor(int coordenadaVetor){
		String vetorString;
		
		vetorString = scannerLinha.next();
		System.out.println(vetorString);

		//sacnner atributo vai até o atributo que se deseja, dado uma string
		scannerAtributo = new Scanner(vetorString)
				.useDelimiter(",");

		//passa pelos atributos da string que não são o atributo que se deseja
		for (int i = 0; i < coordenadaVetor; i++){
			scannerAtributo.nextInt();
		}
		
		//pega o atributo que se deseja
		return scannerAtributo.nextInt();
	}
	
	@Ignore
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
			//menos 1 porque o array começa com pos 0
			Dados.setarAtributoDaCoordenadaDoVetor(valorAtributoNoVetor);
		}
		scannerLinha.close();
	}
	
	public static void lerDados(){
		int[] nExemplosPorClasse = 	new int[Dados.N_CLASSES];
		
		//
		for(int i = 0; i < Dados.N_CLASSES; i++){
			nExemplosPorClasse[i] = 0;
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
			vetorString = scannerLinha.next();
			System.out.println(vetorString);
			
			//sacnner atributo vai até o atributo que se deseja, dado uma string
			scannerAtributo = new Scanner(vetorString)
					.useDelimiter(",");
			scannerAtributo.next();
			
			//passa pelos atributos da string que não são o atributo que se deseja
			//menos 1 pq tem o último que é uma string.
			for (int i = 0; i < Dados.N_ATRIBUTOS - 1; i++){
				valorAtributoNoVetor = scannerAtributo.nextInt();
				Dados.setarAtributoDaCoordenadaDoVetor(valorAtributoNoVetor);
				//desmarque caso queira ver cada um dos atributos do vetor
				//sendo passado para o ArrayList
				/*System.out.print(valorAtributoNoVetor + "   ");*/
			}
			
			//após passar todos os atributos numéricos, chega ao da classe
			classeVetor = scannerAtributo.next();
			
			
			//desmarque caso queira ver a classe do vetor em questão
			/*System.out.print(classeVetor + " ");*/
			
			for (int i = 0; i <  Dados.N_CLASSES; i++){
				
				//verifica se a classe no qual o vetor está classificado
				//contém o nome da classe
				if (classeVetor.contains(Dados.NOMES_CLASSES[i])){
					nExemplosPorClasse[i] = nExemplosPorClasse[i] + 1;
					
					//insere a classe convertido em inteiro para o ArrayList
					Dados.setarAtributoDaCoordenadaDoVetor(i);
					System.out.println(i);
					break;
				}
			}
			//fecha o scannerAtributo			
			scannerAtributo.close();
		}
	
		//descomente caso queira ver o número de exemplos de uma classe.
		
		for (int i = 0; i < Dados.N_CLASSES; i++){
			System.out.println("nº de exemplos de " + Dados.NOMES_CLASSES[i]  + 
					": " + nExemplosPorClasse[i]);
		}
		
		
		//seta o número de exemplos e as prioridades a priori de cada classe
		Dados.setarNExemplosPorClasse(nExemplosPorClasse);
		Dados.setarProbabilidadesAPriori();
		
		//fecha o scannerLinha
		scannerLinha.close();
	}
	
	public static void main(String[] bayesXAltura){
		Dados.instanciarAtributos();
		LerArquivo.lerDados();

		System.out.println(Dados.nExemplos);
		System.out.println(Dados.getAtributosDaCoordenada().size());
		
		AlgoritmoBayes.calcularMedias();
		AlgoritmoBayes.calcularDesviosPadrao();
		AlgoritmoBayes.calcularFuncoesDensidade();
		AlgoritmoBayes.calcularProbabilidades();
		//AlgoritmoBayes.decidirClasse();
	}
}
