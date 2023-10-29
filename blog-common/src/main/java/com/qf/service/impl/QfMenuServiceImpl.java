package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.domain.QfMenu;
import com.qf.mapper.QfMenuMapper;
import com.qf.service.QfMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author : sin
 * @date : 2023/10/31 15:00
 * @Description :
 */

@Service
public class QfMenuServiceImpl implements QfMenuService {

    @Autowired
    QfMenuMapper qfMenuMapper;

    @Override
    public List<QfMenu> listMenu(QueryWrapper<QfMenu> eq) {
        return qfMenuMapper.listMenu(eq);
    }

    @Override
    public List<QfMenu> listByuId(Integer uId) {
        return qfMenuMapper.listByuId(uId);
    }

    @Override
    public List<QfMenu> getRoutesById(Integer parentId, Integer rId) {
        return qfMenuMapper.getRoutesById(parentId, rId);
    }
}