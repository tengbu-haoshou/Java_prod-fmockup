//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.response;

// JSON format:
//
// {
//   "status"  : "status-string",
//   "message" : "message-string",
//   "token"   : "token-string"
// }

/**
 * JSON Response Structure
 *   All Web-API return this JSON.
 */

public class TokenResponse {

	private String status;
	private String message;
	private String token;

	public TokenResponse() {
		;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
