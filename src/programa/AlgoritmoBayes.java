package programa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

		int posicaoArrayList = 0;
		int classe ;
		for (int i = 0; i < Dados.nExemplos; i++){
			posicaoArrayList = calcularPosicaoArrayList(i, Dados.N_ATRIBUTOS - 1) - 1;
			classe = Dados.getAtributosDaCoordenada().get(posicaoArrayList);
			System.out.println(classe);
			//menos 1 pq o último atributo é a classe
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				posicaoArrayList = calcularPosicaoArrayList(i, j) - 1;

				medias[classe][j] = medias[classe][j].doubleValue() + Dados.getAtributosDaCoordenada().
						get(posicaoArrayList);
			} 
		}

		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				medias[i][j] = medias[i][j]/Dados.nExemplosPorClasse[i];				
			}
		}

		Dados.setarMedias(medias); 

		//desmarque o comentário caso queira ver as médias de cada atributo em relação
		// às classes
		for (int k = 0; k < Dados.N_CLASSES; k++){
			//menos 1 pq o último atributo é a classe
			for (int l = 0; l < Dados.N_ATRIBUTOS - 1; l++){
				System.out.println("média " + k + "   " + l + "  :" + medias[k][l]);
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

		int posicaoArrayList = 0;
		int classe;

		for (int i = 0; i < Dados.nExemplos; i++){
			posicaoArrayList = calcularPosicaoArrayList(i, Dados.N_ATRIBUTOS - 1) - 1;
			classe = Dados.getAtributosDaCoordenada().get(posicaoArrayList);
			//System.out.println(classe);
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				posicaoArrayList = calcularPosicaoArrayList(i, j) - 1;
				variancias[classe][j] = variancias[classe][j] +  Math.pow(
						Dados.getAtributosDaCoordenada().get(posicaoArrayList) - 
						Dados.getMedias()[classe][j], 2);
			}
		}

		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				variancias[i][j] = variancias[i][j].doubleValue()/Dados.nExemplosPorClasse[i];
				desviosPadrao[i][j] = Math.sqrt(variancias[i][j].doubleValue());
				//System.out.println(desviosPadrao[i][j] + "   " + variancias[i][j]);				
			}
		}


		//seta os resultados
		Dados.setarVariancias(variancias);
		Dados.setarDesviosPadrao(desviosPadrao);
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
	 */

	//somente é usado se optarmos por armazenar todos os valores num único ArrayList, em vez de ser,
	//momentâneo, como está
	public static int calcularPosicaoArrayList(int i, int j){
		return (i+1)*(j+1) + (i)*(Dados.N_ATRIBUTOS - (j+1));
	}

	/*
	public static int calcularPosicaoArrayList2(int i, int j, int k){
		return (k * Dados.nExemplos) + (i+1)*(j+1) + (i)*(Dados.nExemplos - (j+1));
	}
	 */

	//calcula função densidade de uma só vez
	public static void calcularFuncoesDensidade(){
		//List<Double> densidades = new ArrayList<Double>();
		Double[][][] densidades = new Double[Dados.nExemplos][Dados.N_ATRIBUTOS - 1][Dados.N_CLASSES];

		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				for (int k = 0; k < Dados.N_CLASSES; k++){
					densidades[i][j][k] = 0.0;
				}
			}
		}

		int posicaoArrayList = 0;
		int x = 0;

		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				posicaoArrayList = calcularPosicaoArrayList(i, j) - 1;
				x = Dados.getAtributosDaCoordenada().get(posicaoArrayList);

				for (int k = 0; k < Dados.N_CLASSES; k++){
					densidades[i][j][k] = (1.0/(Math.sqrt(2*Math.PI)*Dados.getDesviosPadrao()[k][j])) * 
							Math.pow(Math.E,(Math.pow(x - 
									Dados.getMedias()[k][j], 2)/(2*Math.
											pow(Dados.getDesviosPadrao()[k][j], 2))));
				}
			}
		}
		Dados.setarDensidades(densidades);

		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				for (int k = 0; k < Dados.N_CLASSES; k++){
					System.out.println("densidade " + i + " " + j + " " + k + " " +densidades[i][j][k]);
				}
			}
		}
	}


	public static void calcularProbabilidades(){
		Double[][] probabilidades = new Double[Dados.N_CLASSES][Dados.nExemplos];
		//BigDecimal[][] probabilidades = new BigDecimal[Dados.N_CLASSES][Dados.nExemplos];
		double probabilidade = 0.0;

		for(int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.nExemplos; j++){
				//probabilidades[i][j] = new BigDecimal("0.00");
				probabilidades[i][j] = new Double(0.00);
			}
		}

		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_CLASSES; j++){
				for (int k = 0; k < Dados.N_ATRIBUTOS - 1; k++){
					//posicaoArrayList = calcularPosicaoArrayList2(k, j, i);
					probabilidade = Math.
							log10(Dados.getDensidades()[i][k][j]);
					probabilidades[j][i] =  probabilidades[j][i] + probabilidade;
				}
			}
		}

		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.nExemplos; j++){
				//falta somar pela probabilidade a priori
				probabilidades[i][j] = probabilidades[i][j] + (Math.log10(Dados.probabilidadesAPriori[i]));
				System.out.println("probabilidade: " + i + "  " + j + "  " + probabilidades[i][j]);
			}
		}
		Dados.setarProbabilidades(probabilidades);
	}

	public static void decidirClasse(){
		//para cada exemplo, salva a maior classe
		int[] classeMaiorProbabilidade = new int[Dados.nExemplos];

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
		double maiorProbabilidade = Double.MIN_VALUE;
		double probabilidade;
System.out.println("\n\n\n");
		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_CLASSES; j++){
				probabilidade = Dados.getProbabilidades()[j][i].doubleValue();
				System.out.println(probabilidade);
				if(probabilidade >= maiorProbabilidade){
					classeMaiorProbabilidade[i] = j;
					System.out.println("classe maior: " + classeMaiorProbabilidade[i]);
					maiorProbabilidade = probabilidade;
				}
				maiorProbabilidade = Double.MIN_VALUE;
			}
			
		}	
		Dados.setarClasseMaiorProbabilidade(classeMaiorProbabilidade);

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
