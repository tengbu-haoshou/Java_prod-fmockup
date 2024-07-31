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
import org.apache.ibatis.annotations.Param;

import cn.com.xxxx.fmockup.entity.MenuEntity;

/**
 * Transaction List Mapper
 *   It manipulates the Transaciton data.
 */

@Mapper
public interface MenuMapper {

	List<MenuEntity> searchAllMenus(@Param("userId") String userId, @Param("langId") String langId);
}
