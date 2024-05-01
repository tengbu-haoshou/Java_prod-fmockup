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
public class UserEntity {
	private int no;
	private String userId;
	private String userName;
	private String passwordAes;
	private int failedCount;
	private String remark;
	private String userLockedF;
	private String addUserDate;
	private String modifyUserDate;
	private String updateDate;
	private String deletedF;
}
