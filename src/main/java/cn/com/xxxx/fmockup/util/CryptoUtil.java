//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Cripto Utility
 *   It encrypts or decrypts data.
 */

public class CryptoUtil {

	private static String charset = "utf-8";

	/**
	 * Cripto Utility
	 *   It encrypts data.
	 */
	public static String encrypt(String inString, int charLength) throws Exception {

		// Feature TODO : Change from "AES" to "AES/GCM/NoPadding".

		String outString;
		try {
			SecretKeySpec key = new SecretKeySpec(PropertyUtil.getCryptoKey().getBytes(charset), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] in = inString.getBytes(charset);
			byte[] out = cipher.doFinal(in);
			outString = new String(bin2hex(out));
		} catch (Exception e) {
			throw new Exception("Eecrypt trouble has occurred.");
		}

		int length = 16 * ( ( charLength * 6 / 16 ) + 1 ) * 2;

		// Assertion
		if (outString.length() > length) {
			throw new Exception("Assertion : Invalid encrypt length (" + String.valueOf(outString.length()) + " > " + String.valueOf(length) + ")");
		}

		return outString;
	}

	/**
	 * Cripto Utility
	 *   It decrypts data.
	 */
	public static String decrypt(String inString) throws Exception {

		String outString;
		try {
			SecretKeySpec key = new SecretKeySpec(PropertyUtil.getCryptoKey().getBytes(charset), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] in = hex2bin(inString);
			byte[] out = cipher.doFinal(in);
			outString = new String(out, charset);
		} catch (Exception ex) {
				throw new Exception("Decrypt trouble has occurred.");
		}

		return outString;
	}

	// bin to hex
	private static String bin2hex(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for (byte b : data) {
			String s = Integer.toHexString(0xff & b);
			if (s.length() == 1) {
				sb.append("0");
			}
			sb.append(s);
		}
	    return sb.toString();
	}

	// hex to bin
	private static byte[] hex2bin(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		for (int index = 0; index < bytes.length; index++) {
			bytes[index] = (byte) Integer.parseInt(hex.substring(index * 2, (index + 1) * 2), 16);
		}
		return bytes;
	}
}
