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
 * Yyyy Entity
 *   It saves values from database.
 */

@Data
public class YyyyEntity {
	private int no;
	private String yyyyId;
	private String yyyyName;
	private String yyyyFlag;
	private String yyyyValue;
	private String remark;
	private String addYyyyDate;
	private String modifyYyyyDate;
	private String updateDate;
	private String deletedF;
}
