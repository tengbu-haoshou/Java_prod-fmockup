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
import cn.com.xxxx.fmockup.validator.UserValidatorForModify;
import cn.com.xxxx.fmockup.validator_order.GroupOrder;
import jakarta.servlet.http.HttpSession;

/**
 * User Modify Action
 *   It processes the Web-API POST request from browser.
 */

@RestController
public class UserModyfiAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private UserService service;

	/**
	 * User Modify Action
	 *   It processes the Web-API POST request from browser.
	 */
	@PostMapping("/user/modify")
	@ResponseBody
	public StatusResponse response(@Validated(GroupOrder.class) @ModelAttribute UserValidatorForModify validator, BindingResult result) {

		MessageUtil.setLocale();

		LogUtil.access("Modify User started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "User");
		} catch (Exception ex) {
			LogUtil.access("Modify User failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Token
		try {
			sessionUtil.checkToken(validator.getToken());
		} catch (Exception ex) {
			LogUtil.access("Modify User failed (Token) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Request Values
		if (result.hasErrors()) {
			LogUtil.access("Modify User failed (Validation) : '" + sessionUtil.getUserName() + "'");
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
			LogUtil.access("Modify User failed (Validation) : '" + sessionUtil.getUserName() + "'");
			response.addMessageMap("remark", ex.getMessage());
			response.setStatus("action-ng");
			response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
			response.addMessageMap("remark", MessageUtil.getMessage(MessageUtil.USER_ERROR_FORM_ERROR_IN_USER_REMARK_CHARACTER));
			return response;
		}

		boolean error = false;

		if (! validator.getNewPassword().equals("") || ! validator.getConfirmPassword().equals("")) {

			// Check New Password String Complexity
			if (validator.getNewPassword().length() < 8) {
				LogUtil.access("Modify User failed (Validation) : '" + sessionUtil.getUserName() + "'");
				response.setStatus("action-ng");
				response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
				response.addMessageMap("newPassword", MessageUtil.getMessage(MessageUtil.USER_ERROR_FORM_ERROR_IN_NEW_PASSWORD_LENGTH));
				error = true;	
			}

			// Check Confirm Password String Complexity
			if (validator.getConfirmPassword().length() < 8) {
				LogUtil.access("Modify User failed (Validation) : '" + sessionUtil.getUserName() + "'");
				response.setStatus("action-ng");
				response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
				response.addMessageMap("confirmPassword", MessageUtil.getMessage(MessageUtil.USER_ERROR_FORM_ERROR_IN_CONFIRM_PASSWORD_LENGTH));
				error = true;
			}

			if (error) {
				return response;
			}
		}

		// Set Values to Database
		try {
			service.updateUser(validator);
		} catch (Exception ex) {
			LogUtil.access("Modify User failed (Service) : '" + sessionUtil.getUserName() + "', '" + validator.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.access("Modify User succeed : '" + sessionUtil.getUserName() + "', '" + validator.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		return response;
	}
}
