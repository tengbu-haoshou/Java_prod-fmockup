//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.util;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Message Utitility
 */

public class MessageUtil {

	public static String COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED = "Unknown trouble has occurred.";
	
	public static String COMMON_ERROR_TITLE_SESSION_TIMEOUT_HAS_OCCURRED = "COMMON_ERROR_TITLE_SESSION_TIMEOUT_HAS_OCCURRED";
	public static String COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE = "COMMON_ERROR_TITLE_PLEASE_MODIFY_VALUE";
	
	public static String MENU_ERROR_TITLE_MENU_LIST_DOES_NOT_EXIST = "MENU_ERROR_TITLE_MENU_LIST_DOES_NOT_EXIST";

	public static String LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT = "LOGIN_ERROR_TITLE_USER_ID_OR_PASSWORD_IS_NOT_CORRECT";
	public static String LOGIN_ERROR_TITLE_THIS_ACCOUNT_DOES_NOT_EXIST = "LOGIN_ERROR_TITLE_THIS_ACCOUNT_DOES_NOT_EXIST";

	public static String PASSWORD_ERROR_TITLE_INVALID_CURRENT_PASSWORD = "PASSWORD_ERROR_TITLE_INVALID_CURRENT_PASSWORD";
	public static String PASSWORD_ERROR_TITLE_ERROR_IN_PASSWORD_CHARACTER = "PASSWORD_ERROR_TITLE_ERROR_IN_PASSWORD_CHARACTER";
	public static String PASSWORD_ERROR_TITLE_ERROR_INVALID_NEW_PASSWORD = "PASSWORD_ERROR_TITLE_ERROR_INVALID_NEW_PASSWORD";
	public static String PASSWORD_ERROR_TITLE_ERROR_NEW_PASSWORD_AND_CONFIRM_PASSWORD_ARE_UNMATCHED = "PASSWORD_ERROR_TITLE_ERROR_NEW_PASSWORD_AND_CONFIRM_PASSWORD_ARE_UNMATCHED";

	public static String ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_DOES_NOT_EXIST = "ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_DOES_NOT_EXIST";
	public static String ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_WAS_LOCKED_PLEASE_LOGOUT_AND_TRY_TO_LOGIN_AGAIN = "ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_WAS_LOCKED_PLEASE_LOGOUT_AND_TRY_TO_LOGIN_AGAIN";
	public static String ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_WAS_LOCKED = "ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_WAS_LOCKED";
	public static String ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_IS_RESTRICTED = "ACCOUNT_ERROR_TITLE_YOUR_ACCOUNT_IS_RESTRICTED";

	public static String USER_ERROR_FORM_ERROR_IN_USER_REMARK_CHARACTER = "USER_ERROR_FORM_ERROR_IN_USER_REMARK_CHARACTER";
	public static String USER_ERROR_FORM_ERROR_IN_NEW_PASSWORD_LENGTH = "USER_ERROR_FORM_ERROR_IN_NEW_PASSWORD_LENGTH";
	public static String USER_ERROR_FORM_ERROR_IN_CONFIRM_PASSWORD_LENGTH = "USER_ERROR_FORM_ERROR_IN_CONFIRM_PASSWORD_LENGTH";
	
	public static String USER_ERROR_TITLE_USER_LIST_DOES_NOT_EXIST = "USER_ERROR_TITLE_USER_LIST_DOES_NOT_EXIST";
	public static String USER_ERROR_TITLE_THIS_USER_DOES_NOT_EXIST = "USER_ERROR_TITLE_THIS_USER_DOES_NOT_EXIST";
	public static String USER_ERROR_TITLE_THIS_USER_ALREADY_EXISTS = "USER_ERROR_TITLE_THIS_USER_ALREADY_EXISTS";
	public static String USER_ERROR_TITLE_USER_ROLE_LIST_DOES_NOT_EXIST = "USER_ERROR_TITLE_USER_ROLE_LIST_DOES_NOT_EXIST";
	public static String USER_ERROR_TITLE_THIS_USER_ROLE_ALREADY_EXISTS = "USER_ERROR_TITLE_THIS_USER_ROLE_ALREADY_EXISTS";
	public static String USER_ERROR_TITLE_THIS_USER_OR_ROLE_DOES_NOT_EXIST = "USER_ERROR_TITLE_THIS_USER_OR_ROLE_DOES_NOT_EXIST";

	public static String ROLE_ERROR_FORM_ERROR_IN_ROLE_REMARK_CHARACTER = "ROLE_ERROR_FORM_ERROR_IN_ROLE_REMARK_CHARACTER";

	public static String ROLE_ERROR_TITLE_NOT_SELECT_ROLE_NAME = "ROLE_ERROR_TITLE_NOT_SELECT_ROLE_NAME";
	public static String ROLE_ERROR_TITLE_ROLE_LIST_DOES_NOT_EXIST = "ROLE_ERROR_TITLE_ROLE_LIST_DOES_NOT_EXIST";
	public static String ROLE_ERROR_TITLE_THIS_ROLE_DOES_NOT_EXIST = "ROLE_ERROR_TITLE_THIS_ROLE_DOES_NOT_EXIST";
	public static String ROLE_ERROR_TITLE_THIS_ROLE_ALREADY_EXISTS = "ROLE_ERROR_TITLE_THIS_ROLE_ALREADY_EXISTS";
	public static String ROLE_ERROR_TITLE_THIS_ROLE_TRANSACTION_DOES_NOT_EXIST = "ROLE_ERROR_TITLE_THIS_ROLE_TRANSACTION_DOES_NOT_EXIST";
	public static String ROLE_ERROR_TITLE_THIS_ROLE_TRANSACTION_ALREADY_EXISTS = "ROLE_ERROR_TITLE_THIS_ROLE_TRANSACTION_ALREADY_EXISTS";
	public static String ROLE_ERROR_TITLE_THIS_ROLE_OR_TRANSACTION_DOES_NOT_EXISTS = "ROLE_ERROR_TITLE_THIS_ROLE_OR_TRANSACTION_DOES_NOT_EXISTS";
	public static String ROLE_ERROR_TITLE_THIS_ROLE_IS_ASSIGNED_BY_USER = "ROLE_ERROR_TITLE_THIS_ROLE_IS_ASSIGNED_BY_USER";

	public static String XXXX_ERROR_FORM_ERROR_IN_XXXX_REMARK_CHARACTER = "XXXX_ERROR_FORM_ERROR_IN_XXXX_REMARK_CHARACTER";
	public static String XXXX_ERROR_TITLE_XXXX_LIST_DOES_NOT_EXIST = "XXXX_ERROR_TITLE_XXXX_LIST_DOES_NOT_EXIST";
	public static String XXXX_ERROR_TITLE_THIS_XXXX_DOES_NOT_EXIST = "XXXX_ERROR_TITLE_THIS_XXXX_DOES_NOT_EXIST";
	public static String XXXX_ERROR_TITLE_THIS_XXXX_ALREADY_EXISTS = "XXXX_ERROR_TITLE_THIS_XXXX_ALREADY_EXISTS;";

	public static String YYYY_ERROR_FORM_ERROR_IN_YYYY_REMARK_CHARACTER = "YYYY_ERROR_FORM_ERROR_IN_YYYY_REMARK_CHARACTER";
	public static String YYYY_ERROR_TITLE_YYYY_LIST_DOES_NOT_EXIST = "YYYY_ERROR_TITLE_YYYY_LIST_DOES_NOT_EXIST";
	public static String YYYY_ERROR_TITLE_THIS_YYYY_DOES_NOT_EXIST = "YYYY_ERROR_TITLE_THIS_YYYY_DOES_NOT_EXIST";
	public static String YYYY_ERROR_TITLE_THIS_YYYY_ALREADY_EXISTS = "YYYY_ERROR_TITLE_THIS_YYYY_ALREADY_EXISTS";

	public static String ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_REMARK_CHARACTER = "ZZZZ_ERROR_FORM_ERROR_IN_ZZZZ_REMARK_CHARACTER";
	public static String ZZZZ_ERROR_TITLE_ZZZZ_LIST_DOES_NOT_EXIST = "ZZZZ_ERROR_TITLE_ZZZZ_LIST_DOES_NOT_EXIST";
	public static String ZZZZ_ERROR_TITLE_THIS_ZZZZ_DOES_NOT_EXIST = "ZZZZ_ERROR_TITLE_THIS_ZZZZ_DOES_NOT_EXIST";
	public static String ZZZZ_ERROR_TITLE_THIS_ZZZZ_ALREADY_EXISTS = "ZZZZ_ERROR_TITLE_THIS_ZZZZ_ALREADY_EXISTS";

	private static ResourceBundleMessageSource messageSource;

	/*
	 * Set message file Locale.
	 */
	public static void setLocale() {

		Locale locale = LocaleContextHolder.getLocale();
		String lang = locale.getLanguage();
		String country = locale.getCountry();

		if (lang.equals("ja")) {
			locale = new Locale("ja");
		} else if (lang.equals("zh") && country.equals("CN")) {
			locale = new Locale("zh", "CN");
		} else if (
			(lang.equals("en") && country.equals("US")) ||
			(lang.equals("en") && country.equals("UK")) ||
			(lang.equals("zh") && country.equals("TW"))) {
			locale = new Locale("en");
		} else {
			locale = new Locale("en");
		}
		LocaleContextHolder.setLocale(locale);
		messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return;
	}

	/*
	 * Get message from message file.
	 */
	public static String getMessage(String id) {
		String message = "";
		try {
			message = messageSource.getMessage(id, null, LocaleContextHolder.getLocale());
		} catch (Exception ex) {
			message = ex.getMessage();
		}
		return message;
	}
}
