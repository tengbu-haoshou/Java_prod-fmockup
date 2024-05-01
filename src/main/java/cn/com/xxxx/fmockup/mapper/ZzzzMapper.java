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

import cn.com.xxxx.fmockup.entity.ZzzzEntity;
import cn.com.xxxx.fmockup.validator.ZzzzValidator;

/**
 * User Mapper
 *   It manipulates User data.
 */

@Mapper
public interface ZzzzMapper {

	List<ZzzzEntity> searchAllZzzzs();
	ZzzzEntity searchOneZzzz(ZzzzValidator validator);

	int insertZzzz(ZzzzValidator validator);
	int updateZzzz(ZzzzValidator validator);
	int deleteZzzz(ZzzzValidator validator);
}
