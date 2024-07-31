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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.xxxx.fmockup.entity.RoleEntity;
import cn.com.xxxx.fmockup.response.ListResponse;
import cn.com.xxxx.fmockup.service.RoleService;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import jakarta.servlet.http.HttpSession;

/**
 * Role List Action
 *   It processes the Web-API GET request from browser.
 */

@RestController
public class RoleListAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private RoleService service;

	/**
	 * Role List Action
	 *   It processes the Web-API GET request from browser.
	 */
	@GetMapping("/role/list")
	@ResponseBody
	public ListResponse response() throws Exception {

		MessageUtil.setLocale();

		LogUtil.access("Role List started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		ListResponse response = new ListResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "Role");
		} catch (Exception ex) {
			LogUtil.access("Role List failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Get Values from Database
		List<RoleEntity> listEntity;
		try {
			listEntity = service.searchAllRoles();
		} catch (Exception ex) {
			response.setStatus("action-ng");
			LogUtil.access("Role List failed (Service) : '" + sessionUtil.getUserName() + "', '" + ex.getMessage() + "'");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.access("Role List succeed : '" + sessionUtil.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		response.setList(listEntity);
		return response;
	}
}
