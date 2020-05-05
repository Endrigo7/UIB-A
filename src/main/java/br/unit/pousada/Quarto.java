package br.unit.pousada;

public class Quarto {
	private int numero;
	private boolean temBanheiro;
	private Estudante estudante;
	
	public Quarto(int numero, boolean temBanheiro, Estudante estudante) {
		this.numero = numero;
		this.temBanheiro = temBanheiro;
		this.estudante = estudante;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public boolean isTemBanheiro() {
		return temBanheiro;
	}
	
	public void setTemBanheiro(boolean temBanheiro) {
		this.temBanheiro = temBanheiro;
	}
	
	public Estudante getEstudante() {
		return estudante;
	}
	
	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}
	
	public String toString() {
		return numero + ":" 
				+ " com banheiro:" + (temBanheiro == true ? " sim " : " não")  
				+ " -> estudante:" + estudante;
	}
}
