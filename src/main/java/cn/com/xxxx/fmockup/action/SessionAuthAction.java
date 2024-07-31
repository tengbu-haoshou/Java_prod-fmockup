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

import cn.com.xxxx.fmockup.entity.SessionAuthEntity;
import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.PropertyUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.validator.SessionAuthValidator;
import cn.com.xxxx.fmockup.validator_order.GroupOrder;
import jakarta.servlet.http.HttpSession;

/**
 * Session Auth Action
 *   It processes the Web-API POST request from browser.
 */

@RestController
public class SessionAuthAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService service;

	/**
	 * Session Auth Action
	 *   It processes the Web-API POST request from browser.
	 */
	@PostMapping("/session/auth")
	@ResponseBody
	public StatusResponse response(@Validated(GroupOrder.class) @ModelAttribute SessionAuthValidator validator, BindingResult result) throws Exception {

		MessageUtil.setLocale();

		LogUtil.access("Login started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Drop Session
		session.invalidate();

		// Check Bean Validator
		if (result.hasErrors()) {
			LogUtil.access("Login faild : '" + validator.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
			for (ObjectError error : result.getAllErrors()) {
				String[] keyValue = error.getDefaultMessage().split(":", 2);
				if (keyValue[0].equals("ambiguous")) {
					response.setMessage(keyValue[1]);
				} else {
					response.addMessageMap(keyValue[0], keyValue[1]);
				}
			}
			return response;
		}

		// Check User Exists or Not
		SessionAuthEntity entity;
		try {
			entity = service.searchOneUser(validator);
		} catch (Exception ex) {
			LogUtil.access("Login failed (Service) : '" + validator.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Session Values
		SessionUtil sessionUtil = new SessionUtil(session, entity.getUserId(), validator.getUserName());

		// Check Account Password Is Expired
		if (entity.getDurationDays() > PropertyUtil.getLoginExpireDay()) {
			LogUtil.access("Login succeed (expired) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ok-expired");    // Go to Change Password Screen
			response.setMessage("");
			return response;
		}

		// Check Account Is Locked or Not
		if (entity.getUserLockedF().equals("Y")) {
			LogUtil.access("Login succeed (locked) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ok-expired");    // Go to Change Password Screen
			response.setMessage("");
			return response;
		}

		// Set Response Values
		sessionUtil.setLoggedIn();
		LogUtil.access("Login succeed : '" + sessionUtil.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		return response;
	}
}
