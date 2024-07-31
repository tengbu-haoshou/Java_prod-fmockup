//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.validator;

import cn.com.xxxx.fmockup.validator_order.GroupOrder1;
import cn.com.xxxx.fmockup.validator_order.GroupOrder2;
import cn.com.xxxx.fmockup.validator_order.GroupOrder3;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * Notice:
 * Do not write detail message in Login Screen.
 * (Need to write ambiguous message in Login Screen)
 */

/**
 * Session Authorization Validator
 *   It checks values from browser.
 */

@Data
public class SessionAuthValidator {

	// Screen Input Values

	@NotEmpty(message = "userName:{LOGIN_ERROR_FORM_NO_USER_ID_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 32, message = "ambiguous:{LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\_\\-]*", message = "ambiguous:{LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT}", groups = GroupOrder3.class)
	private String userName;

	// Password is checked at SessionAuthAction
	@NotEmpty(message = "password:{LOGIN_ERROR_FORM_NO_PASSWORD_IS_SPECIFIED}", groups = GroupOrder1.class)
//	@Size(min = 8, max = 32, message = "ambiguous:{LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT}", groups = GroupOrder2.class)
//	@Pattern(regexp = "[a-zA-Z0-9\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\-\\=\\^\\~\\\\\\|\\@\\`\\[\\{\\;\\+\\:\\*\\]\\}\\,\\<\\.\\>\\/\\?\\_]*", message = "ambiguous:{LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT}", groups = GroupOrder3.class)
	private String password;
}
