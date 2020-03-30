import java.util.Scanner;

public class UIBMain {

	private static final int TOTAL_CONTA = 2;

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
			case 1:
				Conta conta = montarConta();
				contas[totalDeContas] = conta;
				totalDeContas++;
				break;
			case 2:
				Conta contaPesquisa = buscarConta(contas);
				if (contaPesquisa == null) {
					System.out.println("Conta não encontrada");
				} else {
					System.out.println("Seu saldo é :" + contaPesquisa.getSaldo());
				}

				break;
			default:
				System.out.println("Opção digitada não é valida!");
				break;
			}
		} while (opcao != 7);

		leTeclado.close();
	}

	private static Conta buscarConta(Conta[] contas) {
		Scanner leTeclado = new Scanner(System.in);

		System.out.println("Por favor digita a sua conta");
		String numero = leTeclado.next();

		System.out.println("Por favor digita a sua senha");
		String senha = leTeclado.next();
		for (Conta conta : contas) {
			if(conta != null) {
				String senhaHash = SenhaUtil.gerarHash(senha);

				if (conta.getNumero().equals(numero) //
						&& conta.getSenha().equals(senhaHash)) {
					return conta;
				}
			}
		}

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

		Conta conta = new Conta();
		Cliente cliente = montarCliente();
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
