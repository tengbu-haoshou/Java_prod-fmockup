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

import cn.com.xxxx.fmockup.entity.UserEntity;
import cn.com.xxxx.fmockup.mapper.UserMapper;
import cn.com.xxxx.fmockup.util.CryptoUtil;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.util.StringUtil;
import cn.com.xxxx.fmockup.validator.UserValidator;
import cn.com.xxxx.fmockup.validator.UserValidatorForModify;

/**
 * User Service
 *   It processes the xxxx data.
 */

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;

	/**
	 * User Service
	 *   It processes the xxxx data.
	 */
	public List<UserEntity> searchAllUsers() throws Exception {

		List<UserEntity> listEntity;
		try {
			listEntity = mapper.searchAllUsers();
		} catch (Exception ex) {
			LogUtil.info("searchAllUsers : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (listEntity == null) {
			LogUtil.info("searchAllUsers : 'User List does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_USER_LIST_DOES_NOT_EXIST));
		}

		return listEntity;
	}

	/**
	 * User Service
	 *   It processes the user data.
	 */
	public UserEntity searchOneUser(UserValidator validator) throws Exception {

		UserEntity entity;
		try {
			entity = mapper.searchOneUser(validator);
		} catch (Exception ex) {
			LogUtil.info("searchOneUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("searchOneUser : 'This User does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_DOES_NOT_EXIST));
		}

		return entity;
	}

	/**
	 * User Service
	 *   It processes the user data.
	 */
	public void insertUser(UserValidator validator) throws Exception {

		// Check New Password and Confirm Password Are Matched or Not
		if (! validator.getNewPassword().equals(validator.getConfirmPassword())) {
			LogUtil.info("insertUser : 'New Password and Confirm Password are unmatched.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.PASSWORD_ERROR_TITLE_ERROR_NEW_PASSWORD_AND_CONFIRM_PASSWORD_ARE_UNMATCHED));
		}

		// Check Password String Complexity
		try {
			StringUtil.checkPasswordString(validator.getNewPassword());
		} catch (Exception ex) {
			LogUtil.info("insertUser : 'Error in Password character.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.PASSWORD_ERROR_TITLE_ERROR_IN_PASSWORD_CHARACTER));
		}

		// Get UserId
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000000");
		String userId = LocalDateTime.now().format(dtf);

		// Encrypt Password
		String passwordAes;
		try {
			passwordAes = CryptoUtil.encrypt(validator.getNewPassword(), 32);
		} catch (Exception ex) {
			LogUtil.info("insertUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		// Prepare Values
		validator.setUserId(userId);
		validator.setPasswordAes(passwordAes);

		// Set Values to Database
		int count;
		try {
			count = mapper.insertUser(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("insertUser : 'This User already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("insertUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count > 1) {
			LogUtil.info("insertUser : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * User Service
	 *   It processes the user data.
	 */
	public void updateUser(UserValidatorForModify validator) throws Exception  {

		// Check New Password and Confirm Password Are Matched or Not

		if (! validator.getNewPassword().equals("")) {

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

			if (! validator.getNewPassword().equals(validator.getConfirmPassword())) {
				LogUtil.info("updateUser : 'New Password and Confirm Password are unmatched.'");
				throw new Exception(MessageUtil.getMessage(MessageUtil.PASSWORD_ERROR_TITLE_ERROR_NEW_PASSWORD_AND_CONFIRM_PASSWORD_ARE_UNMATCHED));
			}

			// Encrypt Password
			String passwordAes;
			try {
				passwordAes = CryptoUtil.encrypt(validator.getNewPassword(), 32);
			} catch (Exception ex) {
				LogUtil.info("updateUser : '" + ex.getMessage() + "'");
				throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
			}
	
			// Prepare Values
			validator.setPasswordAes(passwordAes);
		}

		// Set Values to Database
		int count;
		try {
			if (! validator.getNewPassword().equals("") ) {
				count = mapper.updateUserWithPassword(validator);
			} else {
				count = mapper.updateUserWithoutPassword(validator);
			}
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("updateUser : 'This User already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("updateUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("updateUser : 'This User does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("updateUser : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * User Service
	 *   It processes the user data.
	 */
	public void deleteUser(UserValidator validator) throws Exception {

		// Remove Values from Database
		int count;
		try {
			count = mapper.deleteUser(validator);
		} catch (Exception ex) {
			LogUtil.info("deleteUser : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("deleteUser : 'This User does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.USER_ERROR_TITLE_THIS_USER_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("deleteUser : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}
}
