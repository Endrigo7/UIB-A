package br.unit.uibb.util;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class SenhaUtil {

	public static String geraHash(String senha) {
		String senhaHash = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes());
			byte[] digest = md.digest();
			
			senhaHash = DatatypeConverter.printHexBinary(digest);
		} catch (Exception e) {
		}

		return senhaHash;
	}

}
