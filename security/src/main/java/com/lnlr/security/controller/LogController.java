package com.lnlr.security.controller;

import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.security.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author:leihfei
 * @description: 日志记录控制器
 * @date:Create in 0:07 2018/11/10
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sys/log")
@Api(value = "日志管理", description = "权限管理系统日志管理")
@Slf4j
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping(value = "/page")
    @ResponseBody
    @ApiOperation(value = "分页查询数据")
    @ApiImplicitParam(name = "ngPager", value = "分页查询数据", dataTypeClass = NgPager.class, paramType = "query")
    public Response page(@RequestBody NgPager ngPager) {
        return new ObjectResponse<>(logService.page(ngPager));
    }
}
