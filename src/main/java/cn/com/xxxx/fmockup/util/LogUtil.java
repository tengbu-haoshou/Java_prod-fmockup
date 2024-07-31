//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log Utility
 *   It writes to log file.
 */

public class LogUtil {

	protected static final Logger accountLogger = LoggerFactory.getLogger("accountLogger");
	protected static final Logger applicationLogger = LoggerFactory.getLogger("applicationLogger");

	public static void access(String msg) {
		accountLogger.info(msg);
		applicationLogger.info(msg);
	}

	public static void info(String msg) {
		applicationLogger.info(msg);
	}

	public static void warn(String msg) {
		applicationLogger.warn(msg);
	}

	public static void error(String msg) {
		applicationLogger.error(msg);
	}

	public static void debug(String msg) {
		applicationLogger.debug(msg);
	}

	public static void trace(String msg) {
		applicationLogger.trace(msg);
	}

}
