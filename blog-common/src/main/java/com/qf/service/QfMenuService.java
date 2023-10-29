package com.qf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.domain.QfMenu;

import java.util.List;


public interface QfMenuService {

    List<QfMenu> listMenu(QueryWrapper<QfMenu> eq);
    List<QfMenu> listByuId(Integer uId);

    public List<QfMenu> getRoutesById(Integer parentId, Integer rId);
}
