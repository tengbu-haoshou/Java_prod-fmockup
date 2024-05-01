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

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Error Resolver
 *   It Sets Relaxed URL Query Characters Attribute.
 */

@Configuration
public class TomcatConfiguration {

	/*
	 * If a URL is specified as a special character, the Spring Boot does not become an error.
	 *
	 * If this is not specified. Spring boot outputs a Java error stack log.
	 * It gives a hint to attackers, so it is not secured...
	 */

	public TomcatServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addConnectorCustomizers((Connector connector) -> {
			connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
			connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
		});
		return factory;
	}
}
