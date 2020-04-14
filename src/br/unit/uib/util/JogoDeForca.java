package br.unit.uib.util;

import java.util.Random;
import java.util.Scanner;

public class JogoDeForca {

	private static final int QUANTIDADE_MAXIMA_ERROS_PERMITIDOS = 3;
	private static final String QUEBRA_LINHA = "\n";
	

	public static void main(String[] args) {
		Scanner leTeclado = new Scanner(System.in);

		String chave = sorteiaChave();
		
		String[] palavraDigitada = new String[chave.length()];

		int quantidadeErros = 0;
		int quantidadeDeLetrasCorretas = 0;

		for (int i = 0; i < chave.length(); i++) {
			palavraDigitada[i] = "-";
		}

		do {
			imprimePalavraDigitada(palavraDigitada);

			System.out.println("Digite a letra");
			String letraLida = leTeclado.next();

			boolean isAcertouLetra = false;
			for (int i = 0; i < chave.length(); i++) {
				String letra = String.valueOf(chave.charAt(i));

				if (letraLida.equalsIgnoreCase(letra)) {
					palavraDigitada[i] = letraLida;
					quantidadeDeLetrasCorretas++;
					isAcertouLetra = true;
				}
			}
			
			if(quantidadeDeLetrasCorretas == chave.length()) {
				System.out.println("Parabens!! Você venceu acentando a senha " + chave);
				break;
			}
			
			if (!isAcertouLetra) {
				quantidadeErros++;
				System.out.println("A letra " + letraLida + " não existe!");

				if (QUANTIDADE_MAXIMA_ERROS_PERMITIDOS != quantidadeErros) {
					System.out.println("Você ainda possui " //
							+ (QUANTIDADE_MAXIMA_ERROS_PERMITIDOS - quantidadeErros) //
							+ " chances!");
				}else {
					System.out.println("Você não acertou. A senha era:" + chave);
				}
			}

		} while (quantidadeErros < QUANTIDADE_MAXIMA_ERROS_PERMITIDOS);
		
		leTeclado.close();
	}

	private static void imprimePalavraDigitada(String[] palavra) {
		for (int i = 0; i < palavra.length; i++) {
			System.out.print(palavra[i] + "  ");
		}
		System.out.print(QUEBRA_LINHA);
	}
	
	private static String sorteiaChave() {
		String[] possibiliades = {"amarelo", "azul", "rosa", "vermelho", "branco", "preto"};
		
		int posicao = getRandomNumberInRange(0, 5);
		return possibiliades[posicao];
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
}
