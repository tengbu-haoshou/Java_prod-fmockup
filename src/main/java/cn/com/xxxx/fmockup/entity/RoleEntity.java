//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.entity;

import lombok.Data;

/**
 * Role Entity
 *   It saves values from database.
 */

@Data
public class RoleEntity {
	private int no;
	private String roleId;
	private String roleName;
	private String remark;
	private String addRoleDate;
	private String modifyRoleDate;
	private String updateDate;
	private String deletedF;
}
