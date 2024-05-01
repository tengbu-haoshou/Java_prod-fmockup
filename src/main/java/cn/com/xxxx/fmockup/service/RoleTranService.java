//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cn.com.xxxx.fmockup.entity.RoleTranEntity;
import cn.com.xxxx.fmockup.mapper.RoleTranMapper;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.validator.RoleTranValidator;

/**
 * User Role Service
 *   It processes the role transaction data.
 */

@Service
public class RoleTranService {

	@Autowired
	private RoleTranMapper mapper;

	/**
	 * User Service
	 *   It processes the role transaction data.
	 */
	public List<RoleTranEntity> searchAllRoleTrans(RoleTranValidator validator) throws Exception {

		List<RoleTranEntity> listEntity;
		try {
			listEntity = mapper.searchAllRoleTrans(validator);
		} catch (Exception ex) {
			LogUtil.info("searchAllRoleTrans : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (listEntity == null) {
			LogUtil.info("searchAllRoleTrans : 'User Role List does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_USER_ROLE_LIST_DOES_NOT_EXIST));
		}

		return listEntity;
	}

	/**
	 * User Role Service
	 *   It processes the role transaction data.
	 */
	public void insertRoleTran(RoleTranValidator validator) throws Exception {

		validator.setRoleId(validator.getRoleId());

		// Check Role and Transaciton exist or not.
		int count;
		try {
			count = mapper.searchOneRoleTranCount(validator);
		} catch (Exception ex) {
			LogUtil.info("insertRoleTran : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("insertRoleTran : 'This Role or Transaction does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_OR_TRANSACTION_DOES_NOT_EXISTS));
		}

		// Set Values to Database
		try {
			count = mapper.insertRoleTran(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("insertRoleTran : 'This Role Transacion already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_TRANSACTION_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("insertRoleTran : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count > 1) {
			LogUtil.info("insertRoleTran : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * User Service
	 *   It processes the role transaction data.
	 */
	public void deleteRoleTran(RoleTranValidator validator) throws Exception  {

		// Set Values to Database
		int count;
		try {
			count = mapper.deleteRoleTran(validator);
		} catch (Exception ex) {
			LogUtil.info("deleteRoleTran : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("deleteRoleTran : 'This Role Transaction does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ROLE_ERROR_TITLE_THIS_ROLE_TRANSACTION_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("deleteRoleTran : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}
}
