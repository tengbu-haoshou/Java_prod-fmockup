//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.xxxx.fmockup.response.TokenResponse;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import jakarta.servlet.http.HttpSession;

/**
 * Session Valid Action
 *   It processes the Web-API GET request from browser.
 */

@RestController
public class SessionValidAction<JsnResponseForToken> {

	@Autowired
	private HttpSession session;

	/**
	 * Session Valid Action
	 *   It processes the GET request from browser.
	 */
	@GetMapping("/session/valid")
	@ResponseBody
	public TokenResponse response() throws Exception {

		MessageUtil.setLocale();

		LogUtil.access("Valid started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		TokenResponse response = new TokenResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, false);
		} catch (Exception ex) {
			LogUtil.access("Valid failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Screen Values
		LogUtil.access("Valid succeed : '" + sessionUtil.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		return response;
	}
}
