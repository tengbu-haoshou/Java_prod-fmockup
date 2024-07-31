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

import java.util.HashMap;
import java.util.Map;

// JSON format:
//
// {
//   "status"     : "status-string",
//   "message"    : "message-string",
//   "messageMap" : {
//       "key"    : "value",
//       ...
//   }
// }

/**
 * JSON Response Structure
 *   All Web-API return this JSON.
 */

public class StatusResponse {

	private String status;
	private String message;
	private Map<String, String> messageMap = new HashMap<>();

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

	public void addMessageMap(String key, String message) {
		this.messageMap.put(key, message);
	}

	public Map<String, String> getMessageMap() {
		return this.messageMap;
	}
}
