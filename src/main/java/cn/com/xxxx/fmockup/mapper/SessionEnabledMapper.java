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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.xxxx.fmockup.entity.SessionAuthEntity;
import cn.com.xxxx.fmockup.validator.SessionEnabledValidator;

/**
 * Session Enabled Mapper
 *   It manipulates the login data.
 */

@Mapper
public interface SessionEnabledMapper {

	SessionAuthEntity findOneUser(@Param("userId") String userId);

	int updateUser(SessionEnabledValidator validator);
	int updateUserFailedCount(@Param("userId") String userId);
}
