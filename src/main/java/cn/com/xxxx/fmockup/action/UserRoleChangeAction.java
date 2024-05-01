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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.service.UserRoleService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.validator.UserRoleValidator;
import cn.com.xxxx.fmockup.validator_order.GroupOrder;
import jakarta.servlet.http.HttpSession;

/**
 * User Role Assign/Unassign Action
 *   It processes the Web-API POST request from browser.
 */

@RestController
public class UserRoleChangeAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private UserRoleService service;

	/**
	 * User Role Assign/Unassign Action
	 *   It processes the Web-API POST request from browser.
	 */
	@PostMapping("/user_role/{urlMode}")
	@ResponseBody
	public StatusResponse response(@Validated(GroupOrder.class) @PathVariable String urlMode, @ModelAttribute UserRoleValidator validator, BindingResult result) throws Exception {

		String mode = "";
		if(urlMode.equals("assign")) {
			mode = "Assign";
		} else if(urlMode.equals("unassign")) {
			mode = "Unassign";
		}

		MessageUtil.setLocale();

		LogUtil.access(mode + " User Role started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "User");
		} catch (Exception ex) {
			LogUtil.access(mode + " User Role failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Token
		try {
			sessionUtil.checkToken(validator.getToken());
		} catch (Exception ex) {
			LogUtil.access(mode + " User Role failed (Token) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Request Values
		if (validator.getRoleId().equals("")) {
			LogUtil.access(mode + " User Role failed (Validation) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_NOT_SELECT_ROLE_NAME));
			return response;
		}

		// Set Values to Database
		try {
			if(urlMode.equals("assign")) {
				service.insertUserRole(validator);
			} else if(urlMode.equals("unassign")) {
				service.deleteUserRole(validator);
			}
		} catch (Exception ex) {
			LogUtil.access(mode + " User Role failed (Service) : '" + sessionUtil.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.access(mode + " User Role succeed : '" + sessionUtil.getUserName() + "'" + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		return response;
	}
}
