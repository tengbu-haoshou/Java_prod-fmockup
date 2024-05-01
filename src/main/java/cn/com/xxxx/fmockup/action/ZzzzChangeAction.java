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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.xxxx.fmockup.response.StatusResponse;
import cn.com.xxxx.fmockup.service.SessionAuthService;
import cn.com.xxxx.fmockup.service.ZzzzService;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.SessionUtil;
import cn.com.xxxx.fmockup.util.StringUtil;
import cn.com.xxxx.fmockup.validator.ZzzzValidator;
import cn.com.xxxx.fmockup.validator_order.GroupOrder;
import jakarta.servlet.http.HttpSession;

/**
 * Zzzz Add/Modify/Drop Action
 *   It processes the Web-API POST request from browser.
 */

@RestController
public class ZzzzChangeAction {

	@Autowired
	private HttpSession session;

	@Autowired
	private SessionAuthService sessionService;

	@Autowired
	private ZzzzService service;

	/**
	 * Zzzz Add/Modify/Drop Action
	 *   It processes the Web-API POST request from browser.
	 */
	@PostMapping("/zzzz/{urlMode}")
	@ResponseBody
	public StatusResponse response(@PathVariable String urlMode, @Validated(GroupOrder.class) @ModelAttribute ZzzzValidator validator, BindingResult result) throws Exception {

		MessageUtil.setLocale();

		String mode = "";
		if(urlMode.equals("add")) {
			mode = "Add";
		} else if(urlMode.equals("modify")) {
			mode = "Modify";
		} else if(urlMode.equals("drop")) {
			mode = "Drop";
		}

		LogUtil.info(mode + " Zzzz started (" + Thread.currentThread().getStackTrace()[1].getClassName() + ")");
		StatusResponse response = new StatusResponse();

		// Check Session
		SessionUtil sessionUtil;
		try {
			sessionUtil = new SessionUtil(session, true);
			sessionService.searchOneUserForSession(sessionUtil.getUserId(), "Zzzz");
		} catch (Exception ex) {
			LogUtil.access(mode + " Zzzz failed (Session) : '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Token
		try {
			sessionUtil.checkToken(validator.getToken());
		} catch (Exception ex) {
			LogUtil.info(mode + " Zzzz failed (Token) : '" + sessionUtil.getUserName() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Check Request Values
		if ((urlMode.equals("add") || urlMode.equals("modify")) && result.hasErrors()) {
			LogUtil.info(mode + " Zzzz failed (Validation) : '" + sessionUtil.getUserName() + "'");
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
				LogUtil.info(mode + " Zzzz failed (Validation) : '" + sessionUtil.getUserName() + "'");
				response.addMessageMap("remark", ex.getMessage());
				response.setStatus("action-ng");
				response.setMessage(MessageUtil.getMessage(MessageUtil.COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE));
				response.addMessageMap("remark", MessageUtil.getMessage(MessageUtil.ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_REMARK_CHARACTER));
				return response;
			}
		}

		// Set Values to Database
		validator.setUserId(sessionUtil.getUserId());
		try {
			if(urlMode.equals("add")) {
				service.insertZzzz(validator);
			} else if(urlMode.equals("modify")) {
				service.updateZzzz(validator);
			} else if(urlMode.equals("drop")) {
				service.deleteZzzz(validator);
			}
		} catch (Exception ex) {
			LogUtil.info(mode + " Zzzz failed (Service) : '" + sessionUtil.getUserName() + "', '" + validator.getZzzzName() + "', '" + ex.getMessage() + "'");
			response.setStatus("action-ng");
			response.setMessage(ex.getMessage());
			return response;
		}

		// Set Response Values
		LogUtil.info(mode + " Zzzz succeed : '" + sessionUtil.getUserName() + "'" + "', '" + validator.getZzzzName() + "'");
		response.setStatus("action-ok");
		response.setMessage("");
		return response;
	}
}
