//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.entity;

import lombok.Data;

/**
 * User Entity
 *   It saves values from database.
 */

@Data
public class UserRoleEntity {
	private String roleId;
	private String roleName;
	private String isExists;
}
