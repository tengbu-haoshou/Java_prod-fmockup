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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.service.YyyyService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.util.StringUtil;
import cn.com.xxxx.fmockup.validator.YyyyValidator;
import cn.com.xxxx.fmockup.validator_order.GroupOrder;
import jakarta.servlet.http.HttpSession;

/**
 * Yyyy Add/Modify/Drop Action
 *   It processes the Web-API POST request from browser.
 */

@RestController
public class YyyyChangeAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private YyyyService service;

	/**
	 * Yyyy Add/Modify/Drop Action
	 *   It processes the Web-API POST request from browser.
	 */
	@PostMapping("/yyyy/{urlMode}")
	@ResponseBody
	public StatusResponse response(@PathVariable String urlMode, @Validated(GroupOrder.class) @ModelAttribute YyyyValidator validator, BindingResult result) throws Exception {

		MessageUtil.setLocale();

		String mode = "";
		if(urlMode.equals("add")) {
			mode = "Add";
		} else if(urlMode.equals("modify")) {
			mode = "Modify";
		} else if(urlMode.equals("drop")) {
			mode = "Drop";
		}

		LogUtil.info(mode + " Yyyy started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "Yyyy");
		} catch (Exception ex) {
			LogUtil.access(mode + " Yyyy failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Token
		try {
			sessionUtil.checkToken(validator.getToken());
		} catch (Exception ex) {
			LogUtil.info(mode + " Yyyy failed (Token) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Request Values
		if ((urlMode.equals("add") || urlMode.equals("modify")) && result.hasErrors()) {
			LogUtil.info(mode + " Yyyy failed (Validation) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
			for (ObjectError error : result.getAllErrors()) {
				String[] keyValue = error.getDefaultMessage().split(":", 2);
				response.addMessageMap(keyValue[0], keyValue[1]);
			}
			return response;
		}

		// Check Remark
		if(urlMode.equals("add") || urlMode.equals("modify")) {
			try {
				StringUtil.checkRemarkString(validator.getRemark());
			} catch(Exception ex) {
				LogUtil.info(mode + " Yyyy failed (Validation) : '" + sessionUtil.getUserName() + "'");
				response.addMessageMap("remark", ex.getMessage());
				response.setStatus("action-ng");
				response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
				response.addMessageMap("remark", MessageUtil.getMessage(MessageUtil.YYYY_ERROR_FORM_ERROR_IN_YYYY_REMARK_CHARACTER));
				return response;
			}
		}

		// Set Values to Database
		validator.setUserId(sessionUtil.getUserId());
		try {
			if(urlMode.equals("add")) {
				service.insertYyyy(validator);
			} else if(urlMode.equals("modify")) {
				service.updateYyyy(validator);
			} else if(urlMode.equals("drop")) {
				service.deleteYyyy(validator);
			}
		} catch (Exception ex) {
			LogUtil.info(mode + " Yyyy failed (Service) : '" + sessionUtil.getUserName() + "', '" + validator.getYyyyName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.info(mode + " Yyyy succeed : '" + sessionUtil.getUserName() + "'" + "', '" + validator.getYyyyName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		return response;
	}
}
