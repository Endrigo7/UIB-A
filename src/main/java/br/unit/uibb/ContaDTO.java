package br.unit.uibb;

public class ContaDTO {
	
	private String numero;
	private String senha;
	
	public ContaDTO(String numero, String senha) {
		super();
		this.numero = numero;
		this.senha = senha;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
