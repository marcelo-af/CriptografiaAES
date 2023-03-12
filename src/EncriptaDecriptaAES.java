import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncriptaDecriptaAES {

	// Variaveis estaticas
	// chaveEncriptacao é como se fosse o secretKey
	static String IV = "AAAAAAAAAAAAAAAA";
	static String textoPuro = "Estudando Criptografia 1234567890\0*0";
	static String chaveEncriptacao = "0123456789abcdef";

	public static void main(String[] args) {

		try {

			// Exibe a frase
			System.out.println("Texto Puro: " + textoPuro);

			byte[] textoEncriptado = encrypt(textoPuro, chaveEncriptacao);

			System.out.println("Texto Criptografado: ");

			for (int i = 0; i < textoEncriptado.length; i++)
				System.out.print(new Integer(textoEncriptado[i]) + " ");

			System.out.println("");

			String textoDecriptado = decrypt(textoEncriptado, chaveEncriptacao);

			System.out.println("Texto Decriptado: " + textoDecriptado);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encrypt(String textoPuro, String chaveEncriptacao) throws Exception {

		// Cria a cifra
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");

		// Informa qual é o tipo de criptografia que a chaveEncriptacao deve ser usada
		SecretKeySpec key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");

		// Inicia a criptografia Cipher.ENCRYPT_MODE
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));

		// Criptografa o texto
		return encripta.doFinal(textoPuro.getBytes("UTF-8"));
	}

	public static String decrypt(byte[] textoEncriptado, String chaveEncriptacao) throws Exception {

		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");

		// Inicia a decriptografia DECRYPT_MODE
		decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(decripta.doFinal(textoEncriptado), "UTF-8");

	}
}
