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

import cn.com.xxxx.fmockup.entity.ZzzzEntity;
import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.service.ZzzzService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.validator.ZzzzValidator;
import jakarta.servlet.http.HttpSession;

/**
 * Zzzzx List Action
 *   It processes the Web-API GET request from browser.
 */

@RestController
public class ZzzzInfoAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private ZzzzService service;

	/**
	 * Zzzz Information Action
	 *   It processes the Web-API GET request from browser.
	 */
	@GetMapping("/zzzz/info")
	@ResponseBody
	public StatusResponse response(@ModelAttribute ZzzzValidator validator) throws Exception {

		MessageUtil.setLocale();

		LogUtil.info("Zzzz Info started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "Zzzz");
		} catch (Exception ex) {
			LogUtil.access("Zzzz Info failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Get Values from Database
		ZzzzEntity entity;
		try {
			entity = service.searchOneZzzz(validator);
		} catch (Exception ex) {
			LogUtil.info("Zzzz Info failed (Service) : '" + sessionUtil.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.info("Zzzz Info succeed : '" + sessionUtil.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		response.addMessageMap("zzzzName", entity.getZzzzName());
		response.addMessageMap("zzzzFlag", entity.getZzzzFlag());
		response.addMessageMap("zzzzValue", entity.getZzzzValue());
		response.addMessageMap("remark", entity.getRemark());
		return response;
	}
}
