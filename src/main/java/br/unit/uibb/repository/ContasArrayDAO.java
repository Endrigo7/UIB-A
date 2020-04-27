package br.unit.uibb.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.unit.uibb.entidades.Conta;

public class ContasArrayDAO {

	private static final int CONTA_NAO_ENCONTRADA = -1;
	private static final int TOTAL_CONTAS = 10;

	public Conta[] contas = new Conta[TOTAL_CONTAS];
	private int posicao;

	public ContasArrayDAO() {
		posicao = 0;
	}

	private int procurarIndice(String numero, String senha) {
		for (int i = 0; i < posicao; i++) {
			if (contas[i].getNumero().equals(numero) //
					&& contas[i].getSenha().equals(senha)) {
				return i;
			}
		}

		return CONTA_NAO_ENCONTRADA;
	}

	private int procurarIndice(String numero) {
		for (int i = 0; i < posicao; i++) {
			if (contas[i].getNumero().equals(numero)) {
				return i;
			}
		}

		return CONTA_NAO_ENCONTRADA;
	}

	public Conta procurar(String numero, String senha) {
		int posicaoConta = procurarIndice(numero, senha);

		if (posicaoConta != CONTA_NAO_ENCONTRADA) {
			return contas[posicaoConta];
		}

		return null;
	}

	public Conta procurar(String numero) {
		int posicaoConta = procurarIndice(numero);

		if (posicaoConta != CONTA_NAO_ENCONTRADA) {
			return contas[posicaoConta];
		}

		return null;
	}

	public boolean existe(String numero) {
		if (procurarIndice(numero) != CONTA_NAO_ENCONTRADA) {
			return true;
		}

		return false;
	}

	public void inserir(Conta novaConta) {
		contas[posicao] = novaConta;
		posicao++;
	}

	public void remover(String numero, String senha) {
		int posicaoConta = procurarIndice(numero, senha);

		for (int i = posicaoConta; i < posicao; i++) {
			contas[i] = contas[i + 1];
		}

		posicao--;
	}

	public void atualizar(Conta conta) {
		int posicaoConta = procurarIndice(conta.getNumero(), conta.getSenha());
		contas[posicaoConta] = conta;
	}

	public List<Conta> procurarContas(String cpf) {
		List<Conta> contasDoCliente = new ArrayList<>();

		for (Conta conta : contas) {
			if ((conta != null) && //
					(conta.getCliente() != null) && //
					(cpf.equals(conta.getCliente().getCpf()))) {
				contasDoCliente.add(conta);
			}
		}

		return contasDoCliente;
	}

	public List<Conta> procurarContasJava8(String cpf) {
		List<Conta> contasDoCliente = Arrays //
				.asList(contas) //
				.stream() //
				.filter(conta -> (conta != null) && //
						(conta.getCliente() != null) && //
						(cpf.equals(conta.getCliente().getCpf()))) //
				.collect(Collectors.toList());
		return contasDoCliente;
	}
}
