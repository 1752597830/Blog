package com.qf.controller;

import com.qf.domain.ResponseResult;
import com.qf.service.QfMenuService;
import com.qf.utils.RouteTools;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : sin
 * @date : 2023/10/31 16:26
 * @Description :
 */

@Tag(name = "路由")
@RestController
public class RouteController {

    @Autowired
    QfMenuService qfMenuService;

    @Autowired
    RouteTools routeTools;

    @GetMapping("/getRoutes")
    public ResponseResult getRoutes() {
        //return new ResponseResult().setData(routeTools.buildRouteTree()).setCode(200).setMessage("success");
        return null;
    }
}