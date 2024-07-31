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

import cn.com.xxxx.fmockup.entity.YyyyEntity;
import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.service.YyyyService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.validator.YyyyValidator;
import jakarta.servlet.http.HttpSession;

/**
 * Yyyyx List Action
 *   It processes the Web-API GET request from browser.
 */

@RestController
public class YyyyInfoAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private YyyyService service;

	/**
	 * Yyyy Information Action
	 *   It processes the Web-API GET request from browser.
	 */
	@GetMapping("/yyyy/info")
	@ResponseBody
	public StatusResponse response(@ModelAttribute YyyyValidator validator) throws Exception {

		MessageUtil.setLocale();

		LogUtil.info("Yyyy Info started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "Yyyy");
		} catch (Exception ex) {
			LogUtil.access("Yyyy Info failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Get Values from Database
		YyyyEntity entity;
		try {
			entity = service.searchOneYyyy(validator);
		} catch (Exception ex) {
			LogUtil.info("Yyyy Info failed (Service) : '" + sessionUtil.getUserName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.info("Yyyy Info succeed : '" + sessionUtil.getUserName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		response.addMessageMap("yyyyName", entity.getYyyyName());
		response.addMessageMap("yyyyFlag", entity.getYyyyFlag());
		response.addMessageMap("yyyyValue", entity.getYyyyValue());
		response.addMessageMap("remark", entity.getRemark());
		return response;
	}
}
