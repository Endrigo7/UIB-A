package br.unit.pousada;

public class Estudante {
	
	private String nome;
	private String email;

	public Estudante() {
		nome = "desconhecido";
	}

	public Estudante(String nome, String email, int numeroQuarto) {
		this.nome = (nome == null ? "desconhecido" : nome);
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.nome = email;
	}

	public String toString() {
		return  nome + "," + email;
	}

}
