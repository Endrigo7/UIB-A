package br.unit.uib.entidades;
import java.util.UUID;

import br.unit.uib.util.SenhaUtil;

public class Conta {

	private String numero;
	private double saldo;
	private Cliente cliente;
	private String senha;
	
	public Conta() {
	}
	
	public Conta(String numero, double saldo, Cliente cliente) {
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = SenhaUtil.gerarHash(senha);
	}
	
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
	
	public String toString() {
		return "nome: " + cliente.getNome() //
		 			+ " numero: " + numero //
		 			+ " saldo: " + saldo;
	}
}
