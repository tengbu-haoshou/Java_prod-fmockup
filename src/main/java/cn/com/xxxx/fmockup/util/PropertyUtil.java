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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Property File "application.property" Utility
 *   It reads from property file parameters.
 */

@Component
public class PropertyUtil {

	// Application Values

	private static int idleTimeout;		// minutes
	private static int loginRetryCount;	// number
	private static int loginExpireDay;	// day
	private static String hostFqdn;		//

	private static String cryptoKey;		// must be 16 bytes

	@Value("${property.idle-timeout:30}")
	public void setIdleTimeout(int timeout) {
		PropertyUtil.idleTimeout = timeout;
	}

	public static int getIdleTimeout() {
		return idleTimeout;
	}

	@Value("${property.login-retry-count:5}")
	public void setLoginRetryCount(int loginRetryCount) {
		PropertyUtil.loginRetryCount = loginRetryCount;
	}

	public static int getLoginRetryCount() {
		return loginRetryCount;
	}

	@Value("${property.login-expire-day:30}")
	public void setLoginExpireDay(int loginExpireDay) {
		PropertyUtil.loginExpireDay = loginExpireDay;
	}

	public static int getLoginExpireDay() {
		return loginExpireDay;
	}

	@Value("${property.crypto-key:Asdf1234Asdf1234}")
	public void setCryptoKey(String cryptokey) {
		PropertyUtil.cryptoKey = cryptokey;
	}

	public static String getCryptoKey() {
		return cryptoKey;
	}

	@Value("${property.host-fqdn:xx.xx.xx.xx:8080}")
	public void setHostFqdn(String hostFqdn) {
		PropertyUtil.hostFqdn = hostFqdn;
	}

	public static String getHostFqdn() {
		return hostFqdn;
	}
}
