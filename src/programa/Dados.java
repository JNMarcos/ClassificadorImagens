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
	public static final int N_ATRIBUTOS = 255;
	public static int nExemplos;
	public static double probabilidadeClasseA = 0.5;
	public static double probabilidadeClasseB = 0.5;
	private static Double[] mediasClasseA;
	private static Double[] mediasClasseB;
	private static Double[] desviosPadraoClasseA;
	private static Double[] desviosPadraoClasseB;
	
	//contém uma lista de uma determinada coluna dos vetores que servem como 
	// dados
	private static List<Integer> atributosDaCoordenada;
	private static List<Double> densidadeDaCoordenada;
	
	public Dados(){
		instanciarAtributos();
		preencherMatrizesComZeros();
	}
	private void instanciarAtributos() {
		Dados.mediasClasseA = new Double[N_ATRIBUTOS];
		Dados.mediasClasseB = new Double[N_ATRIBUTOS];
		Dados.desviosPadraoClasseA = new Double[N_ATRIBUTOS]; 
		Dados.desviosPadraoClasseB = new Double[N_ATRIBUTOS]; 
		Dados.atributosDaCoordenada = new ArrayList<Integer>();
		Dados.densidadeDaCoordenada = new ArrayList<Double>();
	}
	
	private void preencherMatrizesComZeros() {
		for (int i = 0; i < N_ATRIBUTOS; i++){
			mediasClasseA[i] = 0.0;
			desviosPadraoClasseA[i] = 0.0;
			mediasClasseB[i] = 0.0;
			desviosPadraoClasseB[i] = 0.0;
		}
	}

	public static Double[] getMediasClasseA() {
		return mediasClasseA;
	}
	public static Double[] getMediasClasseB() {
		return mediasClasseB;
	}
	public static Double[] getDesviosPadraoClasseA() {
		return desviosPadraoClasseA;
	}
	public static Double[] getDesviosPadraoClasseB() {
		return desviosPadraoClasseB;
	}
	
	public static List<Integer> getAtributosDaCoordenada() {
		return atributosDaCoordenada;
	}

	//não precisa de coordenada pois ele já vai adicionar no lugar correto
	public static void setarAtributoDaCoordenadaDoVetor(int valor){
		Dados.getAtributosDaCoordenada().add(valor);
	}
	
	public static List<Double> getDensidadeDaCoordenada() {
		return densidadeDaCoordenada;
	}
	
	public static void setarDensidadeDaCoordenada(double valor){
		Dados.getDensidadeDaCoordenada().add(valor);
	}
	
	public static void setarMediaDaCoordenadaClasseA(int coordenada, double valor){
		mediasClasseA[coordenada] =  valor;
	}
	
	public static void setarMediaDaCoordenadaClasseB(int coordenada, double valor){
		mediasClasseB[coordenada] =  valor;
	}
	
	public static void setarDesvioPadraoDaCoordenadaClasseA(int coordenada, double valor){
		desviosPadraoClasseA[coordenada] = valor;
	}
	
	public static void setarDesvioPadraoDaCoordenadaClasseB(int coordenada, double valor){
		desviosPadraoClasseB[coordenada] = valor;
	}
}
