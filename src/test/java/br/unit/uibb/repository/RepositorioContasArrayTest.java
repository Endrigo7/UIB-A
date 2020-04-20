package br.unit.uibb.repository;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.unit.uibb.entidades.Cliente;
import br.unit.uibb.entidades.Conta;

public class RepositorioContasArrayTest {
	
	private RepositorioContasArray repositorioContasArray;
	
	private List<Conta> getContasDummy() {
		Cliente clienteComMaisDe1Conta = new Cliente("Jair", "2");
		
		return asList(
		 		   new Conta("1", 10, new Cliente("Jose", "1"), "abc"), // 
		           new Conta("2", 20, clienteComMaisDe1Conta, "abc"), //
		           new Conta("3", 50, new Cliente("Maria", "3"), "abc"), //
		           new Conta("4", 11, new Cliente("Joaquina", "4"), "abc"), //
		           new Conta("5", 10, clienteComMaisDe1Conta, "abc") //
				 );
	}
	
	@Before
	public void setup() {
		repositorioContasArray = new RepositorioContasArray();
		getContasDummy().forEach(conta -> repositorioContasArray.inserir(conta));
	}
	
	@Test
	public void deveRemoverContaQuandoContaExistirNoArray() {
		repositorioContasArray.remover("2", "abc");
		
		assertTrue(repositorioContasArray.existe("1"));
		assertFalse(repositorioContasArray.existe("2"));
	}
	
	@Test
	public void deveAtualizarContaQuandoContaExistirNoArray() {
		Conta contaAtualizada = new Conta("2", 20, new Cliente("Jair Jose", "2"), "abc");
		repositorioContasArray.atualizar(contaAtualizada);
		
		Conta contaRetornada = repositorioContasArray.procurar("2");
		assertEquals("Jair Jose", contaRetornada.getCliente().getNome());
	}
	
	@Test
	public void deveRetornarTodasAsContasDoClienteQuandoContaExistirNoArray() {
		List<Conta> retornoEsperado = asList(
		           new Conta("2", 20, new Cliente("Jair", "2"), "abc"), //
		           new Conta("5", 10, new Cliente("Jair", "2"), "abc") //
				 );
		
		List<Conta> retornoObtido = repositorioContasArray.procurarContas("2");
		
		assertEquals(retornoEsperado, retornoObtido);
	}
	
	@Test
	public void deveRetornarTodasAsContasDoClienteQuandoContaExistirNoArrayComJava8() {
		List<Conta> retornoEsperado = asList(
		           new Conta("2", 20, new Cliente("Jair", "2"), "abc"), //
		           new Conta("5", 10, new Cliente("Jair", "2"), "abc") //
				 );
		
		List<Conta> retornoObtido = repositorioContasArray.procurarContasJava8("2");
		
		assertEquals(retornoEsperado, retornoObtido);
	}
	
}
