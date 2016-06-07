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
	private Double[] medias;
	private Double[] desviosPadrao;
	
	public Dados(){
		setMedias();
		setDesviosPadrao();
	}
	
	public Double[] getMedias() {
		return medias;
	}
	public void setMedias() {
		this.medias = new Double[N_ATRIBUTOS];
	}
	public Double[] getDesviosPadrao() {
		return desviosPadrao;
	}
	public void setDesviosPadrao() {
		this.desviosPadrao = new Double[N_ATRIBUTOS]; 
	}
}
