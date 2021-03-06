package simulated.annealing;

import java.io.IOException;

public class SimulatedAnnealing {
	private double temperaturaAtual = 0, temperaturaFinal = 0;
	private double taxaArrefecimento = 0;
	private Gerador gerador = null;
	
    
	public SimulatedAnnealing(double temperaturaInicial, double taxaArrefecimento, double temperaturaFinal, Gerador gerador){
		this.temperaturaAtual = temperaturaInicial;
		this.taxaArrefecimento = taxaArrefecimento;
		this.temperaturaFinal = temperaturaFinal;
		this.gerador = gerador;
	}
	
	private double probabilidadeEscolha(double energiaSolucaoAtual, double energiaNovaSolucao, double temperatura) {
        if (energiaNovaSolucao < energiaSolucaoAtual) { //a solu��o nova � melhor
            return 1.0;
        }
        // nova solu��o � m� -> calcular probabilidade de aceita��o da solu��o
        return Math.exp((energiaSolucaoAtual - energiaNovaSolucao) / temperatura);
    }
	
	public void run(boolean full) throws IOException{
		Rota solucaoAtual = gerador.geraRota(null);
		Rota melhorSolucao = new Rota(solucaoAtual);
		System.out.println("Temperatura Inicial: " + temperaturaAtual);
		do {
			Rota novaSolucao = full?gerador.geraRota(null):gerador.geraRota(solucaoAtual); 
			
            double energiaSolucaoAtual = solucaoAtual.getDistanciaTotal();
            double energiaNovaSolucao = novaSolucao.getDistanciaTotal();

            if (probabilidadeEscolha(energiaSolucaoAtual, energiaNovaSolucao, temperaturaAtual) > Math.random()) {
                solucaoAtual = new Rota(novaSolucao);
            }

            // Guardar a melhor solu��o at� agr encontrada
            if (solucaoAtual.getDistanciaTotal() < melhorSolucao.getDistanciaTotal()) {
                melhorSolucao = new Rota(solucaoAtual);
            }
            
            temperaturaAtual *= 1-taxaArrefecimento;
		} while( temperaturaAtual > temperaturaFinal);
		System.out.println("Temperatura Final: " + temperaturaFinal);
		
		System.out.println("Melhor Solucao:");
		melhorSolucao.print();
		
		System.out.println("\nSolucao Atual:");
		solucaoAtual.print();
	}
	
}
