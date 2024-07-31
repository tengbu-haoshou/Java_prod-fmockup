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

import lombok.Data;

/**
 * Role Transaction Validator
 *   It checks values from browser.
 *
 */

@Data
public class RoleTranValidator {

	// Token
	private String token;

	// Screen Parameter Values
	private String roleId;
	private String tranId;
	private String updateDate;

	// Hidden Input Values
	// TODO: check roleId, tranId, updateDate validity (For security more strictry)
}
