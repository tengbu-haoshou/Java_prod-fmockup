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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xxxx.fmockup.entity.SessionAuthEntity;
import cn.com.xxxx.fmockup.mapper.SessionEnabledMapper;
import cn.com.xxxx.fmockup.util.CryptoUtil;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.PropertyUtil;
import cn.com.xxxx.fmockup.util.StringUtil;
import cn.com.xxxx.fmockup.validator.SessionEnabledValidator;
import jakarta.servlet.http.HttpSession;

/**
 * Session Enabled Service Service
 *   It processes login data.
 */

@Service
public class SessionEnabledService {

	@Autowired
	private SessionEnabledMapper mapper;

	/*
	 * TODO: If it is not used, then delete it.
	 */
	
	/**
	 * Session Enabled Service
	 *   It processes login data.
	 */
	public void checkPermissionWithoutLocked(String userId) throws Exception {

		SessionAuthEntity entity;
		try {
			entity = mapper.findOneUser(userId);
		} catch (Exception ex) {
			LogUtil.info("checkPermissionWithoutLocked : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("checkPermissionWithoutLocked : 'Your Account does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_DOES_NOT_EXIST));
		}
	}

	/**
	 * Session Enabled Service
	 *   It processes login data.
	 */
	public void updateUser(HttpSession session, SessionEnabledValidator validator) throws Exception {

		// Check New Password and Confirm Password Are Matched or Not
		if (! validator.getNewPassword().equals(validator.getConfirmPassword())) {
			LogUtil.info("updateUser : 'New Password and Confirm Password are unmatched.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.PASSWORD_ERROR_TITLE_ERROR_NEW_PASSWORD_AND_CONFIRM_PASSWORD_ARE_UNMATCHED));
		}

		// Check Password String Complexity
		try {
			StringUtil.checkPasswordString(validator.getNewPassword());
		} catch (Exception ex) {
			LogUtil.info("updateUser : 'Error in Password character.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.PASSWORD_ERROR_TITLE_ERROR_IN_PASSWORD_CHARACTER));
		}

		// Get Account Value
		SessionAuthEntity entity;
		try {
			entity = mapper.findOneUser(validator.getUserId());
		} catch (Exception ex) {
			LogUtil.info("updateUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("updateUser : 'This User does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_DOES_NOT_EXIST));
		}

		// Check Retry Count
		if (entity.getFailedCount() >= PropertyUtil.getLoginRetryCount()) {

			// Drop Session
			session.invalidate();
			LogUtil.info("updateUser : 'Your Account was locked. Please logout.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_WAS_LOCKED));
		}

		// Get Current Password
		String password;
		try {
			password = CryptoUtil.decrypt(entity.getPasswordAes());
		} catch (Exception ex) {
			LogUtil.info("updateUser : '" + ex.getMessage() + "'");	// A true problem explanation
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);	// A blurred explanation
		}

		// Check Current Password
		if (! validator.getCurrentPassword().equals(password)) {

			// Update Failed Cound
			try {
				mapper.updateUserFailedCount(validator.getUserId());
			} catch (Exception ex) {
				LogUtil.info("updateUser : '" + ex.getMessage() + "'");
				throw new Exception(ex.getMessage());
			}
			LogUtil.info("updateUser : 'Invalid Current Password.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.PASSWORD_ERROR_TITLE_INVALID_CURRENT_PASSWORD));
		}

		// Check New Passowrd
		if (validator.getNewPassword().equals(password)) {
			LogUtil.info("updateUser : 'Invalid New Password.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.PASSWORD_ERROR_TITLE_ERROR_INVALID_NEW_PASSWORD));
		}

		// Encrypt Passowrd
		String passwordAes;
		try {
			passwordAes = CryptoUtil.encrypt(validator.getNewPassword(), 32);
		} catch (Exception ex) {
			LogUtil.info("updateUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		// Prepare Values
		validator.setPasswordAes(passwordAes);

		// Set Values to Database (with Reset Failed Count)
		int count;
		try {
			count = mapper.updateUser(validator);
		} catch (Exception ex) {
			LogUtil.info("updateUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("updateUser : 'Your Account does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("updateUser : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}
}
