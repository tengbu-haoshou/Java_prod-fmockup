//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.validator;

import lombok.Data;

/**
 * User Role Validator
 *   It checks values from browser.
 *
 */

@Data
public class UserRoleValidator {

	// Token
	private String token;

	// Screen Parameter Values
	private String userId;
	private String roleId;
	private String updateDate;

	// Hidden Input Values
	// TODO: check roleId, updateDate validity (For security more strictry)
}
