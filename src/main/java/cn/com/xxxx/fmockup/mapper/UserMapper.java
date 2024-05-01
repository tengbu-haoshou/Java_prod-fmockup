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

import cn.com.xxxx.fmockup.entity.UserEntity;
import cn.com.xxxx.fmockup.validator.UserValidator;
import cn.com.xxxx.fmockup.validator.UserValidatorForModify;

/**
 * User Mapper
 *   It manipulates User data.
 */

@Mapper
public interface UserMapper {

	List<UserEntity> searchAllUsers();
	UserEntity searchOneUser(UserValidator validator);

	int insertUser(UserValidator validator);
	int updateUserWithoutPassword(UserValidatorForModify validator);
	int updateUserWithPassword(UserValidatorForModify validator);
	int deleteUser(UserValidator validator);
}
