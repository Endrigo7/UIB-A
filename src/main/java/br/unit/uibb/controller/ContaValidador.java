package br.unit.uibb.controller;

import br.unit.uibb.entidades.Conta;
import br.unit.uibb.repository.ContaDAOHibernate;
import br.unit.uibb.util.CPFValidador;

public class ContaValidador {
	
	private ContaDAOHibernate contaDao;
	
	public ContaValidador() {
		contaDao = new ContaDAOHibernate();
	}
	
	private boolean validaCliente(Conta conta) {
		if (conta.getCliente() == null) {
			System.out.println("A conta não possui cliente associado!");
			return false;
		}

		if ((conta.getCliente().getCpf() == null) || //
				(!CPFValidador.isValidCPF(conta.getCliente().getCpf()))) {
			System.out.println("O CPF invalido!");
			return false;
		}
		
		return true;
	}
	
	public boolean validaContaInserir(Conta conta) {
		if (contaDao.existe(conta.getNumero())) {
			System.out.println("A conta com este numero já existe!");
			return false;
		}
		
		return validaCliente(conta);
	}
	
	public boolean validaContaAlterar(Conta conta) {
		if (!contaDao.existe(conta.getNumero())) {
			System.out.println("A conta não está cadastrada!");
			return false;
		}
		
		return validaCliente(conta);
	}
	
	public boolean validaTransacao(String numero, String senha, double valor) {
		if (valor < 0.1) {
			System.out.println("Valor invalido");
			return false;
		} else {
			Conta conta = contaDao.procurar(numero, senha);
			if (conta == null) {
				System.out.println("Conta nao existe!");
				return false;
			}
		}
		return true;
	}
}
