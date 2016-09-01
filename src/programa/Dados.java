/**
 * 
 */
package programa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JN
 *
 */
public class Dados {
	//número de atributos é igual ao histograma + id de classe
	public static final int N_ATRIBUTOS = 257;
	public static final String[] NOMES_CLASSES = {"cifar_8","cifar_9"};
	public static final int N_CLASSES = Dados.NOMES_CLASSES.length;
	public static int porcentagem = 70;

	// número de exemplos usados, obs: o programa calcula
	public static int nExemplos;
	public static int[] nExemplosPorClasse;
	public static int[] exemplosTreinamento;
	public static int[] nExemplosTreinamentoPorClasse;

	//probabilidade a priori das classes
	public static double[] probabilidadesAPriori;
	
	//guarda a classe (não o valor) de maior probabilidade de um exemplo
	public static int[] classeMaiorProbabilidade;

	//médias de cada um dos atributos em relação às classes
	private static Double[][] medias;

	//variancias de cada um dos atriutos em relação às classes
	private static Double[][] variancias;
	
	//desvios-padrão de cada um dos atributos para cada uma das classes
	private static Double[][] desviosPadrao;
	
	//densidades
	private static Double[][][] densidades;
	
	//define as probabilidades de um exemplo pertencer a uma classe
	private static Double[][] probabilidades;

	//contém uma lista de uma determinada coluna dos vetores que servem como 
	// dados
	//private static List<Integer> atributosDaCoordenada;
	
	//o Integer representa a ordem do exemplo
	private static List<int[]> atributos;
	//private static List<Double> densidadesDosAtributos;
	

	private Dados(){

	}
	public static void instanciarAtributos() {
		//Dados.atributosDaCoordenada = new ArrayList<Integer>();
		Dados.atributos = new ArrayList<int[]>();
	}
	
	//obter valores
	public static List<int[]> getAtributos() {
		return atributos;
	}
	public static int[] getExemplosTreinamento(){
		return exemplosTreinamento;
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
	public static Double[][][] getDensidades() {
		return densidades;
	}
	public static double[] getProbabilidadesAPriori(){
		return probabilidadesAPriori;
	}
	public static int[] getClasseMaiorProbabilidade(){
		return classeMaiorProbabilidade;
	}
	public static int[] getNExemplosTreinamentoPorClasse(){
		return nExemplosTreinamentoPorClasse;
	}
	public static Double[][] getProbabilidades(){
		return probabilidades;
	}
	
	//setar valores
	public static void setarExemplos(List<int[]> exemplos){
		Dados.atributos = exemplos;
	}
	public static void setarExemplo(int[] exemplo){
		Dados.getAtributos().add(exemplo);
	}
	public static void setarNExemplosPorClasse(int[] nExemplosPorClasse){
		Dados.nExemplosPorClasse = nExemplosPorClasse;
	}
	public static void setarNExemplosTotal(int nEx){
		Dados.nExemplos = nEx;
	}
	public static void setarExemplosTreinamento(int[] exemplos){
		Dados.exemplosTreinamento = exemplos;
	}
	public static void setarNExemplosTreinamentoPorClasse(int[] nExemplosTreinamento){
		Dados.nExemplosTreinamentoPorClasse = nExemplosTreinamento;
	}
	public static void setarProbabilidadesAPriori(double[] priori){
		Dados.probabilidadesAPriori = priori;
	}
	public static void setarProbabilidades(Double[][] probabilidades){
		Dados.probabilidades = probabilidades;
	}
	public static void setarClasseMaiorProbabilidade(int[] nDaClasse){
		Dados.classeMaiorProbabilidade = nDaClasse;
	}
	public static void setarDensidades(Double[][][] densidades){
		Dados.densidades = densidades;
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
	/*
	public static List<Integer> getAtributosDaCoordenada() {
		return atributosDaCoordenada;
	}*/
	/*
	public static List<Double> getDensidadesDosAtributos() {
		return densidadesDosAtributos;
	}*/
	
	//não precisa de coordenada pois ele já vai adicionar no lugar correto
	/*
	public static void setarAtributoDaCoordenadaDoVetor(int valor){
		Dados.getAtributosDaCoordenada().add(valor);
	}
	public static void setarDensidades(List<Double> densidades){
		Dados.densidadesDosAtributos = densidades;
	}*/
}
