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

import java.util.Collections;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.SessionTrackingMode;

/**
 * Servlet Initializer
 */
@Configuration
public class ServletInitializer {

	public ServletContextInitializer servletContextInitializer() {

		ServletContextInitializer initializer = servletContext -> {

			// Cookie (HttpOnly) default is true
//			servletContext.getSessionCookieConfig().setHttpOnly(true);
//			servletContext.getSessionCookieConfig().setHttpOnly(false);

			// Cookie (Secure) default is true
//			servletContext.getSessionCookieConfig().setSecure(true);
//			servletContext.getSessionCookieConfig().setSecure(false);

			//
			// Remove the JSESSIONID from URL
			//
			// <link href="./bootstrap/css/bootstrap.min.css;jsessionid=1E304E1B77F604CA856F7A4E7333FF5E" type="text/css" rel="stylesheet" />
			//                                               -------------------------------------------
			//                                               This process can remove this jsessoinid
			//
			servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
		};

		return initializer;
	}
}