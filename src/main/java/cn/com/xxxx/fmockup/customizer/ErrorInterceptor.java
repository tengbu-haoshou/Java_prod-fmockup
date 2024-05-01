//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.customizer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Error Interceptor
 *   It Cuts off Some Information from Error Information.
 */

/*
 * If a URL is invalid, the Spring Boot does not become an error.
 *
 * If this is not specified. Spring boot outputs a "Not Found" page.
 * It gives a hint to attackers, so it is not secured...
 */

@Controller
public class ErrorInterceptor implements ErrorController {

	/**
	 * Error Interceptor (for HTML request)
	 *   It Cuts off Some Information from Error Information.
	 */
	@RequestMapping(produces = {MediaType.TEXT_HTML_VALUE})
	public ModelAndView errorHtml(HttpServletRequest request, ModelAndView mav) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (statusCode != null && statusCode.toString().equals("404")) {
		  status = HttpStatus.NOT_FOUND;
		}
		mav.setStatus(status);

		return mav;
	}

	/**
	 * Error Interceptor (for JSON request)
	 *   It Cuts off Some Information from Error Information.
	 */
	@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> errorJson(HttpServletRequest req) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		// No Convert
		// Object statusCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		// if (statusCode != null && statusCode.toString().equals("404")) {
		// 　　　　status = HttpStatus.NOT_FOUND;
		// }

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("status", status.value());

		return new ResponseEntity<>(body, status);
	}
}
