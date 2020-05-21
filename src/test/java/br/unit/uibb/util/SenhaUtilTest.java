package br.unit.uibb.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SenhaUtilTest {

	@Test
	public void x() {
		String retornoEsperado = "f1ab5589a9819ce3d8879de45f9c638b".toUpperCase();
		
		String retornoObtido = SenhaUtil.geraHash("ENDRIGO");
		assertEquals(retornoEsperado, retornoObtido);
		
	}
	
}
