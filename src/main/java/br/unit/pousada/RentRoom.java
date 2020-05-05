package br.unit.pousada;
import java.util.Scanner;

public class RentRoom {
	
	public static void main(String[] args) {
		
		Scanner leTeclado = new Scanner(System.in);

		Quarto[] quartos = new Quarto[10];
		for (int i = 0; i < quartos.length; i++) {
			quartos[i] = new Quarto(i, (i % 2 == 0 ? true : false), null); 
		}

		while (true) {
			System.out.println("Digite a quantidade de estudantes");
			int totalEstudantes = leTeclado.nextInt();

			if (totalEstudantes >= 1 && totalEstudantes <= 10) {
				for (int i = 0; i < totalEstudantes; i++) {
					System.out.println("Digite o nome do Estudante");
					leTeclado.next();
					String nome = leTeclado.nextLine();

					String email = "";
					while(true) {
						System.out.println("Digite o e-mail do Estudante");
						email = leTeclado.next();
						
						if(email.indexOf("@") == -1 | email.indexOf(".") == -1){
							System.out.println("E-mail invalido!");
						}else {
							break;
						}
						
					}
					
					int numeroQuarto = -1;
					while(true) {
						System.out.println("Digite o numero do quarto desejado");
						numeroQuarto = leTeclado.nextInt();
						
						if(quartos[numeroQuarto].getEstudante() != null) {
							System.out.println("Quarto ocupado!");
						}else {
							break;
						}
					}
					
					Estudante estudante = new Estudante(nome, email, numeroQuarto);
					quartos[numeroQuarto].setEstudante(estudante);
				}

				break;
			} else {
				System.out.println("Digite um valor entre 1 e 10");
			}
		}

		for (int i = 0; i < quartos.length; i++) {
			if (quartos[i].getEstudante() != null) {
				System.out.println(i + ":" + quartos[i]);
			}
		}
		
		leTeclado.close();
	}

}
