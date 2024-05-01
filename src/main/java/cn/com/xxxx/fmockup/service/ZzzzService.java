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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cn.com.xxxx.fmockup.entity.ZzzzEntity;
import cn.com.xxxx.fmockup.mapper.ZzzzMapper;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.validator.ZzzzValidator;

/**
 * Zzzz Service
 *   It processes the zzzz data.
 */

@Service
public class ZzzzService {

	@Autowired
	private ZzzzMapper mapper;

	/**
	 * Zzzz Service
	 *   It processes the zzzz data.
	 */
	public List<ZzzzEntity> searchAllZzzzs() throws Exception {

		List<ZzzzEntity> listEntity;
		try {
			listEntity = mapper.searchAllZzzzs();
		} catch (Exception ex) {
			LogUtil.info("searchAllZzzzs : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (listEntity == null) {
			LogUtil.info("searchAllZzzzs : 'Zzzz List does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ZZZZ_ERROR_TITLE_ZZZZ_LIST_DOES_NOT_EXIST));
		}

		return listEntity;
	}

	/**
	 * Zzzz Service
	 *   It processes the zzzz data.
	 */
	public ZzzzEntity searchOneZzzz(ZzzzValidator validator) throws Exception {

		ZzzzEntity entity;
		try {
			entity = mapper.searchOneZzzz(validator);
		} catch (Exception ex) {
			LogUtil.info("searchOneZzzz : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("searchOneZzzz : 'This Zzzz does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ZZZZ_ERROR_TITLE_THIS_ZZZZ_DOES_NOT_EXIST));
		}

		return entity;
	}

	/**
	 * Zzzz Service
	 *   It processes the zzzz data.
	 */
	public void insertZzzz(ZzzzValidator validator) throws Exception {

		// Get ZzzzId
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000000");
		String zzzzId = LocalDateTime.now().format(dtf);

		// Prepare Values
		validator.setZzzzId(zzzzId);

		// Set Values to Database
		int count;
		try {
			count = mapper.insertZzzz(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("insertZzzz : 'This Zzzz already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ZZZZ_ERROR_TITLE_THIS_ZZZZ_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("insertZzzz : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count > 1) {
			LogUtil.info("insertZzzz : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * Zzzz Service
	 *   It processes the zzzz data.
	 */
	public void updateZzzz(ZzzzValidator validator) throws Exception  {

		// Set Values to Database
		int count;
		try {
			count = mapper.updateZzzz(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("updateZzzz : 'This Zzzz already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ZZZZ_ERROR_TITLE_THIS_ZZZZ_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("updateZzzz : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("updateZzzz : 'This Zzzz does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ZZZZ_ERROR_TITLE_THIS_ZZZZ_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("updateZzzz : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * Zzzz Service
	 *   It processes the zzzz data.
	 */
	public void deleteZzzz(ZzzzValidator validator) throws Exception {

		// Remove Values from Database
		int count;
		try {
			count = mapper.deleteZzzz(validator);
		} catch (Exception ex) {
			LogUtil.info("deleteZzzz : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("deleteZzzz : 'This Zzzz does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.ZZZZ_ERROR_TITLE_THIS_ZZZZ_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("deleteZzzz : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}
}
