//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Author  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.com.xxxx.fmockup.entity.YyyyEntity;
import cn.com.xxxx.fmockup.validator.YyyyValidator;

/**
 * User Mapper
 *   It manipulates User data.
 */

@Mapper
public interface YyyyMapper {

	List<YyyyEntity> searchAllYyyys();
	YyyyEntity searchOneYyyy(YyyyValidator validator);

	int insertYyyy(YyyyValidator validator);
	int updateYyyy(YyyyValidator validator);
	int deleteYyyy(YyyyValidator validator);
}
