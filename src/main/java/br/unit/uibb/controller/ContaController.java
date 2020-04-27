package br.unit.uibb.controller;

import java.util.List;

import br.unit.uibb.entidades.Conta;
import br.unit.uibb.repository.ContasArrayDAO;

public class ContaController {

	private ContasArrayDAO contaDao;
	private ContaValidador contaValidador;

	public ContaController() {
		contaDao = new ContasArrayDAO();
		contaValidador = new ContaValidador();
	}

	public void inserir(Conta conta) {
		if (contaValidador.validaContaInserir(conta, contaDao)) {
			contaDao.inserir(conta);
		}
	}

	public void atualizar(Conta conta) {
		if (contaValidador.validaContaAlterar(conta, contaDao)) {
			contaDao.atualizar(conta);
		}
	}

	public Conta procurar(String numero) {
		return contaDao.procurar(numero);
	}

	public void remover(String numero, String senha) {
		contaDao.remover(numero, senha);
	}

	public List<Conta> procurarContas(String cpf) {
		return contaDao.procurarContas(cpf);
	}

	public void debitar(String numero, String senha, double valor) {
		if (contaValidador.validaTransacao(numero, senha, valor, contaDao)) {
			Conta conta = contaDao.procurar(numero, senha);
			conta.debitar(valor);
		}
	}

	public void creditar(String numero, String senha, double valor) {
		if (contaValidador.validaTransacao(numero, senha, valor, contaDao)) {
			Conta conta = contaDao.procurar(numero, senha);
			conta.creditar(valor);
		}
	}

	public void transferir(String origem, String destino, double valor) {

	}

}
