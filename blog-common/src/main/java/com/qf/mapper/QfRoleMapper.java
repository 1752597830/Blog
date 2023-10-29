package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.domain.QfRole;
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
public interface QfRoleMapper extends BaseMapper<QfRole> {

    List<QfRole> getSelectedRolesByMenuId(@Param(value = "menuId")Long menuId);

    //List<QfRole> getRolesByUserId(@Param(value = "userId")Long userId);
    //
    //List<QfRole> getSelectedRolesByUserId(@Param(value = "userId")Long userId);

}
