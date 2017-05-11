package com.tigerj;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.Before;
import org.junit.Test;

public class DefaultEncryptionAlgoTests {

	private List<MyCipher> myCiphers = new ArrayList<MyCipher>();

	private String plainText = "Sticks and stones may break my bones but coding makes me joyous";

	private class MyCipher {
		public String cipherName;
		public String[] algorithms;
		public SecretKey secretKey;
	}

	@Before
	public void generateKeys() throws Exception {
		// AES
		MyCipher c = new MyCipher();
		c.cipherName = "AES";
		c.algorithms = new String[] { "AES/ECB/PKCS5Padding", "AES/ECB/NoPadding", "AES/CBC/NoPadding",
				"AES/CBC/PKCS5Padding" };
		KeyGenerator kgen = KeyGenerator.getInstance(c.cipherName);
		kgen.init(128);
		c.secretKey = kgen.generateKey();
		myCiphers.add(c);

		// DES
//		c = new MyCipher();
//		c.cipherName = "DES";
//		c.algorithms = new String[] { "DES/CBC/NoPadding", "DES/CBC/PKCS5Padding", "DES/ECB/NoPadding",
//				"DES/ECB/PKCS5Padding" };
//		kgen = KeyGenerator.getInstance(c.cipherName);
//		kgen.init(56);
//		c.secretKey = kgen.generateKey();
//		myCiphers.add(c);

		// DESede (or Triple DES)
//		c = new MyCipher();
//		c.cipherName = "DESede";
//		c.algorithms = new String[] { "DESede/CBC/NoPadding", "DESede/CBC/PKCS5Padding", "DESede/ECB/NoPadding",
//				"DESede/ECB/PKCS5Padding" };
//		kgen = KeyGenerator.getInstance(c.cipherName);
//		kgen.init(168);
//		c.secretKey = kgen.generateKey();
//		myCiphers.add(c);
	}

	@Test
	public void testSecurityProvider() throws Exception {
		for (Provider provider : Security.getProviders()) {
			System.out.println(provider.getName());
			for (String key : provider.stringPropertyNames()) {
				System.out.println("\t" + key + "\t" + provider.getProperty(key));
			}
		}
	}

	@Test
	public void testAlgorithms() throws Exception {
		for (MyCipher c : myCiphers) {
			// Default algorithm
			Cipher cipher = Cipher.getInstance(c.cipherName);
			cipher.init(Cipher.ENCRYPT_MODE, c.secretKey);
			byte[] cipherText = cipher.doFinal(plainText.getBytes());
			String defaultAlgoEncryptedHex = Hex.encodeHexString(cipherText);

			// Possible algorithms
			for (String a : c.algorithms) {
				try {
					cipher = Cipher.getInstance(a);
					cipher.init(Cipher.ENCRYPT_MODE, c.secretKey);
					cipherText = cipher.doFinal(plainText.getBytes());

					String encryptedHex = Hex.encodeHexString(cipherText);

					System.out.println(a + ": " + defaultAlgoEncryptedHex.equals(encryptedHex));
				} catch (Exception e) {
					System.out.println(a + ": " + e.getMessage());
				}
			}
			System.out.println("==========================================================");
		}
	}

}
