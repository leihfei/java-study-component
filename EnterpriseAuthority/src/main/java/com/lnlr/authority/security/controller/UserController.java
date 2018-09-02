package com.lnlr.authority.security.controller;

import com.lnlr.authority.security.pojo.master.dto.ValidateVO;
import com.lnlr.authority.security.service.UserService;
import com.lnlr.common.exception.ParamException;
import com.lnlr.common.utils.ApplicationContextHelper;
import com.lnlr.common.utils.BeanValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:leihfei
 * @description 权限管理系统用户控制层
 * @date:Create in 19:49 2018/8/30
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sysUser")
@Api(value = "系统用户", description = "系统用户控制器类")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询所有数据")
    @GetMapping(value = "findAll")
    public Object findAll() {
        return userService.findAll();
    }

    @ApiOperation(value = "查询所有数据")
    @PostMapping(value = "validate")
    @ApiImplicitParam(dataTypeClass = ValidateVO.class, paramType = "body")
    public Object validate(ValidateVO validateVO) throws ParamException {
        BeanValidator.check(validateVO);
        return userService.findAll();
    }

    @ApiOperation(value = "检查spring context 工具类")
    @GetMapping(value = "testContext")
    public Object testJson() {
        SimpleJpaRepository t = ApplicationContextHelper.popBean(SimpleJpaRepository.class);
        System.out.println(t.toString());
        return null;
    }
}
