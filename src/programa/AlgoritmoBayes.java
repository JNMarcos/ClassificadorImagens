package programa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @author JN
 * 
 */
public class AlgoritmoBayes {



	//calcular as Médias de uma vez só
	public static void calcularMedias(){
		//menos 1 pq o último atributo é a classe
		Double[][] medias = new Double[Dados.N_CLASSES][Dados.N_ATRIBUTOS - 1];

		for(int i = 0; i < Dados.N_CLASSES; i++){
			//menos 1 pq o último atributo é a classe
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				medias[i][j] = new Double (0.0);
			}
		}

		//usado para pegar a posição no arraylis do exemplo usado para treinamento
		int posicaoArrayList = 0;

		//usado para pegar o último atributo (a classe) do exemplo de treinamento
		int classe;
		/*
		for (int i = 0; i < Dados.nExemplos; i++){
			posicaoArrayList = calcularPosicaoArrayList(i, Dados.N_ATRIBUTOS - 1) - 1;
			classe = Dados.getAtributosDaCoordenada().get(posicaoArrayList);
		 */

		// vai até o tamanho dos exemplos usados para treinamento
		for (int i = 0; i < Dados.getExemplosTreinamento().length; i++){
			posicaoArrayList = Dados.getExemplosTreinamento()[i];
			classe = Dados.getAtributos().get(posicaoArrayList)[Dados.N_ATRIBUTOS - 1];
			//System.out.println(classe);
			//menos 1 pq o último atributo é a classe
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				//posicaoArrayList = calcularPosicaoArrayList(i, j) - 1;

				//medias[classe][j] = medias[classe][j].doubleValue() + Dados.getAtributosDaCoordenada().
				//	get(posicaoArrayList);


				//soma a média o valor do exemplo usado no treinamento
				medias[classe][j] = medias[classe][j].doubleValue() + 
						Dados.getAtributos().get(posicaoArrayList)[j];
			} 
		}

		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				medias[i][j] = medias[i][j]/Dados.getNExemplosTreinamentoPorClasse()[i];				
			}
		}

		Dados.setarMedias(medias); 

		//desmarque o comentário caso queira ver as médias de cada atributo em relação
		// às classes
		for (int k = 0; k < Dados.N_CLASSES; k++){
			//menos 1 pq o último atributo é a classe
			for (int l = 0; l < Dados.N_ATRIBUTOS - 1; l++){
				System.out.println("média " + k + "   " + l + "  :" + Dados.getMedias()[k][l]);
			}
		}
	}

	/*
	//calcula uma média por vez
	public static void calcularMedia(int coordenada){
		int media = 0;

		for (int i = 0; i < Dados.getAtributosDaCoordenada().size(); i++){
			media = media + Dados.getAtributosDaCoordenada().get(i);
		}
		System.out.println(media);
		Dados.setarMediaDaCoordenada(coordenada, media);   
	}
	 */

	//calcula os desvios padrão de uma só vez
	public static void calcularDesviosPadrao(){
		Double[][] variancias = new Double[Dados.N_CLASSES][Dados.N_ATRIBUTOS];
		Double[][] desviosPadrao = new Double[Dados.N_CLASSES][Dados.N_ATRIBUTOS];

		for(int i = 0; i < Dados.N_CLASSES; i++){
			//menos 1 pq o último atributo é a classe
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				variancias[i][j] = 0.0;
				desviosPadrao[i][j] = 0.0;
			}
		}


		//usado para pegar a posição no arraylis do exemplo usado para treinamento
		int posicaoArrayList = 0;

		//usado para pegar o último atributo (a classe) do exemplo de treinamento
		int classe;

		/*
		for (int i = 0; i < Dados.nExemplos; i++){
			posicaoArrayList = calcularPosicaoArrayList(i, Dados.N_ATRIBUTOS - 1) - 1;
			classe = Dados.getAtributosDaCoordenada().get(posicaoArrayList);
		 */

		for (int i = 0; i < Dados.getExemplosTreinamento().length; i++){
			//System.out.println(classe);
			posicaoArrayList = Dados.getExemplosTreinamento()[i];
			classe = Dados.getAtributos().get(posicaoArrayList)[Dados.N_ATRIBUTOS - 1];
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				/*posicaoArrayList = calcularPosicaoArrayList(i, j) - 1;


				variancias[classe][j] = variancias[classe][j] +  Math.pow(
						Dados.getAtributosDaCoordenada().get(posicaoArrayList) - 
						Dados.getMedias()[classe][j], 2);
				 */

				variancias[classe][j] = variancias[classe][j] +  
						Math.pow(Dados.getAtributos().get(posicaoArrayList)[j] -
								Dados.getMedias()[classe][j],2);
			}
		}

		//seta os resultados
		Dados.setarVariancias(variancias);
		Dados.setarDesviosPadrao(desviosPadrao);

		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				variancias[i][j] = variancias[i][j].doubleValue()/Dados.nExemplosTreinamentoPorClasse[i];
				desviosPadrao[i][j] = Math.sqrt(variancias[i][j].doubleValue());
				System.out.println("Desvio : " + Dados.getDesviosPadrao()[i][j] + "   Variância: " + Dados.getVariancias()[i][j]);				
			}
		}
	}

	/*
	//calcula um desvio padrão por vez
	public static void calcularDesvioPadrao(int coordenada){
		double variancia = 0.0;
		double desvioPadrao = 0.0;
		for (int i = 0; i < Dados.getAtributosDaCoordenada().size(); i++){
			variancia = variancia +  Math.pow(Dados.getAtributosDaCoordenada().get(i) - 
					Dados.getMedias()[coordenada], 2);
		}
		variancia = variancia/Dados.getAtributosDaCoordenada().size();
		desvioPadrao = Math.sqrt(variancia);
		System.out.print(desvioPadrao);
		Dados.setarDesvioPadraoDaCoordenada(coordenada, desvioPadrao);
	}

	//somente é usado se optarmos por armazenar todos os valores num único ArrayList, em vez de ser,
	//momentâneo, como está
	public static int calcularPosicaoArrayList(int i, int j){
		return (i+1)*(j+1) + (i)*(Dados.N_ATRIBUTOS - (j+1));
	}


	public static int calcularPosicaoArrayList2(int i, int j, int k){
		return (k * Dados.nExemplos) + (i+1)*(j+1) + (i)*(Dados.nExemplos - (j+1));
	}
	 */

	//calcula função densidade de uma só vez
	public static void calcularFuncoesDensidade(){
		//List<Double> densidades = new ArrayList<Double>();
		//Double[][][] densidades = new Double[Dados.nExemplos][Dados.N_ATRIBUTOS - 1][Dados.N_CLASSES];

		//calcula-se a densidade apenas para os atributos dos exemplos na qual são para teste
		//por isso que se retira do n° total de exemplos o nº de exemplos para treinamento
		//obtendo ao final, apenas o nº exemplos de teste
		//subtrai um em Dados.N_ATRIBUTOS pois não se faz o cálculo com a classe
		Double[][][] densidades = new Double[Dados.N_CLASSES][Dados.nExemplos - Dados.exemplosTreinamento.length][Dados.N_ATRIBUTOS - 1];
		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.nExemplos - Dados.exemplosTreinamento.length; j++){
				for (int k = 0; k < Dados.N_ATRIBUTOS - 1; k++){
					densidades[i][j][k] = 0.0;
				}
			}
		}

		List<Integer> nDosExemplos = new ArrayList<Integer>();

		//int kk = 0;
		//pega todos os exemplos de treinamento do vetor para arraylist
		for (int i = 0; i < Dados.getExemplosTreinamento().length; i++){
			nDosExemplos.add(Dados.getExemplosTreinamento()[i]);
			//System.out.println(nDosExemplos.get(i));
			//kk++;
		}
		//System.out.println(kk + "  " + Dados.nExemplos);

		//pega o índice do exemplo no array
		int indice;

		//indica o índice da coluna da matriz densidade
		int indiceColunaMatriz;

		//indica o valor do atributo para o cálculo da densidade
		int x;

		for (int i = 0; i < Dados.N_CLASSES; i++){
			indiceColunaMatriz = 0;

			//passa por todos os exemplos, depois decide se é pafra calcular ou não
			//a densidade
			for (int j = 0; j < Dados.nExemplos; j++){
				/*
				posicaoArrayList = calcularPosicaoArrayList(i, j) - 1;
				x = Dados.getAtributosDaCoordenada().get(posicaoArrayList);
				 */

				indice = nDosExemplos.indexOf(j);

				//pelo método indexOf(Obj) se o índice for -1, é pq não existe no arraylist
				// logo não é está no conjunto de treinamento, então pode calcular sua 
				//densidade
				if (indice == -1){
					
					//para cada um dos atributos do exemplo, calcula-se a densidade
					for (int k = 0; k < Dados.N_ATRIBUTOS - 1; k++){
						//pega-se o atributo na posicao k no elemento j
						x = Dados.getAtributos().get(j)[k];
						densidades[i][indiceColunaMatriz][k] = 
								(1.0/(Math.sqrt(2*Math.PI)*Dados.getDesviosPadrao()[i][k])) * 
								Math.pow(Math.E,(Math.pow(x - 
										Dados.getMedias()[i][k], 2)/(2*Math.
												pow(Dados.getDesviosPadrao()[i][k], 2))));
					}
					++indiceColunaMatriz;
				}
			}
		}
		Dados.setarDensidades(densidades);

		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.nExemplos - Dados.exemplosTreinamento.length; j++){
				for (int k = 0; k < Dados.N_ATRIBUTOS - 1; k++){
					System.out.println("densidade " + i + " " + j + " " + k + " " +Dados.getDensidades()[i][j][k]);
				}
			}
		}
	}


	public static void calcularProbabilidades(){
		Double[][] probabilidades = new Double[Dados.N_CLASSES][Dados.nExemplos - Dados.exemplosTreinamento.length];
		double probabilidade;

		for(int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.nExemplos - Dados.exemplosTreinamento.length; j++){
				probabilidades[i][j] = new Double(0.00);
			}
		}

		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.nExemplos - Dados.exemplosTreinamento.length; j++){
				for (int k = 0; k < Dados.N_ATRIBUTOS - 1; k++){
					//posicaoArrayList = calcularPosicaoArrayList2(k, j, i);
					probabilidade = Math.log10(Dados.getDensidades()[i][j][k]);
					probabilidades[i][j] =  probabilidades[i][j] + probabilidade;
				}
			}
		}

		Dados.setarProbabilidades(probabilidades);
		
		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.nExemplos - Dados.exemplosTreinamento.length; j++){
				//falta somar pela probabilidade a priori
				probabilidades[i][j] = probabilidades[i][j] + (Math.log10(Dados.probabilidadesAPriori[i]));
				System.out.println("probabilidade: " + i + "  " + j + "  " + Dados.getProbabilidades()[i][j]);
			}
		}
		
	}

	public static void decidirClasse(){
		//para cada exemplo, salva a maior classe
		int[] classeMaiorProbabilidade = new int[Dados.nExemplos];
		for (int i = 0; i < Dados.nExemplos; i++){
			classeMaiorProbabilidade[i] = -1;
		}

		BufferedWriter gravador = null;

		try {
			gravador = new BufferedWriter(new FileWriter("saidaTreinamento.txt"));
			gravador.append("Classe correta" + "     " + "Classe Algoritmo" + "\n");
			gravador.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//a probabilidade está negativa
		int vezes = 0;
		double maiorProbabilidade;
		double probabilidade;
		System.out.println("\n\n\n");
		int p =0;
		for (int i = 0; i < Dados.nExemplos; i++){
			while(p < Dados.N_CLASSES - 1){
				//for (int j = 0; j < Dados.N_CLASSES - 1; j++){
				/*
				probabilidade = (Dados.getProbabilidades()[j][i]);
				System.out.println(probabilidade);
				if (probabilidade > maiorProbabilidade){
					classeMaiorProbabilidade[i] = j;
					System.out.println("classe maior: " + classeMaiorProbabilidade[i]);
					maiorProbabilidade = probabilidade;
					vezes++;
				 */

				if (Dados.getProbabilidades()[p][i] > Dados.getProbabilidades()[p+1][i]){
					classeMaiorProbabilidade[i] = p;
					vezes++;
				} else {
					classeMaiorProbabilidade[i] = p + 1;
					vezes++;
				}
				p++;
			}
		}	
		System.out.println(vezes);
		Dados.setarClasseMaiorProbabilidade(classeMaiorProbabilidade);
		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_CLASSES - 1; j++){
				System.out.println("classe maior do elemento " + i +  "    " + Dados.classeMaiorProbabilidade[i]);
			}
		}
		int posicaoArrayList = 0;
		int[] contadorDeAcerto = new int[Dados.N_CLASSES];
		for (int i = 0; i < Dados.N_CLASSES; i++){
			contadorDeAcerto[i] = 0;
		}
		int x = 0;
		for (int i = 0; i < Dados.nExemplos; i++){
			//tira um do argumento pq a posição na matriz do último atributo é nColuna -1
			// tira um do resultado porque ele diz a ordem. ou seja, começa do primeiro...
			posicaoArrayList = calcularPosicaoArrayList(i, Dados.N_ATRIBUTOS - 1) - 1;
			//dá a classe do vetor
			x = Dados.getAtributosDaCoordenada().get(posicaoArrayList);
			//System.out.println(x);
			if (x == Dados.classeMaiorProbabilidade[i]){
				contadorDeAcerto[x] = contadorDeAcerto[x] + 1;
			}
			try {
				//System.out.println(Dados.classeMaiorProbabilidade[i]);
				gravador.write(x + "                             " + Dados.classeMaiorProbabilidade[i]);
				gravador.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < Dados.N_CLASSES; i++){
			try {
				gravador.write("Quantidade de exemplos da classe " + i + "  " + Dados.nExemplosPorClasse[i]);
				gravador.newLine();
				gravador.write("Quantidade de acertos em relação à classe " + i + "   " + contadorDeAcerto[i]);
				gravador.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			gravador.flush();
			gravador.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
