package br.unit.uibb;

import static br.unit.uibb.Constantes.ABRIR_CONTA;
import static br.unit.uibb.Constantes.CONSULTA_SALDO;
import static br.unit.uibb.Constantes.CREDITAR;
import static br.unit.uibb.Constantes.DEBITAR;
import static br.unit.uibb.Constantes.SAIR;
import static br.unit.uibb.Constantes.TRANSFERIR;

import java.util.Scanner;

import br.unit.uibb.entidades.Cliente;
import br.unit.uibb.entidades.Conta;
import br.unit.uibb.repository.RepositorioContasArray;
import br.unit.uibb.util.SenhaUtil;

public class UIBMain {

	private static RepositorioContasArray repositorioContasArray = new RepositorioContasArray();
	private static Scanner leTeclado = new Scanner(System.in);

	public static void main(String[] args) {
		Scanner leTeclado = new Scanner(System.in);

		System.out.println("Bem vindo ao Unit Internet Bank");
		System.out.println("-------------------------------");

		int opcao = -1;
		do {
			imprimeMenu();
			opcao = leTeclado.nextInt();

			switch (opcao) {
			case ABRIR_CONTA:
				Conta conta = montaConta();
				repositorioContasArray.inserir(conta);
				
				System.out.println("O numero da sua conta eh: " + conta.getNumero());
				break;
			case CONSULTA_SALDO:
				conta = buscarConta();
				if (conta != null) {
					System.out.println("seu saldo eh:" + conta.getSaldo());
				}

				break;
			case CREDITAR:
				conta = buscarConta();
				if (conta != null) {
					System.out.println("Digite o valor");
					double valor = leTeclado.nextDouble();

					conta.creditar(valor);
				}

				break;
			case DEBITAR:
				conta = buscarConta();
				if (conta != null) {
					System.out.println("Digite o valor");
					double valor = leTeclado.nextDouble();

					conta.debitar(valor);
				}

				break;
			case TRANSFERIR:
				conta = buscarConta();
				if (conta != null) {
					Conta contaDestino = buscarContaDestino();

					if (contaDestino != null) {
						System.out.println("Digite o valor");
						double valor = leTeclado.nextDouble();

						conta.transferir(contaDestino, valor);
					}
				}

				break;
			case SAIR:
				System.out.println("Obrigado por usa o  Unit Internet Bank");
				System.out.println("--------------------------------------");
				break;
			default:
				System.out.println("Opção invalida! Selecione novamente!");
				break;
			}

		} while (opcao != SAIR);

		leTeclado.close();
	}

	private static void imprimeMenu() {
		System.out.println("[1] - Abrir Conta");
		System.out.println("[2] - consulta saldo");
		System.out.println("[3] - creditar em conta");
		System.out.println("[4] - debitar em conta");
		System.out.println("[5] - Transferir");
		System.out.println("[6] - sair");
	}

	private static Cliente montaCliente() {
		System.out.println("Digite o cpf do Cliente");
		String cpf = leTeclado.next();

		System.out.println("Digite o nome do Cliente");
		String nome = leTeclado.next();

		Cliente cliente = new Cliente(nome, cpf);
		return cliente;
	}

	private static Conta montaConta() {
		Cliente cliente = montaCliente();

		System.out.println("Digite a sua senha");
		String senha = leTeclado.next();
		String senhaHash = SenhaUtil.geraHash(senha);

		System.out.println("Digite valor do deposito inicial");
		double saldoInicial = leTeclado.nextDouble();

		String numero = Conta.gerarNumero();

		Conta conta = new Conta(numero, saldoInicial, cliente, senhaHash);
		return conta;
	}

	private static Conta buscarConta() {
		System.out.println("Digite o numero da Conta");
		String numero = leTeclado.next();

		System.out.println("Digite a sua senha");
		String senha = leTeclado.next();
		String senhaHash = SenhaUtil.geraHash(senha);

		Conta conta = repositorioContasArray.procurar(numero, senhaHash);
		if (conta != null) {
			return conta;
		}

		System.out.println("Conta " + numero + " não encontrada!");
		return null;
	}

	private static Conta buscarContaDestino() {
		System.out.println("Digite o numero da Conta Destino");
		String numero = leTeclado.next();

		Conta conta = repositorioContasArray.procurar(numero);

		if (conta != null) {
			return conta;
		}

		System.out.println("Conta " + numero + " não encontrada!");
		return null;
	}
}
