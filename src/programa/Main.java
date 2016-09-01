/**
 * 
 */
package programa;

/**
 * @author JN
 *
 */
public class Main {
	public static void main(String[] bayesXAltura){
		Dados.instanciarAtributos();
		LerArquivo.lerDados();
		//LerArquivo.mostrar();
		LerArquivo.escolherParaTreinamento();
	
		AlgoritmoBayes.calcularMedias();
		AlgoritmoBayes.calcularDesviosPadrao();
		AlgoritmoBayes.calcularFuncoesDensidade();
		AlgoritmoBayes.calcularProbabilidades();
		//AlgoritmoBayes.decidirClasse();
	}
}
