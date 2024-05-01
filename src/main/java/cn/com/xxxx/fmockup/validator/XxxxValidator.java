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

import cn.com.xxxx.fmockup.validator_order.GroupOrder1;
import cn.com.xxxx.fmockup.validator_order.GroupOrder2;
import cn.com.xxxx.fmockup.validator_order.GroupOrder3;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Xxxx Validator
 *   It checks values from browser.
 *
 */

@Data
public class XxxxValidator {

	// Token
	private String token;

	// Screen Parameter Values
	private String xxxxId;
	private String updateDate;

	// Hidden Input Values
	// TODO: check xxxxId, updateDate validity (For security more strictry)

	// Screen Input Values

	@NotBlank(message = "xxxxName:{XXXX_ERROR_FORM_NO_XXXX_NAME_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 32, message = "xxxxName:{XXXX_ERROR_FORM_ERROR_IN_XXXX_VALUE_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\_\\-\\.]*", message = "xxxxName:{XXXX_ERROR_FORM_ERROR_IN_XXXX_NAME_CHARACTER}", groups = GroupOrder3.class)
	private String xxxxName;

	@NotBlank(message = "xxxxValue:{XXXX_ERROR_FORM_NO_XXXX_VALUE_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 32, message = "xxxxValue:{XXXX_ERROR_FORM_ERROR_IN_XXXX_VALUE_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\_\\-\\.]*", message = "xxxxValue:{XXXX_ERROR_FORM_ERROR_IN_XXXX_VALUE_CHARACTER}", groups = GroupOrder3.class)
	private String xxxxValue;

	@NotBlank(message = "xxxxFlag:{XXXX_ERROR_FORM_NO_XXXX_FLAG_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 1, message = "xxxxFlag:{XXXX_ERROR_FORM_ERROR_IN_XXXX_FLAG_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[YN]", message = "xxxxFlag:{XXXX_ERROR_FORM_ERROR_IN_XXXX_FLAG_CHARACTER}", groups = GroupOrder3.class)
	private String xxxxFlag;

	@Size(max = 128, message = "remark:{XXXX_ERROR_FORM_ERROR_IN_XXXX_REMARK_LENGTH}", groups = GroupOrder2.class)
	private String remark;

	// SQL Values
	private String userId;
}
