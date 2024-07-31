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
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.service.UserService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.util.StringUtil;
import cn.com.xxxx.fmockup.validator.UserValidator;
import cn.com.xxxx.fmockup.validator_order.GroupOrder;
import jakarta.servlet.http.HttpSession;

/**
 * User Add Action
 *   It processes the Web-API POST request from browser.
 */

@RestController
public class UserAddAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private UserService service;

	/**
	 * User Add Action
	 *   It processes the Web-API POST request from browser.
	 */
	@PostMapping("/user/add")
	@ResponseBody
	public StatusResponse response(@Validated(GroupOrder.class) @ModelAttribute UserValidator validator, BindingResult result) throws Exception {

		MessageUtil.setLocale();

		LogUtil.access("Add User started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "User");
		} catch (Exception ex) {
			LogUtil.access("Add User failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Token
		try {
			sessionUtil.checkToken(validator.getToken());
		} catch (Exception ex) {
			LogUtil.access("Add User failed (Token) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Request Values
		if (result.hasErrors()) {
			LogUtil.access("Add User failed (Validation) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
			for (ObjectError error : result.getAllErrors()) {
				String[] keyValue = error.getDefaultMessage().split(":", 2);
				response.addMessageMap(keyValue[0], keyValue[1]);
			}
			return response;
		}

		// Check Remark
		try {
			StringUtil.checkRemarkString(validator.getRemark());
		} catch(Exception ex) {
			LogUtil.access("Add User failed (Validation) : '" + sessionUtil.getUserName() + "'");
			response.addMessageMap("remark", ex.getMessage());
			response.setStatus("action-ng");
			response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
			response.addMessageMap("remark", MessageUtil.getMessage(MessageUtil.USER_ERROR_FORM_ERROR_IN_USER_REMARK_CHARACTER));
			return response;
		}

		// Set Values to Database
		try {
			service.insertUser(validator);
		} catch (Exception ex) {
			LogUtil.access("Add User failed (Service) : '" + sessionUtil.getUserName() + "', '" + validator.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.access("Add User succeed : '" + sessionUtil.getUserName() + "'" + "', '" + validator.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		return response;
	}
}
