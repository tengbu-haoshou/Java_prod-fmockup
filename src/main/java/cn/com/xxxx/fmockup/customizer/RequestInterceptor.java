//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.customizer;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Request Interceptor
 */

@Component
public class RequestInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

		// Browser should control cache.
		response.addHeader("Cache-Control", "private, no-store, no-cache, max-age=0, must-revalidate");
		// Browser cache time is zero.
		response.addHeader("Expires", "");
		// Browser should inspect cache. (For old browser)
		// Browser should keep https conneciton.
		response.addHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		response.addHeader("Pragma", "no-cache");
		// Browser should check content-type (should not check contents characters), prevent XSS.
		response.addHeader("X-Content-Type-Options", "nosniff");
		// Browser should prevent Click-Jacking. <iframe> only can use in same-origin (Ex. https://xxxx_IT_Technologies.com.cn:8080).
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		// Browser should prevent XSS after found XSS.
		response.addHeader("X-XSS-Protection", "1; mode=block");

		// Response Header (Spring Security Does Not Sets Value)
		// response.addHeader("Content-Security-Policy", "default-src 'self'"); <- Please Do Not Use It.

		return;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
		return;
	}
}
