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

import cn.com.xxxx.fmockup.util.LogUtil;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Session Listener
 *   It Catches Session Idle Timeout.
 */

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void sessionDestroyed(HttpSessionEvent event) throws ClassCastException {

		HttpSession session = event.getSession();

		if (session == null || session.getAttribute("fmockup") == null || session.getAttribute("userName") == null) {
			LogUtil.access("Already Logged-out (at listener).");
			return;
		}

		LogUtil.access("Logout succeed (at listener) : " + (String) session.getAttribute("userName"));
		return;
	}
}
