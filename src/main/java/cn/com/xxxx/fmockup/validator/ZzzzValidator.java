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
 * Zzzz Validator
 *   It checks values from browser.
 *
 */

@Data
public class ZzzzValidator {

	// Token
	private String token;

	// Screen Parameter Values
	private String zzzzId;
	private String updateDate;

	// Hidden Input Values
	// TODO: check xxxxId, updateDate validity (For security more strictry)

	// Screen Input Values

	@NotBlank(message = "zzzzName:{ZZZZ_ERROR_FORM_NO_ZZZZ_NAME_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 32, message = "zzzzName:{ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_VALUE_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\_\\-\\.]*", message = "zzzzName:{ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_NAME_CHARACTER}", groups = GroupOrder3.class)
	private String zzzzName;

	@NotBlank(message = "zzzzValue:{ZZZZ_ERROR_FORM_NO_ZZZZ_VALUE_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 32, message = "zzzzValue:{ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_VALUE_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\_\\-\\.]*", message = "zzzzValue:{ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_VALUE_CHARACTER}", groups = GroupOrder3.class)
	private String zzzzValue;

	@NotBlank(message = "zzzzFlag:{ZZZZ_ERROR_FORM_NO_ZZZZ_FLAG_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 1, message = "zzzzFlag:{ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_FLAG_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[YN]", message = "zzzzFlag:{ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_FLAG_CHARACTER}", groups = GroupOrder3.class)
	private String zzzzFlag;

	@Size(max = 128, message = "remark:{ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_REMARK_LENGTH}")
	private String remark;

	// SQL Values
	private String userId;
}
