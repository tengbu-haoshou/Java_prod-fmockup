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

import java.util.ArrayList;
import java.util.List;

// JSON format:
//
// {
//   "status"  : "status-string",
//   "message" : "message-string",
//   "list"    : [
//     {
//       "key" : "value",
//       ...
//     }, ...
//   ]
// }

/**
 * JSON Response Structure
 *   All Web-API return this JSON.
 */

public class ListResponse {

	private String status;
	private String message;
	private Object list = new Object();

	public ListResponse() {
		List<String> list_ = new ArrayList<String>();
		list = (Object) list_;
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

	public void setList(Object object) {
		this.list = object;
	}

	public Object getList() {
		return this.list;
	}
}
