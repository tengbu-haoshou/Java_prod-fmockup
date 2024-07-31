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

import cn.com.xxxx.fmockup.entity.UserRoleEntity;
import cn.com.xxxx.fmockup.validator.UserRoleValidator;

/**
 * User Role Mapper
 *   It manipulates User Role list data.
 */

@Mapper
public interface UserRoleMapper {

	List<UserRoleEntity> searchAllUserRoles(UserRoleValidator validator);
	int searchOneUserRoleCount(UserRoleValidator validator);

	int insertUserRole(UserRoleValidator validator);
	int deleteUserRole(UserRoleValidator validator);
}
