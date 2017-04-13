package lab11;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * This is a bit of code I've scraped together from a couple of tutorials on
 * Java cryptography. I am by no means a crypto expert, NOR SHOULD THIS CODE
 * BE THOUGHT OF AS AN EXAMPLE OF GOOD ENCRYPTION/DECRYPTION. I just thought it
 * was a good bit of fun, and a few of you might enjoy it.
 * 
 * I'm using the AES cypher for my encryption.
 * @author ryans
 *
 */
public class Decoder {
	private static final int KEY_LENGTH = 128;
	private static final int ITERATION_COUNT = 65536;
	
	File directory;
	private String password;
	
	public Decoder(String file, String password) {
		directory = new File(file);
		this.password = password;
		if(!directory.exists()) {
			throw new LabElevenException("File not found: " + file);
		}
		if(!directory.isDirectory()) {
			throw new LabElevenException("The specified path must be a directory.");
		}
	}
	
	/**
	 * Runs the necessary steps to decode the students passwd.enc, with the proper salt.byte
	 */
	public void decode() {
		String cryptText = readFile();
		Spinner.slash("Reading file ");
		System.out.println();
		System.out.println("Encrypted text is: " + cryptText);
		Spinner.dots("Decrypting File");
		System.out.println();
		String plainText = decipher(cryptText);
		System.out.println("Your password is: ");
		System.out.println(plainText);
	}
	
	private String decipher(String cryptText) {
		//I encode the message in Base64 to make it more printable...so we gotta reverse that!
		byte[] cryptBytes = Base64.getDecoder().decode(cryptText);
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// These can be in clear text! The salts were all randomly generated..yours is different from everyone else's!
			byte[] randomBytes = getSaltAndInitVectorFromFile();
			byte[] iv = Arrays.copyOfRange(randomBytes, 0, 16); // first 16 bytes
			byte[] salt = Arrays.copyOfRange(randomBytes, 16, 24); // last 8 bytes
			
			SecretKey key = buildSecretyKey(salt);
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			return new String(cipher.doFinal(cryptBytes), "UTF-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
				InvalidKeyException | InvalidAlgorithmParameterException |
				UnsupportedEncodingException | IllegalBlockSizeException | 
				BadPaddingException e) {
			e.printStackTrace();
		}
		return "";
	}

	//One way of reading files is to use the new Files class in Java 8. 
	private byte[] getSaltAndInitVectorFromFile() {
		try {
			// I recommend using Paths to create valid file paths from multiple strings. 
			// This is a nice, system agnostic way to do things. ( Meaning it will work on windows or unix);
			Path saltPath = Paths.get(directory.getAbsolutePath(), "rand.byte");
			return Files.readAllBytes(saltPath);
		} catch (IOException e) {
			throw new LabElevenException(e);
		}
	}

	/*
	 * This is not a cryptography lab, so I'm not going to explain this code that much.
	 * Basically, all a cyphers work the same way:
	 * 
	 *  plain text --> [Cypher Algorithm] --> cypher text
	 *                      |
	 *                     key 
	 *                     
	 *  this code creates a valid key from your password and the salt.
	 *   It takes your password, hashes it with SHA-256  (plus whatever 
	 *   padding is necessary) ITERATION_COUNT number of times, then takes
	 *   the first KEY_LENGTH number of bytes. 
	 */
	private SecretKey buildSecretyKey(byte[] salt) {
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
			return secret;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
		}
		return null;
	}

	/*
	 * Another example of reading a file. 
	 */
	private String readFile() {
		try {
			StringBuilder sb = new StringBuilder();
			for (String line : Files.readAllLines(Paths.get(directory.getAbsolutePath(), "passwd.enc"))) {
				sb.append(line);
			};
			return sb.toString();
		} catch (IOException e) {
			throw new LabElevenException(e);
		}
	}
}
