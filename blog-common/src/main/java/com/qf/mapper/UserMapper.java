package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.qf.domain.User;
import org.apache.ibatis.annotations.*;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 *  用户 Mapper 接口
 *
 * @author zqd
 *
 * @date 2023-07-05 13:47:14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getByUsername(@Param("username") String username);

    IPage<User> listPage(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}
