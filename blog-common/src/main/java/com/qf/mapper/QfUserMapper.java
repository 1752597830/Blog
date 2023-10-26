package com.qf.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qf.domain.QfUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QfUserMapper extends BaseMapper<QfUser> {

    // 获取用户信息以及用户Role List
    QfUser getByUsername(@Param("username") String username);

    IPage<QfUser> listPage(IPage<QfUser> page, @Param(Constants.WRAPPER) Wrapper<QfUser> queryWrapper);
}