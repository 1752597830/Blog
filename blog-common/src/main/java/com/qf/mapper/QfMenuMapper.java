package com.qf.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qf.domain.QfMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sin
 * @since 2023/10/26 17:08
 */
@Mapper
public interface QfMenuMapper extends BaseMapper<QfMenu> {

    List<QfMenu> listMenu(@Param(Constants.WRAPPER) Wrapper wrapper);

    IPage<QfMenu> listPage(IPage<QfMenu> page, @Param(Constants.WRAPPER) Wrapper<QfMenu> queryWrapper);

    QfMenu selectByParentId(@Param("pid") Long pid);

    QfMenu selectByroleId(@Param("roleId") int roleId);
}
