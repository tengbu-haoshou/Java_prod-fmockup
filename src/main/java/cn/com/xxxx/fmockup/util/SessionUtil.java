//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.util;

import java.security.SecureRandom;

import jakarta.servlet.http.HttpSession;

/**
 * Session Utility
 *   It gets session and checks session contents.
 */

public class SessionUtil {

	private HttpSession session;
	private String userId;
	private String userName;

	/**
	 * Session Utility
	 *  for Login Aciton
	 */
	public SessionUtil(HttpSession session, String userId, String userName) {

		// Set Session Values
		session.setAttribute("fmockup", "<< fmockup >> Mock-up for Business System Demonstration and Development Templates");
		session.setAttribute("userId", userId);
		session.setAttribute("userName", userName);
		session.setAttribute("token", "");
		session.setAttribute("loggedIn", "false");
		session.setMaxInactiveInterval(PropertyUtil.getIdleTimeout() * 60);    // idle timeout (minutes * seconds)

		this.session = session;
		this.userId = userId;
		this.userName = userName;
		return;
	}

	/**
	 * Session Utility
	 */
	public SessionUtil(HttpSession session, Boolean loggedIn_) throws Exception {

		// Check Session
		if (session == null || session.getAttribute("fmockup") == null) {
			throw new Exception(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_SESSION_TIMEOUT_HAS_OCCURRED));
		}

		// Check Session
		String fmockup = (String) session.getAttribute("fmockup");
		if (! fmockup.equals("<< fmockup >> Mock-up for Business System Demonstration and Development Templates")) {
			throw new Exception(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_SESSION_TIMEOUT_HAS_OCCURRED));
		}

		// Check Logged In or Not
		if (loggedIn_) {
			String loggedIn = (String) session.getAttribute("loggedIn");
			if (! loggedIn.equals("true")) {
				throw new Exception(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_SESSION_TIMEOUT_HAS_OCCURRED));
			}
		}

		// Get Session Values
		this.session = session;
		this.userId = (String) session.getAttribute("userId");
		this.userName = (String) session.getAttribute("userName");

		return;
	}

	public void setLoggedIn() {

		// Set Session Values
		session.setAttribute("loggedIn", "true");

		return;
	}

	public String getUserId() {
		return this.userId;
	}

	public String getUserName() {
		return this.userName;
	}

	private static int TOKEN_LENGTH = 16;

	/**
	 * Session Utility
	 *   Set token to session. (for POST screen)
	 */
	public String setToken() throws Exception {

		byte[] token = new byte[TOKEN_LENGTH];
		StringBuffer buf = new StringBuffer();
		SecureRandom random = null;

		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(token);
			for (int i = 0; i < token.length; i++) {
				buf.append(String.format("%02x", token[i]));
			}
		} catch (Exception ex) {
			LogUtil.info("setToken : '" + ex.getMessage() + "'");
			throw new Exception("Unknown error has occurerd.");
		}

		String sessionToken = buf.toString();
		this.session.setAttribute("token", sessionToken);
		return sessionToken;
	}

	/**
	 * Session Utility
	 *   Check token with session. (for POST Web-API)
	 */
	public void checkToken(String token) throws Exception {

		String sessionToken = (String) this.session.getAttribute("token");
		if (! token.equals(sessionToken)) {
			LogUtil.access("checkToken : 'Invalid Token is appeared in Web-API request.'");	// A true problem explanation
			throw new Exception("Unknown error has occurerd.");								// A blurred explanation
		}
		return;
	}
}
