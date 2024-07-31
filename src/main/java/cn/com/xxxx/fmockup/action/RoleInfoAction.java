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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.xxxx.fmockup.entity.RoleEntity;
import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.RoleService;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.validator.RoleValidator;
import jakarta.servlet.http.HttpSession;

/**
 * User List Action
 *   It processes the Web-API GET request from browser.
 */

@RestController
public class RoleInfoAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private RoleService service;

	/**
	 * Role Information Action
	 *   It processes the Web-API GET request from browser.
	 */
	@GetMapping("/role/info")
	@ResponseBody
	public StatusResponse response(@ModelAttribute RoleValidator validator) throws Exception {

		MessageUtil.setLocale();

		LogUtil.access("Role Info started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "Role");
		} catch (Exception ex) {
			LogUtil.access("Role Info failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Get Values from Database
		RoleEntity entity;
		try {
			entity = service.searchOneRole(validator);
		} catch (Exception ex) {
			LogUtil.access("Role Info failed (Service) : '" + sessionUtil.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.access("Role Info succeed : '" + sessionUtil.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		response.addMessageMap("roleName", entity.getRoleName());
		response.addMessageMap("remark", entity.getRemark());
		return response;
	}
}
