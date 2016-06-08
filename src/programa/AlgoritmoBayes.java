package programa;
/*
 * @author JN
 * 
 */
public class AlgoritmoBayes {

	//calcular as M�dias de uma vez s�
	public static void calcularMedias(){
		int mediaClasseA = 0;
		int mediaClasseB = 0;
		int posicaoArrayList = 0;
		for (int coordenada = 0; coordenada < Dados.N_ATRIBUTOS; coordenada++){
			for (int j = 0; j < Dados.nExemplos; j++){
				posicaoArrayList = calcularPosicaoArrayList(coordenada, j);
				//considera-se que a base sempre estar� ordenada
				  // dividido por 2 pq s� h� duas classes
				if (j < Dados.nExemplos/2){
					mediaClasseA = mediaClasseA + Dados.getAtributosDaCoordenada().
							get(posicaoArrayList);
				} else{
					mediaClasseB = mediaClasseB + Dados.getAtributosDaCoordenada().
							get(posicaoArrayList);
				}
			}
			Dados.setarMediaDaCoordenadaClasseA(coordenada, mediaClasseA); 
			Dados.setarMediaDaCoordenadaClasseB(coordenada, mediaClasseB); 

			//seta media para zero novamente
			mediaClasseA = 0;  
			mediaClasseB = 0;
		}
	}

	/*
	//calcula uma m�dia por vez
	public static void calcularMedia(int coordenada){
		int media = 0;
		
		for (int i = 0; i < Dados.getAtributosDaCoordenada().size(); i++){
			media = media + Dados.getAtributosDaCoordenada().get(i);
		}
		System.out.println(media);
		Dados.setarMediaDaCoordenada(coordenada, media);   
	}
	*/

	//calcula os desvios padr�o de uma s� vez
	public static void calcularDesviosPadrao(){
		double varianciaClasseA = 0.0;
		double desvioPadraoClasseA = 0.0;
		double varianciaClasseB = 0.0;
		double desvioPadraoClasseB = 0.0;
		
		int posicaoArrayList = 0;
		for (int coordenada = 0; coordenada < Dados.N_ATRIBUTOS; coordenada++){
			for (int j = 0; j < Dados.nExemplos; j++){
				posicaoArrayList = calcularPosicaoArrayList(coordenada, j);
				if (j < Dados.nExemplos/2){
				varianciaClasseA = varianciaClasseA +  Math.pow(Dados.getAtributosDaCoordenada().get(posicaoArrayList) - 
						Dados.getMediasClasseA()[coordenada], 2);
				} else{
					varianciaClasseB = varianciaClasseB +  Math.pow(Dados.getAtributosDaCoordenada().get(posicaoArrayList) - 
							Dados.getMediasClasseB()[coordenada], 2);
				}
			}

			//divide por 2 pq apenas metade dos elementos � da classe
			varianciaClasseA = varianciaClasseA/(Dados.getAtributosDaCoordenada().size()/2);
			varianciaClasseB = varianciaClasseB/(Dados.getAtributosDaCoordenada().size()/2);
			
			desvioPadraoClasseA = Math.sqrt(varianciaClasseA);
			desvioPadraoClasseB = Math.sqrt(varianciaClasseB);
			
			Dados.setarDesvioPadraoDaCoordenadaClasseA(coordenada, desvioPadraoClasseA);
			Dados.setarDesvioPadraoDaCoordenadaClasseB(coordenada, desvioPadraoClasseB);
			
			//seta para zero, para a nova itera��o
			varianciaClasseA = 0.0;
			desvioPadraoClasseA = 0.0;
			varianciaClasseB = 0.0;
			desvioPadraoClasseB = 0.0;
		}
	}

	/*
	//calcula um desvio padr�o por vez
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
	
	//somente � usado se optarmos por armazenar todos os valores num �nico ArrayList, em vez de ser,
	//moment�neo, como est�
	public static int calcularPosicaoArrayList(int i, int j){
		return (i+1)*(j+1) + (i)*(Dados.nExemplos - (j+1));
	}

	/*
	//calcula fun��o densidade de uma s� vez
	public static void calcularFuncaoDensidade(){
		double densidade = 0.0;
		int posicaoArrayList = 0;
		for (int coordenada = 0; coordenada < Dados.N_ATRIBUTOS; coordenada++){
			for (int j = 0; j < Dados.getAtributosDaCoordenada().size(); j++){
				posicaoArrayList = calcularPosicaoArrayList(coordenada, j);
				densidade = (1/(Math.sqrt(2*Math.PI)*Dados.
						getDesviosPadrao()[coordenada])) * 
						Math.pow(Math.E,(Math.pow(Dados.
								getAtributosDaCoordenada().get(posicaoArrayList) - 
								Dados.getMedias()[coordenada], 2)/(2*Math.
										pow(Dados.getDesviosPadrao()[coordenada], 2))) );
			}
			//falta terminar
		}
	}
	*/
	
	public static void calcularProbabilidade(){
		
	}

}
