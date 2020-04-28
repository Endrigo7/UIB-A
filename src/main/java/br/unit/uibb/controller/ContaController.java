package br.unit.uibb.controller;

import java.util.List;

import br.unit.uibb.entidades.Conta;
import br.unit.uibb.repository.ContasArrayDAO;
import br.unit.uibb.util.SenhaUtil;

public class ContaController {

	private ContasArrayDAO contaDao;
	private ContaValidador contaValidador;

	public ContaController() {
		contaDao = new ContasArrayDAO();
		contaValidador = new ContaValidador();
	}

	public boolean inserir(Conta conta) {
		String numero = Conta.gerarNumero();
		conta.setNumero(numero);
		
		if (contaValidador.validaContaInserir(conta, contaDao)) {
			
			String senhaHash = SenhaUtil.geraHash(conta.getSenha());
			conta.setSenha(senhaHash);
			
			contaDao.inserir(conta);
			
			return true;
		}
		return false;
	}

	public void atualizar(Conta conta) {
		if (contaValidador.validaContaAlterar(conta, contaDao)) {
			contaDao.atualizar(conta);
		}
	}

	public Conta procurar(String numero) {
		return contaDao.procurar(numero);
	}
	
	public Conta procurar(String numero, String senha) {
		String senhaHash = SenhaUtil.geraHash(senha);
		return contaDao.procurar(numero, senhaHash);
	}

	public void remover(String numero, String senha) {
		String senhaHash = SenhaUtil.geraHash(senha);
		contaDao.remover(numero, senhaHash);
	}

	public List<Conta> procurarContas(String cpf) {
		return contaDao.procurarContas(cpf);
	}

	public void debitar(String numero, String senha, double valor) {
		String senhaHash = SenhaUtil.geraHash(senha);
		
		if (contaValidador.validaTransacao(numero, senhaHash, valor, contaDao)) {
			Conta conta = contaDao.procurar(numero, senhaHash);
			conta.debitar(valor);
		}
	}

	public void creditar(String numero, String senha, double valor) {
		String senhaHash = SenhaUtil.geraHash(senha);
		
		if (contaValidador.validaTransacao(numero, senhaHash, valor, contaDao)) {
			Conta conta = contaDao.procurar(numero, senhaHash);
			conta.creditar(valor);
		}
	}

	public void transferir(String origem, String senhaOrigem, String destino, double valor) {
		String senhaHash = SenhaUtil.geraHash(senhaOrigem);
		
		if (contaValidador.validaTransacao(origem, senhaHash, valor, contaDao)) {
			Conta contaOrigem = contaDao.procurar(origem, senhaHash);
			
			Conta contaDestino = procurar(destino);
			if(contaDestino == null) {
				System.out.println("A conta destino nao existe!");
			}else {
				contaOrigem.transferir(contaDestino, valor);
			}
		}
	}
	
	public Double consultarSaldo(String numero, String senha) {
		Conta conta = procurar(numero, senha);
		
		if(conta != null) {
			return conta.getSaldo();
		}
		
		return null;
	}

}
