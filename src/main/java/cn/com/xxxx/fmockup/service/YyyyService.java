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

import cn.com.xxxx.fmockup.entity.YyyyEntity;
import cn.com.xxxx.fmockup.mapper.YyyyMapper;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.validator.YyyyValidator;

/**
 * Yyyy Service
 *   It processes the yyyy data.
 */

@Service
public class YyyyService {

	@Autowired
	private YyyyMapper mapper;

	/**
	 * Yyyy Service
	 *   It processes the yyyy data.
	 */
	public List<YyyyEntity> searchAllYyyys() throws Exception {

		List<YyyyEntity> listEntity;
		try {
			listEntity = mapper.searchAllYyyys();
		} catch (Exception ex) {
			LogUtil.info("searchAllYyyys : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (listEntity == null) {
			LogUtil.info("searchAllYyyys : 'Yyyy List does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.YYYY_ERROR_TITLE_YYYY_LIST_DOES_NOT_EXIST));
		}

		return listEntity;
	}

	/**
	 * Yyyy Service
	 *   It processes the yyyy data.
	 */
	public YyyyEntity searchOneYyyy(YyyyValidator validator) throws Exception {

		YyyyEntity entity;
		try {
			entity = mapper.searchOneYyyy(validator);
		} catch (Exception ex) {
			LogUtil.info("searchOneYyyy : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("searchOneYyyy : 'This Yyyy does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.YYYY_ERROR_TITLE_THIS_YYYY_DOES_NOT_EXIST));
		}

		return entity;
	}

	/**
	 * Yyyy Service
	 *   It processes the yyyy data.
	 */
	public void insertYyyy(YyyyValidator validator) throws Exception {

		// Get YyyyId
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000000");
		String yyyyId = LocalDateTime.now().format(dtf);

		// Prepare Values
		validator.setYyyyId(yyyyId);

		// Set Values to Database
		int count;
		try {
			count = mapper.insertYyyy(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("insertYyyy : 'This Yyyy already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.YYYY_ERROR_TITLE_THIS_YYYY_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("insertYyyy : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count > 1) {
			LogUtil.info("insertYyyy : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * Yyyy Service
	 *   It processes the yyyy data.
	 */
	public void updateYyyy(YyyyValidator validator) throws Exception  {

		// Set Values to Database
		int count;
		try {
			count = mapper.updateYyyy(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("updateYyyy : 'This Yyyy already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.YYYY_ERROR_TITLE_THIS_YYYY_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("updateYyyy : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("updateYyyy : 'This Yyyy does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.YYYY_ERROR_TITLE_THIS_YYYY_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("updateYyyy : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * Yyyy Service
	 *   It processes the yyyy data.
	 */
	public void deleteYyyy(YyyyValidator validator) throws Exception {

		// Remove Values from Database
		int count;
		try {
			count = mapper.deleteYyyy(validator);
		} catch (Exception ex) {
			LogUtil.info("deleteYyyy : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("deleteYyyy : 'This Yyyy does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.YYYY_ERROR_TITLE_THIS_YYYY_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("deleteYyyy : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}
}
