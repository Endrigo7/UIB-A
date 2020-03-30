import java.security.MessageDigest;

public class SenhaUtil {

	public static String gerarHash(String senha) {
		String senhaHash = null;
		try {
			byte[] bytesOfMessage = senha.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);

			senhaHash = new String(thedigest);
			System.out.println("senha: " + senha);
			System.out.println("senhaHash: " + senhaHash);
		} catch (Exception e) {
		}
		
		return senhaHash;
	}

}
