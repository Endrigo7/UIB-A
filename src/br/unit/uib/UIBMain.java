package br.unit.uib;

import static br.unit.uib.util.Constantes.*;

import java.util.Scanner;

import br.unit.uib.entidades.Cliente;
import br.unit.uib.entidades.Conta;
import br.unit.uib.util.SenhaUtil;

public class UIBMain {

	public static void main(String[] args) {
		Scanner leTeclado = new Scanner(System.in);

		Conta contas[] = new Conta[TOTAL_CONTA];

		int totalDeContas = 0;

		System.out.println("Bem vindo ao Unit Internet Bank");
		System.out.println("-------------------------------");

		int opcao = -1;
		do {
			imprimeMenu();
			opcao = leTeclado.nextInt();

			switch (opcao) {
			case OPCAO_CADASTRAR_CONTA:
				Conta conta = montarConta();
				contas[totalDeContas] = conta;
				totalDeContas++;
				break;
			case OPCAO_CONSULTAR_SALDO:
				Conta contaPesquisada = buscarConta(contas);
				if (contaPesquisada != null) {
					System.out.println("Seu saldo é :" + contaPesquisada.getSaldo());
				}

				break;
			case OPCAO_CREDITAR:
				contaPesquisada = buscarConta(contas);
				if (contaPesquisada != null) {
					creditar(contaPesquisada);
				}

				break;
			case OPCAO_DEBITAR:
				contaPesquisada = buscarConta(contas);
				if (contaPesquisada != null) {
					debitar(contaPesquisada);
				}
				break;
			case OPCAO_TRANSFERIR:
				contaPesquisada = buscarConta(contas);
				if (contaPesquisada != null) {
					transferir(contaPesquisada, contas);
				}
				break;
			case OPCAO_SAIR:
				System.out.println("Obrigado por usar o UIB!");
				break;
			default:
				System.out.println("Opção digitada não é valida!");
				break;
			}
		} while (opcao != OPCAO_SAIR);

		leTeclado.close();
	}
	
	private static void transferir(Conta conta, Conta[] contas) {
		Scanner leTeclado = new Scanner(System.in);
		
		System.out.println("Digite o numero da Conta que recebera o credito");
		String numero = leTeclado.next();
		Conta contaAReceberOCredito = buscarConta(numero, contas);

		if (contaAReceberOCredito == null) {
			System.out.println("Conta de destino nao encontrada");
		} else {
			System.out.println("Digite o valor a ser transferido");
			double valor = leTeclado.nextDouble();
			conta.transferir(contaAReceberOCredito, valor);
		}
	}

	private static void debitar(Conta conta) {
		Scanner leTeclado = new Scanner(System.in);

		System.out.println("Digite o valor do debitar");
		double valor = leTeclado.nextDouble();
		conta.debitar(valor);
	}

	private static void creditar(Conta conta) {
		Scanner leTeclado = new Scanner(System.in);

		System.out.println("Digite o valor do credito");
		double valor = leTeclado.nextDouble();

		conta.creditar(valor);
	}

	private static Conta buscarConta(String numero, Conta[] contas) {
		for (Conta conta : contas) {
			if (conta != null) {
				if (conta.getNumero().equals(numero)) {
					return conta;
				}
			}
		}

		return null;
	}

	private static Conta buscarConta(Conta[] contas) {
		Scanner leTeclado = new Scanner(System.in);

		System.out.println("Por favor digita a sua conta");
		String numero = leTeclado.next();

		System.out.println("Por favor digita a sua senha");
		String senha = leTeclado.next();
		for (Conta conta : contas) {
			if (conta != null) {
				String senhaHash = SenhaUtil.gerarHash(senha);

				if (conta.getNumero().equals(numero) //
						&& conta.getSenha().equals(senhaHash)) {
					return conta;
				}
			}
		}

		System.out.println("Conta não encontrada");
		return null;
	}

	private static void imprimeMenu() {
		System.out.println("[1] - Abrir Conta");
		System.out.println("[2] - consulta saldo");
		System.out.println("[3] - creditar em conta");
		System.out.println("[4] - debitar em conta");
		System.out.println("[5] - Transferir");
		System.out.println("[6] - sair");
	}

	private static Cliente montarCliente() {
		Scanner leTeclado = new Scanner(System.in);

		System.out.println("Digite o Cpf do Cliente");
		String cpf = leTeclado.next();

		System.out.println("Digite o Nome do Cliente");
		String nome = leTeclado.next();

		Cliente cliente = new Cliente(cpf, nome);

		return cliente;
	}

	private static Conta montarConta() {
		Scanner leTeclado = new Scanner(System.in);

		Cliente cliente = montarCliente();

		Conta conta = new Conta();
		conta.setCliente(cliente);

		System.out.println("Digite o valor do deposito inicial");
		double valor = leTeclado.nextDouble();
		conta.setSaldo(valor);

		String numero = Conta.gerarNumero();
		System.out.println("O numero da sua conta eh: " + numero);
		conta.setNumero(numero);

		System.out.println("Digite a senha da conta");
		String senha = leTeclado.next();
		conta.setSenha(senha);

		return conta;
	}
}
