/**
 * 
 */
package programa;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author JN
 *
 */
public class Dados {
	//número de atributos é igual ao histograma + id de classe
	public static final int N_ATRIBUTOS = 256;
	public static final String[] NOMES_CLASSES = {"cifar_8","cifar_9"};
	public static final int N_CLASSES = Dados.NOMES_CLASSES.length;

	// número de exemplos usados, obs: o programa calcula
	public static int nExemplos;

	//probabilidade a priori das classes
	public static Hashtable<String, Number> probabilidadesAPriori;


	//médias de cada um dos atributos em relação às classes
	private static Double[][] medias;

	//desvios-padrão de cada um dos atributos para cada uma das classes
	private static Double[][] desviosPadrao;

	//contém uma lista de uma determinada coluna dos vetores que servem como 
	// dados
	private static List<Integer> atributosDaCoordenada;
	private static List<Double> densidadeDaCoordenada;

	public Dados(){
		instanciarAtributos();
		preencherMatrizesComZeros();
	}
	private void instanciarAtributos() {
		Dados.medias = new Double[N_CLASSES][N_ATRIBUTOS];
		Dados.desviosPadrao = new Double[N_CLASSES][N_ATRIBUTOS]; 
		Dados.atributosDaCoordenada = new ArrayList<Integer>();
		Dados.densidadeDaCoordenada = new ArrayList<Double>();
	}
	
	private void preencherMatrizesComZeros() {
		for(int i = 0; i < N_CLASSES; i++){
			for (int j = 0; j < N_ATRIBUTOS; j++){
				medias[i][j] = 0.0;
				desviosPadrao[i][j] = 0.0;
			}
		}
	}
	
	public static Double[][] getMedias() {
		return medias;
	}
	public static Double[][] getDesviosPadrao() {
		return desviosPadrao;
	}
	public static List<Integer> getAtributosDaCoordenada() {
		return atributosDaCoordenada;
	}
	public static List<Double> getDensidadeDaCoordenada() {
		return densidadeDaCoordenada;
	}
	public static Hashtable<String,Number> getProbabilidadesAPriori(){
		return probabilidadesAPriori;
	}
	public static void setProbabilidadeAPriori(double probabilidade){
		
	}
	public static void setarProbabilidadesAPriori(Hashtable<String,Number> probabilidades){
		Dados.probabilidadesAPriori = probabilidades;
		Number nExemplos = new Integer(Dados.nExemplos); 
		for (int i = 0; i < Dados.N_CLASSES; i++){
			probabilidadesAPriori.put(Dados.NOMES_CLASSES[i], 
					probabilidadesAPriori.get(Dados.NOMES_CLASSES[i]).doubleValue() 
							/ nExemplos.intValue());
			System.out.println(probabilidades.get(Dados.NOMES_CLASSES[i]));
		}
	}
	//não precisa de coordenada pois ele já vai adicionar no lugar correto
	public static void setarAtributoDaCoordenadaDoVetor(int valor){
		Dados.getAtributosDaCoordenada().add(valor);
	}

	public static void setarDensidadeDaCoordenada(double valor){
		Dados.getDensidadeDaCoordenada().add(valor);
	}

	public static void setarMediaDaCoordenada(int linha,int coluna, double valor){
		medias[linha][coluna] =  valor;
	}

	public static void setarDesvioPadraoDaCoordenada(int linha,int coluna, double valor){
		desviosPadrao[linha][coluna] = valor;
	}
}
