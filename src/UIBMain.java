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
			System.out.println("[1] - Abrir Conta");
			System.out.println("[2] - creditar em conta");
			System.out.println("[3] - debitar em conta");
			System.out.println("[4] - consulta saldo");
			System.out.println("[5] - sair");
			
			opcao = leTeclado.nextInt();
			
			switch (opcao) {
			case 1:
				Cliente cliente = montarCliente();
				
				Conta conta = montarConta();
				conta.setCliente(cliente);
				
				contas[totalDeContas] = conta;
				totalDeContas++;
				
				break;
			case 2:
				break;
			default:
				System.out.println("Opção digitada não é valida!");
				break;
			}
		} while (opcao != 7);

		leTeclado.close();
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
		
		System.out.println("Digite o valor do deposito inicial");
		double valor = leTeclado.nextDouble();
		
		Conta conta = new Conta();
		conta.setSaldo(valor);
		conta.setNumero(Conta.gerarNumero());
		
		return conta;
	}
}
