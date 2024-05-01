//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.com.xxxx.fmockup.entity.XxxxEntity;
import cn.com.xxxx.fmockup.validator.XxxxValidator;

/**
 * User Mapper
 *   It manipulates User data.
 */

@Mapper
public interface XxxxMapper {

	List<XxxxEntity> searchAllXxxxs();
	XxxxEntity searchOneXxxx(XxxxValidator validator);

	int insertXxxx(XxxxValidator validator);
	int updateXxxx(XxxxValidator validator);
	int deleteXxxx(XxxxValidator validator);
}
