package br.unit.uibb;

import static br.unit.uibb.Constantes.ABRIR_CONTA;
import static br.unit.uibb.Constantes.CONSULTA_SALDO;
import static br.unit.uibb.Constantes.CREDITAR;
import static br.unit.uibb.Constantes.DEBITAR;
import static br.unit.uibb.Constantes.LISTA_TODAS_AS_CONTAS_CLIENTE;
import static br.unit.uibb.Constantes.SAIR;
import static br.unit.uibb.Constantes.TRANSFERIR;

import java.util.List;
import java.util.Scanner;

import br.unit.uibb.controller.ContaController;
import br.unit.uibb.entidades.Cliente;
import br.unit.uibb.entidades.Conta;

public class UIBMain {

	private static ContaController contaController = new ContaController();
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
				boolean ok = contaController.inserir(conta);

				if (ok) {
					System.out.println("O numero da sua conta eh: " + conta.getNumero());
				}

				break;
			case CONSULTA_SALDO:
				ContaDTO contaDTO = transacaoComNumeroESenha();

				Double saldo = contaController.consultarSaldo(contaDTO.getNumero(), contaDTO.getSenha());

				if (saldo != null) {
					System.out.println("Seu saldo é: " + saldo);
				} else {
					System.out.println("Conta náo encontrada!");
				}

				break;
			case CREDITAR:
				contaDTO = transacaoComNumeroESenha();

				System.out.println("Digite o valor");
				double valor = leTeclado.nextDouble();

				contaController.creditar(contaDTO.getNumero(), contaDTO.getSenha(), valor);
				break;
			case DEBITAR:
				contaDTO = transacaoComNumeroESenha();

				System.out.println("Digite o valor");
				valor = leTeclado.nextDouble();

				contaController.debitar(contaDTO.getNumero(), contaDTO.getSenha(), valor);
				break;
			case TRANSFERIR:
				contaDTO = transacaoComNumeroESenha();

				System.out.println("Digite o numero da Conta Destino");
				String destino = leTeclado.next();

				System.out.println("Digite o valor");
				valor = leTeclado.nextDouble();

				contaController.transferir(contaDTO.getNumero(), contaDTO.getSenha(), destino, valor);

				break;
			case LISTA_TODAS_AS_CONTAS_CLIENTE:
				System.out.println("Digite o seu CPF");
				String cpf = leTeclado.next();

				List<Conta> contas = contaController.procurarContas(cpf);
				// contas.forEach(System.out::println); //java8 +
				
				for (Conta conta2 : contas) {
					System.out.println(conta2);
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
		System.out.println("[6] - Lista todas as contas do cliente");
		System.out.println("[7] - sair");
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

		System.out.println("Digite valor do deposito inicial");
		double saldoInicial = leTeclado.nextDouble();

		Conta conta = new Conta(null, saldoInicial, cliente, senha);
		return conta;
	}

	private static ContaDTO transacaoComNumeroESenha() {
		System.out.println("Digite o numero da Conta");
		String numero = leTeclado.next();

		System.out.println("Digite a sua senha");
		String senha = leTeclado.next();

		ContaDTO contaDTO = new ContaDTO(numero, senha);
		return contaDTO;
	}
}
