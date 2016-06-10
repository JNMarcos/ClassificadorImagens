/**
 * 
 */
package programa;

import java.math.BigDecimal;
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
	public static int nExemplos = 0;
	public static int[] nExemplosPorClasse;

	//probabilidade a priori das classes
	public static double[] probabilidadesAPriori;


	//médias de cada um dos atributos em relação às classes
	private static Double[][] medias;

	//variancias de cada um dos atriutos em relação às classes
	private static Double[][] variancias;
	
	//desvios-padrão de cada um dos atributos para cada uma das classes
	private static Double[][] desviosPadrao;
	
	//define as probabilidades de um exemplo pertencer a uma classe
	private static Double[][] probabilidades;

	//contém uma lista de uma determinada coluna dos vetores que servem como 
	// dados
	private static List<Integer> atributosDaCoordenada;
	private static List<Double> densidadesDosAtributos;

	private Dados(){

	}
	public static void instanciarAtributos() {
		Dados.atributosDaCoordenada = new ArrayList<Integer>();
	}

	public static Double[][] getMedias() {
		return medias;
	}
	public static Double[][] getVariancias(){
		return variancias;
	}
	public static Double[][] getDesviosPadrao() {
		return desviosPadrao;
	}
	public static List<Integer> getAtributosDaCoordenada() {
		return atributosDaCoordenada;
	}
	public static List<Double> getDensidadesDosAtributos() {
		return densidadesDosAtributos;
	}
	public static double[] getProbabilidadesAPriori(){
		return probabilidadesAPriori;
	}
	public static Double[][] getProbabilidades(){
		return probabilidades;
	}

	public static void setarNExemplosPorClasse(int[] nExemplosPorClasse){
		Dados.nExemplosPorClasse = nExemplosPorClasse;
		setarNExemplosTotal();
	}

	private static void setarNExemplosTotal(){
		for (int i = 0; i < Dados.N_CLASSES; i++){
			Dados.nExemplos = Dados.nExemplos + Dados.nExemplosPorClasse[i];
		}
	}
	public static void setarProbabilidadesAPriori(){
		Dados.probabilidadesAPriori = new double[N_CLASSES];
		for (int i = 0; i < Dados.N_CLASSES; i++){	
			Dados.probabilidadesAPriori[i] = Dados.nExemplosPorClasse[i]/Dados.nExemplos;
		}
	}
	public static void setarProbabilidades(Double[][] probabilidades){
		Dados.probabilidades = probabilidades;
	}
	//não precisa de coordenada pois ele já vai adicionar no lugar correto
	public static void setarAtributoDaCoordenadaDoVetor(int valor){
		Dados.getAtributosDaCoordenada().add(valor);
	}
	public static void setarDensidades(List<Double> densidades){
		Dados.densidadesDosAtributos = densidades;
	}
	public static void setarMedias(Double[][] mediasMatriz){
		Dados.medias =  mediasMatriz;
	}
	public static void setarVariancias(Double[][] varianciasMatriz){
		Dados.variancias = varianciasMatriz;
	}
	public static void setarDesviosPadrao(Double[][] desviosPadraoMatriz){
		Dados.desviosPadrao = desviosPadraoMatriz;
	}
}
