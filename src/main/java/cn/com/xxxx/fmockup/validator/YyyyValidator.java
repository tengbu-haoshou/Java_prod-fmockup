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
 * Yyyy Validator
 *   It checks values from browser.
 *
 */

@Data
public class YyyyValidator {

	// Token
	private String token;

	// Screen Parameter Values
	private String yyyyId;
	private String updateDate;

	// Hidden Input Values
	// TODO: check xxxxId, updateDate validity (For security more strictry)

	// Screen Input Values

	@NotBlank(message = "yyyyName:{YYYY_ERROR_FORM_NO_YYYY_NAME_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 32, message = "yyyyName:{YYYY_ERROR_FORM_ERROR_IN_YYYY_VALUE_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\_\\-\\.]*", message = "{YYYY_ERROR_FORM_ERROR_IN_YYYY_NAME_CHARACTER}", groups = GroupOrder3.class)
	private String yyyyName;

	@NotBlank(message = "yyyyValue:{YYYY_ERROR_FORM_NO_YYYY_VALUE_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 32, message = "yyyyValue:{YYYY_ERROR_FORM_ERROR_IN_YYYY_VALUE_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\_\\-\\.]*", message = "yyyyValue:{YYYY_ERROR_FORM_ERROR_IN_YYYY_VALUE_CHARACTER}", groups = GroupOrder3.class)
	private String yyyyValue;

	@NotBlank(message = "yyyyFlag:{YYYY_ERROR_FORM_NO_YYYY_FLAG_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 1, message = "yyyyFlag:{YYYY_ERROR_FORM_ERROR_IN_YYYY_FLAG_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[YN]", message = "yyyyFlag:{YYYY_ERROR_FORM_ERROR_IN_YYYY_FLAG_CHARACTER}", groups = GroupOrder3.class)
	private String yyyyFlag;

	@Size(max = 128, message = "remark:{YYYY_ERROR_FORM_ERROR_IN_YYYY_REMARK_LENGTH}")
	private String remark;

	// SQL Values
	private String userId;
}
