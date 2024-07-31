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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Session Enabled Validator
 *   It checks values from browser.
 */

@Data
public class SessionEnabledValidator {

	// Token
	private String token;

	// Screen Input Values

	// a-z A-Z 0-9 ! " # $ % & ' ( ) - = ^ ~ \ | @ ` [ { ; + : * ] } , < . > / ? _
	@NotBlank(message = "currentPassword:{USER_ERROR_FORM_NO_CURRENT_PASSWORD_IS_SPECIFIED}", groups = GroupOrder1.class)
//	@Size(min = 8, max = 32, message = "currentPassword:{USER_ERROR_FORM_ERROR_IN_CURRENT_PASSWORD_LENGTH}", groups = GroupOrder2.class)
//	@Pattern(regexp = "[a-zA-Z0-9\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\-\\=\\^\\~\\\\\\|\\@\\`\\[\\{\\;\\+\\:\\*\\]\\}\\,\\<\\.\\>\\/\\?\\_]*", message = "currentPassword:{USER_ERROR_FORM_ERROR_IN_CURRENT_PASSWORD_CHARACTER}", groups = GroupOrder3.class)
	private String currentPassword;

	// a-z A-Z 0-9 ! " # $ % & ' ( ) - = ^ ~ \ | @ ` [ { ; + : * ] } , < . > / ? _
	@NotBlank(message = "newPassword:{USER_ERROR_FORM_NO_NEW_PASSWORD_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(min = 8, max = 32, message = "newPassword:{USER_ERROR_FORM_ERROR_IN_NEW_PASSWORD_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\-\\=\\^\\~\\\\\\|\\@\\`\\[\\{\\;\\+\\:\\*\\]\\}\\,\\<\\.\\>\\/\\?\\_]*", message = "newPassword:{USER_ERROR_FORM_ERROR_IN_NEW_PASSWORD_CHARACTER}", groups = GroupOrder3.class)
	private String newPassword;

	// a-z A-Z 0-9 ! " # $ % & ' ( ) - = ^ ~ \ | @ ` [ { ; + : * ] } , < . > / ? _
	@NotBlank(message = "confirmPassword:{USER_ERROR_FORM_NO_CONFIRM_PASSWORD_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(min = 8, max = 32, message = "confirmPassword:{USER_ERROR_FORM_ERROR_IN_CONFIRM_PASSWORD_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\-\\=\\^\\~\\\\\\|\\@\\`\\[\\{\\;\\+\\:\\*\\]\\}\\,\\<\\.\\>\\/\\?\\_]*", message = "confirmPassword:{USER_ERROR_FORM_ERROR_IN_CONFIRM_PASSWORD_CHARACTER}", groups = GroupOrder3.class)
	private String confirmPassword;

	// SQL Values
	private String userId;
	private String passwordAes;
}
