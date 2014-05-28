package logic;

public class Edificio {
	
	public static final int DefaultCapacidadeMaxima = 50;
	
	protected static int lastID = 0;
	public int ID;
	public String nome;
	protected int ocupantes;
	protected int capacidadeMaxima;
	
	/** Construtor que aplica a capacidade maxima defaul **/
	Edificio(String nome, int ocupantes){
		this.nome = nome;
		this.ocupantes = ocupantes;
		this.capacidadeMaxima = DefaultCapacidadeMaxima;
		ID = lastID++;
	}
	
	/** Construtor que permite definir a capacidade maxima do Edificio **/
	Edificio(String nome, int ocupantes, int capacidadeMaxima){
		this.nome = nome;
		this.ocupantes = ocupantes;
		this.capacidadeMaxima = capacidadeMaxima;
		ID = lastID++;
	}
	
	/** Construtor de c�pia **/
	public Edificio(Edificio e) {
		this.nome = e.nome;
		this.ID = e.ID;
		this.ocupantes = e.getOcupantes();
		this.capacidadeMaxima = e.getCapacidade();
	}
	
	/** Obtem o numero de ocupantes de um edificio **/
	public int getOcupantes(){
		return ocupantes;
	}
	
	/** Obtem a capacidade Maxima deste edificio **/
	public int getCapacidade(){
		return capacidadeMaxima;
	}
	
	/** Obtem o espa�o disponivel na clinica **/
	public int getEspacoDisponivel(){
		return capacidadeMaxima - ocupantes;
	}
	
	/** Retira n ocupantes do edificio **/
	public int retirarOcupantes(int n){
		if( n > ocupantes ){ //nao podem ser retiradas mais pessoas do que aquelas que estao num edificio
			n = ocupantes;
		}
		
		ocupantes -= n;
		
		return n;
	}
	
	/** Retira todos os ocupantes do edificio **/
	public int esvaziaEdificio(){
		return retirarOcupantes(ocupantes);
	}
	
	public String toString(){
		return nome;
	}

}
