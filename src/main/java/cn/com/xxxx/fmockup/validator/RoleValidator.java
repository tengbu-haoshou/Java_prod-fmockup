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
 * Role Validator
 *   It checks values from browser.
 *
 */

@Data
public class RoleValidator {

	// Token
	private String token;

	// Screen Parameter Values
	private String roleId;
	private String updateDate;

	// Hidden Input Values
	// TODO: check roleId, updateDate validity (For security more strictry)

	// Screen Input Values

	@NotBlank(message = "roleName:{ROLE_ERROR_FORM_NO_ROLE_NAME_IS_SPECIFIED}", groups = GroupOrder1.class)
	@Size(max = 32, message = "roleName:{ROLE_ERROR_FORM_ERROR_IN_ROLE_NAME_LENGTH}", groups = GroupOrder2.class)
	@Pattern(regexp = "[a-zA-Z0-9\\_\\-\\.]*", message = "roleName:{ROLE_ERROR_FORM_ERROR_IN_ROLE_NAME_CHARACTER}", groups = GroupOrder3.class)
	private String roleName;

	@Size(max = 128, message = "remark:{ROLE_ERROR_FORM_ERROR_IN_ROLE_NAME_LENGTH}", groups = GroupOrder2.class)
	private String remark;
}
