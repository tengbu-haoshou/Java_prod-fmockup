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
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import cn.com.xxxx.fmockup.entity.MenuEntity;
import cn.com.xxxx.fmockup.mapper.MenuMapper;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;

/**
 * Menu List Service
 *   It processes the menu data.
 */

@Service
public class MenuService {

	@Autowired
	private MenuMapper mapper;

	/**
	 * Menu List Service
	 *   It processes the menu data.
	 */
	public List<MenuEntity> searchAllMenus(String userId) throws Exception {

		Locale locale = LocaleContextHolder.getLocale();
		String lang = locale.getLanguage();
		String country = locale.getCountry();

		String langId;
		if (lang.equals("ja")) {
			langId = "ja";
		} else if (lang.equals("zh") && country.equals("CN")) {
			langId = "zh-CN";
		} else if (
			(lang.equals("en") && country.equals("US")) ||
			(lang.equals("en") && country.equals("UK")) ||
			(lang.equals("zh") && country.equals("TW"))) {
			langId = "en";
		} else {
			langId = "en";
		}

		List<MenuEntity> listEntity;
		try {
			listEntity = mapper.searchAllMenus(userId, langId);
		} catch (Exception ex) {
			LogUtil.info("searchAllMenus : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (listEntity == null) {
			LogUtil.info("searchAllMenus : 'Menu List does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.MENU_ERROR_TITLE_MENU_LIST_DOES_NOT_EXIST));
		}

		return listEntity;
	}
}
