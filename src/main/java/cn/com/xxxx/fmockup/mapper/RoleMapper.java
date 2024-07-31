//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.xxxx.fmockup.entity.RoleEntity;
import cn.com.xxxx.fmockup.validator.RoleValidator;

/**
 * User Mapper
 *   It manipulates Role data.
 */

@Mapper
public interface RoleMapper {

	List<RoleEntity> searchAllRoles();
	RoleEntity searchOneRole(RoleValidator validator);
	int searchUserRoleCount(@Param("roleId") String roleId);

	int insertRole(RoleValidator validator);
	int updateRole(RoleValidator validator);
	int deleteRole(RoleValidator validator);
}
