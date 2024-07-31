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

import cn.com.xxxx.fmockup.entity.XxxxEntity;
import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.service.XxxxService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.validator.XxxxValidator;
import jakarta.servlet.http.HttpSession;

/**
 * Xxxxx List Action
 *   It processes the Web-API GET request from browser.
 */

@RestController
public class XxxxInfoAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private XxxxService service;

	/**
	 * Xxxx Information Action
	 *   It processes the Web-API GET request from browser.
	 */
	@GetMapping("/xxxx/info")
	@ResponseBody
	public StatusResponse response(@ModelAttribute XxxxValidator validator) throws Exception {

		MessageUtil.setLocale();

		LogUtil.info("Xxxx Info started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "Xxxx");
		} catch (Exception ex) {
			LogUtil.access("Xxxx Info failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Get Values from Database
		XxxxEntity entity;
		try {
			entity = service.searchOneXxxx(validator);
		} catch (Exception ex) {
			LogUtil.info("Xxxx Info failed (Service) : '" + sessionUtil.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.info("Xxxx Info succeed : '" + sessionUtil.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		response.addMessageMap("xxxxName", entity.getXxxxName());
		response.addMessageMap("xxxxFlag", entity.getXxxxFlag());
		response.addMessageMap("xxxxValue", entity.getXxxxValue());
		response.addMessageMap("remark", entity.getRemark());
		return response;
	}
}
