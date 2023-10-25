package com.qf.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qf.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listMenu(@Param(Constants.WRAPPER) Wrapper wrapper);

    IPage<Menu> listPage(IPage<Menu> page, @Param(Constants.WRAPPER) Wrapper<Menu> queryWrapper);

    Menu selectByParentId(@Param("pid") Long pid);
}
