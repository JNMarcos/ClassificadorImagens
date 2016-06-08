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
	private static Double[] medias;
	private static Double[] desviosPadrao;
	
	//contém uma lista de uma determinada coluna dos vetores que servem como 
	// dados
	private static List<Integer> atributosDaCoordenada;
	
	public Dados(){
		setMedias();
		setDesviosPadrao();
		setAtributosDaCoordenada();
		preencherMatrizesComZeros();
	}
	
	private void preencherMatrizesComZeros() {
		for (int i = 0; i < N_ATRIBUTOS; i++){
			medias[i] = 0.0;
			desviosPadrao[i] = 0.0;
		}
	}

	public static Double[] getMedias() {
		return medias;
	}
	public static void setMedias() {
		Dados.medias = new Double[N_ATRIBUTOS];
	}
	public static Double[] getDesviosPadrao() {
		return desviosPadrao;
	}
	public static void setDesviosPadrao() {
		Dados.desviosPadrao = new Double[N_ATRIBUTOS]; 
	}
	
	public static List<Integer> getAtributosDaCoordenada() {
		return atributosDaCoordenada;
	}

	public static void setAtributosDaCoordenada() {
		Dados.atributosDaCoordenada = new ArrayList<Integer>();
	}
	
	
	//não precisa de coordenada pois ele já vai adicionar no lugar correto
	public static void setarAtributoDaCoordenadaDoVetor(int valor){
		Dados.getAtributosDaCoordenada().add(valor);
	}
	
	public static void setarMediaNaCoordenada(int coordenada, double valor){
		medias[coordenada] =  valor;
	}
	
	public static void setarDesvioPadraoNaCoordenada(int coordenada, double valor){
		desviosPadrao[coordenada] = valor;
	}
}
