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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.SessionEnabledService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.validator.SessionEnabledValidator;
import cn.com.xxxx.fmockup.validator_order.GroupOrder;
import jakarta.servlet.http.HttpSession;

/**
 * Session Enabled Action
 *   It processes the Web-API POST request from browser.
 */

@RestController
public class SessionEnabledAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionEnabledService service;

	/**
	 * Session Enabled Action
	 *   It processes the Web-API POST request from browser.
	 */
	@PostMapping("/session/enabled")
	@ResponseBody
	public StatusResponse response(@Validated(GroupOrder.class) @ModelAttribute SessionEnabledValidator validator, BindingResult result) throws Exception {

		MessageUtil.setLocale();

		LogUtil.access("Enable User started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, false);
		} catch (Exception ex) {
			LogUtil.access("Enable User failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Token
		try {
			sessionUtil.checkToken(validator.getToken());
		} catch (Exception ex) {
			LogUtil.access("Enable User failed (Token) : '" + sessionUtil.getUserId() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Request Values
		if (result.hasErrors()) {
			LogUtil.access("Enable User failed (Validation) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
			for (ObjectError error : result.getAllErrors()) {
				String[] keyValue = error.getDefaultMessage().split(":", 2);
				response.addMessageMap(keyValue[0], keyValue[1]);
			}
			return response;
		}

		// Prepare Values
		validator.setUserId(sessionUtil.getUserId());

		// Set Values to Database
		try {
			service.updateUser(session, validator);
		} catch (Exception ex) {
			LogUtil.access("Enable User failed (Service) : '" + sessionUtil.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		sessionUtil.setLoggedIn();
		LogUtil.access("Enable User succeed : '" + sessionUtil.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		return response;
	}
}
