package br.unit.uibb.entidades;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "CONTA")
@NoArgsConstructor
@AllArgsConstructor
public class Conta implements Serializable {
	
	private static final long serialVersionUID = -6319719293259400617L;
	
	@Id
	@Column(name = "NUMERO")
	private String numero;
	
	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name="CPF")
	private Cliente cliente;
	
	@Column(name = "SALDO")
	private Double saldo;
	
	@Column(name = "SENHA")
	private String senha;

	public void creditar(double valor) {
		saldo += valor;
	}

	public void debitar(double valor) {
		saldo -= valor;
	}

	public void transferir(Conta contaDestino, double valor) {
		debitar(valor);
		contaDestino.creditar(valor);
	}

	public static String gerarNumero() {
		return UUID.randomUUID().toString();
	}
}
