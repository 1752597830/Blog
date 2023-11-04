package com.qf.utils;

import com.qf.domain.QfMenu;
import com.qf.service.QfMenuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : sin
 * @date : 2023/10/31 14:53
 * @Description : 路由构建
 */
@Slf4j
@Component("routeTools")
public class RouteTools {
    
    @Resource
    QfMenuService qfMenuService;
    
    
    public List<QfMenu> buildRouteTree(Integer uId){
        // 获取用户的菜单权限
        List<QfMenu> menus = qfMenuService.listByuId(uId);
        //将获取的菜单转换成前端route格式
        return buildTree(menus);
    }
    /**
     * List转成树结构
     */
    public List<QfMenu> buildTree(List<QfMenu> lisy) {
        //先选出非顶级的节点
        List<QfMenu> list = lisy.stream().filter(node -> node.getParentId() != 0L).collect(Collectors.toList());
        //将这些非顶级节点的数据按pid进行分组
        Map<Integer, List<QfMenu>> sub = list.stream().collect(Collectors.groupingBy(QfMenu::getParentId));
        //循环设置对应的子节点（根据id = pid）
        lisy.forEach(node -> node.setChildren(sub.get(node.getId())));
        //过滤掉父节点数据
        List<QfMenu> m = lisy.stream().filter(node -> node.getParentId() == 0L).collect(Collectors.toList());
        List<QfMenu> s = new ArrayList<>();
        for (QfMenu qfMenu : m) {
            s.add(qfMenu);
        }
        System.out.println(s);
        return s;
    }

    public List<String> getPermissions(Integer id) {
        List<String> list = qfMenuService.listPermissByuId(id);
        return list;
    }
}