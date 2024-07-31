//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.xxxx.fmockup.response.ListResponse;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import jakarta.servlet.http.HttpSession;

/**
 * Session Unauth Action
 *   It processes the Web-API POST request.
 */

@RestController
public class SessionUnauthAction {

	@Autowired
	private HttpSession session;

	/**
	 * Session Unauth Action
	 *   It processes the Web-API POST request.
	 */
	@PostMapping("/session/unauth")
	@ResponseBody
	public ListResponse response() throws Exception {

		MessageUtil.setLocale();

		LogUtil.access("Logout started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		ListResponse response = new ListResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, false);
		} catch (Exception ex) {
			LogUtil.access("Logout succeed (already logout)");
			response.setStatus("action-ok");
			response.setMessage("");
			return response;
		}

		// Drop Session
		session.invalidate();

		// Set Response Values
		response.setStatus("action-ok");
		response.setMessage("");
		LogUtil.access("Logout succeed : '" + sessionUtil.getUserName() + "'");
		return response;
	}
}
