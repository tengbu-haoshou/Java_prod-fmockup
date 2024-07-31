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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xxxx.fmockup.entity.SessionAuthEntity;
import cn.com.xxxx.fmockup.mapper.SessionAuthMapper;
import cn.com.xxxx.fmockup.util.CryptoUtil;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.PropertyUtil;
import cn.com.xxxx.fmockup.validator.SessionAuthValidator;

/**
 * Session Auth Service
 *   It processes login data.
 */

@Service
public class SessionAuthService {

	@Autowired
	private SessionAuthMapper mapper;

	/**
	 * Session Auth Service
	 *   It processes login data.
	 */
	public SessionAuthEntity searchOneUser(SessionAuthValidator validator) throws Exception {

		//
		// If an error occurs in this process, it must output a blurred explanation in error message.
		//

		// Get Values from Database
		SessionAuthEntity entity;
		try {
			entity = mapper.searchOneUser(validator);
		} catch (Exception ex) {
			LogUtil.info("searchOneUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("searchOneUser : 'User does not exists.'");															// A true problem explanation
			throw new Exception(MessageUtil.getMessage(MessageUtil.LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT));		// A blurred explanation
		}

		// Check Retry Count
		if (entity.getFailedCount() >= PropertyUtil.getLoginRetryCount()) {
			LogUtil.info("searchOneUser : 'Retry Count is overed by maximum count.'");											// A true problem explanation
			throw new Exception(MessageUtil.getMessage(MessageUtil.LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT));		// A blurred explanation
		}

		// Check Match Password
		String password;
		try {
			password = CryptoUtil.decrypt(entity.getPasswordAes());
		} catch (Exception ex) {
			LogUtil.info("searchOneUser : '" + ex.getMessage() + "'");		// A true problem explanation
			throw new Exception(MessageUtil.getMessage(MessageUtil.LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT));	// A blurred explanation
		}
		if (! password.equals(validator.getPassword())) {

			try {
				mapper.updateUserFailedCount(entity.getUserId());
			} catch (Exception ex) {
				LogUtil.info("searchOneUser : '" + ex.getMessage() + "'");
				throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
			}

			LogUtil.info("searchOneUser : 'Password is not correct.'");														// A true problem explanation
			throw new Exception(MessageUtil.getMessage(MessageUtil.LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT));	// A blurred explanation
		}

		//
		// Because authorization process is already ended,
		// so if an error occurs in this process, it can output a true problem explanation in error message.
		//

		// Set Values to Database
		int count;
		try {
			count = mapper.resetUserFailedCount(entity.getUserId());
		} catch (Exception ex) {
			LogUtil.info("searchOneUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("searchOneUser : 'This Account does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.LOGIN_ERROR_TITLE_THIS_ACCOUNT_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("searchOneUser : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return entity;
	}

	public void searchOneUserForSession(String userId, String tranName) throws Exception {

		//
		// Because authorization process is already ended,
		// so if an error occurs in this process, it can output a true problem explanation in error message.
		//

		// Get Values from Database
		SessionAuthEntity entity;
		try {
			entity = mapper.searchOneUserForSession(userId);
		} catch (Exception ex) {
			LogUtil.info("searchOneUserForSession : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("searchOneUserForSession : 'Yout Account does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_DOES_NOT_EXIST));
		}

		// Check Account Is Locked or Not
		if (entity.getUserLockedF().equals("Y")) {
			LogUtil.info("searchOneUserForSession : 'Your Account was locked. Please logout, and try to login again.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_WAS_LOCKED_PLEASE_LOGOUT_AND_TRY_TO_LOGIN_AGAIN));
		}

		// Check Access Control
		int count;
		try {
			count = mapper.searchOneUserTranCount(userId, tranName);
		} catch (Exception ex) {
			LogUtil.info("searchOneUserForSession : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("searchOneUserForSession : 'Your Account access is restricted.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_IS_RESTRICTED));
		}

		return;
	}
}
