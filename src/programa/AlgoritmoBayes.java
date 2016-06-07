package programa;
/*
 * @author JN
 * 
 */
public class AlgoritmoBayes {
	
	public static void calcularSoma(int coordenadaVetor, int valorCoordenadaDoVetor){
		Dados.setarMediaNaCoordenada(coordenadaVetor, Dados.getMedias()[coordenadaVetor] + valorCoordenadaDoVetor);
		Double valor = Dados.getMedias()[coordenadaVetor];
		System.out.println(valor);
	}
	

	public static void calcularMedias(){
		for (int i = 0; i < Dados.N_ATRIBUTOS; i++){
			Dados.getMedias()[i] = (Dados.getMedias()[i])/Dados.N_ATRIBUTOS;
			System.out.println(Dados.getMedias()[i]);
		}
	}
	
	public static void calcularDesvioPadrao(){
		
	}
	
	public static void calcularFuncaoDensidade(){
		
	}

}
