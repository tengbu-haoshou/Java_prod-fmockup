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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cn.com.xxxx.fmockup.entity.RoleEntity;
import cn.com.xxxx.fmockup.mapper.RoleMapper;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.validator.RoleValidator;

/**
 * Role Service
 *   It processes the role data.
 */

@Service
public class RoleService {

	@Autowired
	private RoleMapper mapper;

	/**
	 * Role Service
	 *   It processes the role data.
	 */
	public List<RoleEntity> searchAllRoles() throws Exception {

		List<RoleEntity> listEntity;
		try {
			listEntity = mapper.searchAllRoles();
		} catch (Exception ex) {
			LogUtil.info("searchAllRoles : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (listEntity == null) {
			LogUtil.info("searchAllRoles : 'Role List does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_ROLE_LIST_DOES_NOT_EXIST));
		}

		return listEntity;
	}

	/**
	 * Role Service
	 *   It processes the role data.
	 */
	public RoleEntity searchOneRole(RoleValidator validator) throws Exception {

		RoleEntity entity;
		try {
			entity = mapper.searchOneRole(validator);
		} catch (Exception ex) {
			LogUtil.info("searchOneRole : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("searchOneRole : 'This Role does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_DOES_NOT_EXIST));
		}

		return entity;
	}

	/**
	 * Role Service
	 *   It processes the role data.
	 */
	public void insertRole(RoleValidator validator) throws Exception {

		// Get RoleId
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000000");
		String roleId = LocalDateTime.now().format(dtf);

		// Prepare Values
		validator.setRoleId(roleId);

		// Set Values to Database
		int count;
		try {
			count = mapper.insertRole(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("insertRole : 'This Role already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("insertRole : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count > 1) {
			LogUtil.info("insertRole : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * Role Service
	 *   It processes the role data.
	 */
	public void updateRole(RoleValidator validator) throws Exception  {

		// Set Values to Database
		int count;
		try {
			count = mapper.updateRole(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("updateRole : 'This Role already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("updateRole : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("updateRole : 'This Role does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("updateRole : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * Role Service
	 *   It processes the role data.
	 */
	public void deleteRole(RoleValidator validator) throws Exception {

		// Check Role is assinged by User
		int count;
		try {
			count = mapper.searchUserRoleCount(validator.getRoleId());
		} catch (Exception ex) {
			LogUtil.info("deleteRole : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count != 0) {
			LogUtil.info("deleteRole : 'This Role is assigned by User.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_IS_ASSIGNED_BY_USER));
		}

		// Remove Values from Database
		try {
			count = mapper.deleteRole(validator);
		} catch (Exception ex) {
			LogUtil.info("deleteRole : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("deleteRole : 'This Role does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("deleteRole : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}
}
