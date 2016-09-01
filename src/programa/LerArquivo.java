/**
 * 
 */
package programa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author JN
 *
 */
public class LerArquivo {
	static Scanner scannerLinha = null;
	static Scanner scannerAtributo = null;

	private LerArquivo(){
		//n�o se pode instanciar a classe LerArquivo porr fora da classe
	}

	// passa pela parte do arquivo que n�o possui informa��o '�til' para
	// o c�lculo em si
	private static void pularArrobas(){
		// o motivo de ser mais tr�s � pq tem espa�o para o tipo, informa��o
		for (int i = 0; i < Dados.N_ATRIBUTOS + 3; i++){
			scannerLinha.next();
		}
	}

	public static void lerDados(){
		int[] nExemplosPorClasse = 	new int[Dados.N_CLASSES];
		double[] probabilidadesAPriori = new double[Dados.N_CLASSES];
		String vetorString;
		int valorAtributoNoVetor = 0;
		String classeVetor;

		//
		for(int i = 0; i < Dados.N_CLASSES; i++){
			nExemplosPorClasse[i] = 0;
			//probabilidadesAPriori[i] = 0.0;
		}

		try {
			scannerLinha = new Scanner(new FileReader("bancoTreinamento.txt"))
					.useDelimiter("\\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pularArrobas();
		List<int[]> c = new ArrayList<int[]>();
		//ArrayList<Integer> a = new ArrayList<Integer>();

		int[] b = new int[Dados.N_ATRIBUTOS];

		
		while (scannerLinha.hasNext()){	
			vetorString = scannerLinha.next();

			//sacnner atributo vai at� o atributo que se deseja, dado uma string
			scannerAtributo = new Scanner(vetorString)
					.useDelimiter(",");

			//passa pelos atributos da string que n�o s�o o atributo que se deseja
			//menos 1 pq tem o �ltimo que � uma string.
			for (int i = 0; i < Dados.N_ATRIBUTOS - 1; i++){
				valorAtributoNoVetor = scannerAtributo.nextInt();
				//a.add(valorAtributoNoVetor);
				b[i] = valorAtributoNoVetor;
				//Dados.getAtributos().get(nExemplos).add(valorAtributoNoVetor);

				//antes da apresenta��o
				//Dados.setarAtributoDaCoordenadaDoVetor(valorAtributoNoVetor);


				//desmarque caso queira ver cada um dos atributos do vetor
				//sendo passado para o ArrayList
				/*System.out.print(valorAtributoNoVetor + "   ");*/
			}

			//ap�s passar todos os atributos num�ricos, chega ao da classe
			classeVetor = scannerAtributo.next();


			//desmarque caso queira ver a classe do vetor em quest�o
			/*System.out.print(classeVetor + " ");*/

			for (int i = 0; i <  Dados.N_CLASSES; i++){

				//verifica se a classe no qual o vetor est� classificado
				//cont�m o nome da classe
				if (classeVetor.contains(Dados.NOMES_CLASSES[i])){
					nExemplosPorClasse[i] = nExemplosPorClasse[i] + 1;

					//insere a classe convertido em inteiro para o ArrayList
					//a.add(i);
					b[Dados.N_ATRIBUTOS - 1] = i;
					//Dados.getAtributos().get(nExemplos).add(i);

					//antes da apresenta��o
					//Dados.setarAtributoDaCoordenadaDoVetor(i);
					break;
				}
			}
			//adiciona o ArrayList 'a' aos atributos
			c.add(b);
			b = new int[Dados.N_ATRIBUTOS];
			//Dados.setarExemplo(a);

			//limpa o arrayList a
			//a.clear();
			//a = new ArrayList<Integer>();
			//fecha o scannerAtributo	
			scannerAtributo.close();
		}

		//descomente caso queira ver o n�mero de exemplos de uma classe.
		for (int i = 0; i < Dados.N_CLASSES; i++){
			System.out.println("n� de exemplos de " + Dados.NOMES_CLASSES[i]  + 
					": " + nExemplosPorClasse[i]);
		}
		int nEx = 0;

		for (int i = 0; i < Dados.N_CLASSES; i++){
			nEx = nEx + nExemplosPorClasse[i];
		}
		//seta o n�mero de exemplos e as prioridades a priori de cada classe
		Dados.setarNExemplosPorClasse(nExemplosPorClasse);

		//n�meor de exemplos total
		Dados.setarNExemplosTotal(nEx);
		
		//seta a lista de atributos dos  exemplos
		Dados.setarExemplos(c);

		for (int i = 0; i < Dados.N_CLASSES; i++){
			probabilidadesAPriori[i] = (((double)nExemplosPorClasse[i])/Dados.nExemplos);
			System.out.println(probabilidadesAPriori[i]);
		}

		Dados.setarProbabilidadesAPriori(probabilidadesAPriori);

		//fecha o scannerLinha
		scannerLinha.close();
	}
	
	public static void mostrar(){
		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS; j++){
				System.out.print(Dados.getAtributos().get(i)[j] + "  ");
			}
			System.out.println();
		}
	}

	public static void escolherParaTreinamento(){

		int nExemplosTreinamento = 0; //talvez desnecess�rio
		int[] nExemplosClassesTreinamento = new int[Dados.N_CLASSES];
		int[] contadorExemplosTreinamentoParaAClasse = new int[Dados.N_CLASSES];
		List<Integer> listaSorteio = new ArrayList<Integer>();

		//seta o contador de exemplo para cada classe igual a 0
		for (int i = 0; i < Dados.N_CLASSES; i++){
			contadorExemplosTreinamentoParaAClasse[i] = 0;
		}

		//adiciona os n�meros dos exemplos � lista de sorteio
		for (int i = 0; i < Dados.nExemplos; i++){
			listaSorteio.add(i);
		}

		int[] exemplosUsadosTreinamento;

		//pega o n�mero de exemplos de cada classe
		for (int i = 0; i < Dados.N_CLASSES; i++){
			nExemplosClassesTreinamento[i] = (int) Math.ceil((Dados.porcentagem * Dados.nExemplosPorClasse[i])/100);
			nExemplosTreinamento = nExemplosTreinamento + nExemplosClassesTreinamento[i];
		}
		
		System.out.println("N�mero de exemplos para treinamento: " + nExemplosTreinamento);
		
		for (int i = 0; i < Dados.N_CLASSES; i++){
			System.out.println(nExemplosClassesTreinamento[i]);
		}
		
		//seta os n�meros de exemplos para treinamento de cada uma das classes
		Dados.setarNExemplosTreinamentoPorClasse(nExemplosClassesTreinamento);

		exemplosUsadosTreinamento = new int[nExemplosTreinamento];


		int nExemplosTreino = 0;
		Random gravador = new Random();

		//diz qual o exemplo: o primeiro, o segundo, o terceiro, ...
		int nDoExemplo;

		while (nExemplosTreino < nExemplosTreinamento){

			//ordena a lista de forma aleat�ria
			Collections.shuffle(listaSorteio, gravador);

			//seleciona o primeiro valor do arraylist
			nDoExemplo = listaSorteio.get(0).intValue();

			//remove o valor sorteado da lista de sorteio para evitar repeti��o
			listaSorteio.remove(0);

			for (int i = 0; i < Dados.N_CLASSES; i++){

				//se a posi��o do arrylist que indica a classe for igual ao valor atribu�do
				//� classe entra no if
				// de um jeito ou de outro vai entrar, mas depende do valor do i
				if (Dados.getAtributos().get(nDoExemplo)[Dados.N_ATRIBUTOS - 1] == 
						i){

					//se contador de n�mero de exemplos j� selecionados para aquela classe
					//for menor ou igual ao n�mero de exemplos que classe deve ter para treinamento
					//sen�o deve descart�-lo pois a cota de exemplos daquela classe
					// j� foi alcan�ada
					if (contadorExemplosTreinamentoParaAClasse[i] < nExemplosClassesTreinamento[i]){
						//adiciona a posi��o na qual o exemplos est� no arrayList para
						// o vetor
						exemplosUsadosTreinamento[nExemplosTreino] = nDoExemplo;

						//aumenta em um o contador para indicar que um exemplo foi adicionado
						// � lista de exemplos de treinamento
						contadorExemplosTreinamentoParaAClasse[i]++;

						//s� aumenta n� de exemplos de treino quando um exemplo � "v�lido"
						nExemplosTreino++;
					}
					break;
				}
			}
		}
		
		Dados.setarExemplosTreinamento(exemplosUsadosTreinamento);
		
		//comente a partir daqui caso n�o queira ver os exemplos escolhidos aleatoriamente
		int vezes = 0;
		for (int i = 0; i < Dados.getExemplosTreinamento().length; i++){
			System.out.println(Dados.getExemplosTreinamento()[i]);
			vezes++;
		}

		System.out.println(vezes + "   " + Dados.getExemplosTreinamento().length);
	}
}
