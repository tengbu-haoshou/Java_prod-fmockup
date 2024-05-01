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

import cn.com.xxxx.fmockup.entity.XxxxEntity;
import cn.com.xxxx.fmockup.mapper.XxxxMapper;
import cn.com.xxxx.fmockup.util.LogUtil;
import cn.com.xxxx.fmockup.util.MessageUtil;
import cn.com.xxxx.fmockup.validator.XxxxValidator;

/**
 * Xxxx Service
 *   It processes the xxxx data.
 */

@Service
public class XxxxService {

	@Autowired
	private XxxxMapper mapper;

	/**
	 * Xxxx Service
	 *   It processes the xxxx data.
	 */
	public List<XxxxEntity> searchAllXxxxs() throws Exception {

		List<XxxxEntity> listEntity;
		try {
			listEntity = mapper.searchAllXxxxs();
		} catch (Exception ex) {
			LogUtil.info("searchAllXxxxs : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (listEntity == null) {
			LogUtil.info("searchAllXxxxs : 'Xxxx List does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.XXXX_ERROR_TITLE_XXXX_LIST_DOES_NOT_EXIST));
		}

		return listEntity;
	}

	/**
	 * Xxxx Service
	 *   It processes the xxxx data.
	 */
	public XxxxEntity searchOneXxxx(XxxxValidator validator) throws Exception {

		XxxxEntity entity;
		try {
			entity = mapper.searchOneXxxx(validator);
		} catch (Exception ex) {
			LogUtil.info("searchOneXxxx : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (entity == null) {
			LogUtil.info("searchOneXxxx : 'This Xxxx does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.XXXX_ERROR_TITLE_THIS_XXXX_DOES_NOT_EXIST));
		}

		return entity;
	}

	/**
	 * Xxxx Service
	 *   It processes the xxxx data.
	 */
	public void insertXxxx(XxxxValidator validator) throws Exception {

		// Get XxxxId
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000000");
		String xxxxId = LocalDateTime.now().format(dtf);

		// Prepare Values
		validator.setXxxxId(xxxxId);

		// Set Values to Database
		int count;
		try {
			count = mapper.insertXxxx(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("insertXxxx : 'This Xxxx already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.XXXX_ERROR_TITLE_THIS_XXXX_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("insertXxxx : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count > 1) {
			LogUtil.info("insertXxxx : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * Xxxx Service
	 *   It processes the xxxx data.
	 */
	public void updateXxxx(XxxxValidator validator) throws Exception  {

		// Set Values to Database
		int count;
		try {
			count = mapper.updateXxxx(validator);
		} catch (DataIntegrityViolationException ex) {
			LogUtil.info("updateXxxx : 'This Xxxx already exists.', '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.XXXX_ERROR_TITLE_THIS_XXXX_ALREADY_EXISTS));
		} catch (Exception ex) {
			LogUtil.info("updateXxxx : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("updateXxxx : 'This Xxxx does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.XXXX_ERROR_TITLE_THIS_XXXX_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("updateXxxx : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}

	/**
	 * Xxxx Service
	 *   It processes the xxxx data.
	 */
	public void deleteXxxx(XxxxValidator validator) throws Exception {

		// Remove Values from Database
		int count;
		try {
			count = mapper.deleteXxxx(validator);
		} catch (Exception ex) {
			LogUtil.info("deleteXxxx : '" + ex.getMessage() + "'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}
		if (count == 0) {
			LogUtil.info("deleteXxxx : 'This Xxxx does not exist.'");
			throw new Exception(MessageUtil.getMessage(MessageUtil.XXXX_ERROR_TITLE_THIS_XXXX_DOES_NOT_EXIST));
		} else if (count > 1) {
			LogUtil.info("deleteXxxx : 'Assertion : Invalid count of manipulate data.'");
			throw new Exception(MessageUtil.COMMON_ERROR_STRING_UNKNOWN_TROUBLE_HAS_OCCURRED);
		}

		return;
	}
}
