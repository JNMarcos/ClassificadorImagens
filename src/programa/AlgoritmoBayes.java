package programa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
				medias[i][j] = 0.0;
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
				System.out.println(medias[k][l]);
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
			System.out.println(classe);
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				posicaoArrayList = calcularPosicaoArrayList(i, j) - 1;
				variancias[classe][j] = variancias[classe][j] +  Math.pow(Dados.getAtributosDaCoordenada().get(posicaoArrayList) - 
						Dados.getMedias()[classe][j], 2);
			}
		}

		for (int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				variancias[i][j] = variancias[i][j].doubleValue()/Dados.nExemplosPorClasse[i];
				desviosPadrao[i][j] = Math.sqrt(variancias[i][j].doubleValue());
				System.out.println(desviosPadrao[i][j] + "   " + variancias[i][j]);				
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
	
	public static int calcularPosicaoArrayList2(int i, int j){
		return (i+1)*(j+1) + (i)*(Dados.nExemplos - (j+1));
	}


	//calcula função densidade de uma só vez
	public static void calcularFuncoesDensidade(){
		List<Double> densidades = new ArrayList<Double>();
		for (int i = 0; i < Dados.N_CLASSES * Dados.nExemplos * Dados.N_ATRIBUTOS; i++){
				densidades.add(null);
		}
		int posicaoArrayList = 0;
		int x = 0;

		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_ATRIBUTOS - 1; j++){
				posicaoArrayList = calcularPosicaoArrayList(i, j) - 1;
				x = Dados.getAtributosDaCoordenada().get(posicaoArrayList);

				for (int k = 0; k < Dados.N_CLASSES; k++){
					//densidade em relação à classe A
					densidades.set((k * (Dados.N_ATRIBUTOS - 1)) +  posicaoArrayList, (1/(Math.sqrt(2*Math.PI)*Dados.
							getDesviosPadrao()[k][j])) * 
							Math.pow(Math.E,(Math.pow(x - 
									Dados.getMedias()[k][j], 2)/(2*Math.
											pow(Dados.getDesviosPadrao()[k][j], 2)))));
					System.out.println("Densidade: " + densidades.get((k * (Dados.N_ATRIBUTOS - 1)) + posicaoArrayList));
				}
			}
		}
		Dados.setarDensidades(densidades);
	}


	public static void calcularProbabilidades(){
		//BigDecimal[][] probabilidades = new BigDecimal[Dados.N_CLASSES][Dados.nExemplos];
		Double[][] probabilidades = new Double[Dados.N_CLASSES][Dados.nExemplos];
		int posicaoArrayList = 0;
		double probabilidade = 0.0;

		for(int i = 0; i < Dados.N_CLASSES; i++){
			for (int j = 0; j < Dados.nExemplos; j++){
				//probabilidades[i][j] = new BigDecimal("0.00");
				probabilidades[i][j] = new Double(0.00);
			}
		}

		for (int i = 0; i < Dados.N_CLASSES; i++){
				for (int k = 0; k < Dados.N_ATRIBUTOS - 1; k++){
					for (int j = 0; j < Dados.nExemplos; j++){
					posicaoArrayList = calcularPosicaoArrayList2(k, j) - 1;
					probabilidade = Math.
							log(Dados.getDensidadesDosAtributos().get((i * (Dados.N_ATRIBUTOS - 1) ) +  posicaoArrayList).doubleValue());
					probabilidades[i][j] = probabilidades[i][j] + probabilidade;
					//probabilidades[i][j].add(new BigDecimal(Double.toString(Math.
					//log(Dados.getDensidadesDosAtributos().get((i+1) * posicaoArrayList).doubleValue()))));
					//System.out.println(probabilidade + "   " + probabilidades[i][j].add(BigDecimal.valueOf(probabilidade)).toString() );
					//probabilidades[i][j] = new BigDecimal((probabilidades[i][j].add(BigDecimal.valueOf(probabilidade)).toString()));

				}
				//falta multiplicar pela probabilidade a priori
			}
		}
		
		for (int i = 0; i < Dados.N_CLASSES; i++){
			for(int j = 0; j < Dados.nExemplos; j++){
				System.out.println("probabilidade: "  + probabilidades[i][j]);
			}
		}

		Dados.setarProbabilidades(probabilidades);

	}

	public static void decidirClasse(){
		BufferedWriter gravador = null;
		try {
			gravador = new BufferedWriter(new FileWriter("saidaTreinamento.txt"));
			gravador.append("Classe correta" + "     " + "Classe Algoritmo" + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BigDecimal maiorProbabilidade = new BigDecimal("0.00000000000");

		for (int i = 0; i < Dados.nExemplos; i++){
			for (int j = 0; j < Dados.N_CLASSES; j++){
				//if (Dados.getProbabilidades()[j][i].max(maiorProbabilidade) != null){
				//maiorProbabilidade = Dados.getProbabilidades()[j][i];
			}
		}

		System.out.println("Maior probabilidade:   " + maiorProbabilidade);
		maiorProbabilidade = new BigDecimal("0.00000000000");
		try {
			gravador.flush();
			gravador.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
