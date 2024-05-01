//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.com.xxxx.fmockup.entity.RoleTranEntity;
import cn.com.xxxx.fmockup.validator.RoleTranValidator;

/**
 * Role Transaction Mapper
 *   It manipulates User Role list data.
 */

@Mapper
public interface RoleTranMapper {

	List<RoleTranEntity> searchAllRoleTrans(RoleTranValidator validator);
	int searchOneRoleTranCount(RoleTranValidator validator);

	int insertRoleTran(RoleTranValidator validator);
	int deleteRoleTran(RoleTranValidator validator);
}
