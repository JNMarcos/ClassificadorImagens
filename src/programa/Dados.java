/**
 * 
 */
package programa;

/**
 * @author JN
 *
 */
public class Dados {
	public static final int N_ATRIBUTOS = 255;
	private static Double[] medias;
	private static Double[] desviosPadrao;
	
	public Dados(){
		setMedias();
		setDesviosPadrao();
		setInicializarComZeros();
	}
	
	private void setInicializarComZeros() {
		for (int i = 0; i < N_ATRIBUTOS; i++){
			medias[i] = 0.0;
			desviosPadrao[i] = 0.0;
		}
	}

	public static Double[] getMedias() {
		return medias;
	}
	public static void setMedias() {
		medias = new Double[N_ATRIBUTOS];
	}
	public static Double[] getDesviosPadrao() {
		return desviosPadrao;
	}
	public static void setDesviosPadrao() {
		desviosPadrao = new Double[N_ATRIBUTOS]; 
	}
	
	public static void setarMediaNaCoordenada(int coordenada, double valor){
		medias[coordenada] =  valor;
	}
	
	public static void setarDesvioPadraoNaCoordenada(int coordenada, double valor){
		desviosPadrao[coordenada] = valor;
	}
}
