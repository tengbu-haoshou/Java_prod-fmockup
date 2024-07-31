//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cn.com.xxxx.fmockup.entity.UserRoleEntity;
import cn.com.xxxx.fmockup.mapper.UserRoleMapper;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.validator.UserRoleValidator;

/**
 * User Role Service
 *   It processes the role data.
 */

@Service
public class UserRoleService {

	@Autowired
	private UserRoleMapper mapper;

	/**
	 * User Service
	 *   It processes the role data.
	 */
	public List<UserRoleEntity> searchAllUserRoles(UserRoleValidator validator) throws Exception {

		List<UserRoleEntity> listEntity;
		try {
			listEntity = mapper.searchAllUserRoles(validator);
		} catch (Exception ex) {
			LogUtil.info("searchAllUserRoles : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (listEntity == null) {
			LogUtil.info("searchAllUserRoles : 'User Role List does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_USER_ROLE_LIST_DOES_NOT_EXIST));
		}

		return listEntity;
	}

	/**
	 * User Role Service
	 *   It processes the role data.
	 */
	public void insertUserRole(UserRoleValidator validator) throws Exception {

		validator.setUserId(validator.getUserId());

		// Check User and Role exist or not.
		int count;
		try {
			count = mapper.searchOneUserRoleCount(validator);
		} catch (Exception ex) {
			LogUtil.info("insertUserRole : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("insertUserRole : 'This User or Role does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_OR_ROLE_DOES_NOT_EXIST));
		}

		// Set Values to Database
		try {
			count = mapper.insertUserRole(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("insertUserRole : 'This User Role already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_ROLE_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("insertUserRole : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count > 1) {
			LogUtil.info("insertUserTran : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * User Service
	 *   It processes the role data.
	 */
	public void deleteUserRole(UserRoleValidator validator) throws Exception  {

		// Set Values to Database
		int count;
		try {
			count = mapper.deleteUserRole(validator);
		} catch (Exception ex) {
			LogUtil.info("deleteUserRole : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("deleteUserRole : 'This User Role does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_OR_ROLE_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("deleteUserRole : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}
}
