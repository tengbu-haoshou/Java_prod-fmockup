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

import java.util.regex.Pattern;

/**
 * Character String Utility
 *   It checks password string and path string.
 */

public class StringUtil {

	/**
	 * Character String Checker
	 *   It Checks Password String Complexity.
	 */

	public static void checkPasswordString(String password) throws Exception {

		// Not Implement String Length Check
		// if (8 > password.length() || password.length() > 32) {
		// new Exception("Invalid Password length.");
		// }

		boolean bigLetter = false;
		boolean smallLetter = false;
		boolean mark = false;
		String matching;
		Pattern pattern;

		// Big Letter
		matching = "[A-Z]";
		pattern = Pattern.compile(matching);
		if (pattern.matcher(password).find()) {
			bigLetter = true;
		}

		// Small Letter
		matching = "[a-z]";
		pattern = Pattern.compile(matching);
		if (pattern.matcher(password).find()) {
			smallLetter = true;
		}

		// Mark
		// 0-9 ! " # $ % & ' ( ) - = ^ ~ \ | @ ` [ { ; + : * ] } , < . > / ? _
		matching = "[0-9\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\-\\=\\^\\~\\\\\\|\\@\\`\\[\\{\\;\\+\\:\\*\\]\\}\\,\\<\\.\\>\\/\\?\\_]";
		pattern = Pattern.compile(matching);
		if (pattern.matcher(password).find()) {
			mark = true;
		}

		if (! bigLetter || ! smallLetter || ! mark) {
			throw new Exception("Invalid Password character.");
		}

		return;
	}

	/**
	 * Character String Utility
	 *   It checks remark string.
	 */
	public static void checkRemarkString(String remark) throws Exception {

		String matching;
		Pattern pattern;

		// 0x00 - 0x1f, 0x7f
		matching = "[\\p{Cntrl}]";
		pattern = Pattern.compile(matching);
		if (pattern.matcher(remark).find()) {
			throw new Exception("Invalid Remark character.");
		}

		return;
	}
}
