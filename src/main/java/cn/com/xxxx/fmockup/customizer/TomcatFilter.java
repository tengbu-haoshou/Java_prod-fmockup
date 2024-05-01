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

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Tomcat Filter
 */

@Component
public class TomcatFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		return;
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String hostFqdn = request.getHeader("host");
		if(! hostFqdn.equals(cn.com.xxxx.fmockup.util.PropertyUtil.getHostFqdn())) {
			response.setStatus(403);
		}

/*
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
*/

		filterChain.doFilter(request, response);
		return;
	}

	@Override
	public void destroy() {
		return;
	}
}
