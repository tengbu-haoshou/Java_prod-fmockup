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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.xxxx.fmockup.entity.SessionAuthEntity;
import cn.com.xxxx.fmockup.validator.SessionAuthValidator;

/**
 * Session Auth Mapper
 *   It manipulates the login data.
 */

@Mapper
public interface SessionAuthMapper {

	SessionAuthEntity searchOneUser(SessionAuthValidator validator);
	SessionAuthEntity searchOneUserForSession(@Param("userId") String userId);
	int searchOneUserTranCount(@Param("userId") String userId, @Param("tranName") String tranName);

	int updateUserFailedCount(@Param("userId") String userId);
	int resetUserFailedCount(@Param("userId") String userId);
}
