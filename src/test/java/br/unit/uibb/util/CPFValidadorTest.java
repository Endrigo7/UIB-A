package br.unit.uibb.util;

import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Test;

public class CPFValidadorTest {

	@Test
	public void deveRetornarFalsoQuandoCPFMenorQue11Digitos() {
		assertFalse(CPFValidador.isValidCPF("123"));
	}

	@Test
	public void deveRetornarFalsoQuandoCPFInvalido() {
		assertFalse(CPFValidador.isValidCPF("12312312312"));
	}

	@Test
	public void deveRetornarTrueQuandoCPFValido() {
		Assert.assertTrue(CPFValidador.isValidCPF("52051755086"));
	}

}
