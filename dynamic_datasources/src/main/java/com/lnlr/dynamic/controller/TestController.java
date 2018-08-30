package com.lnlr.dynamic.controller;

import com.lnlr.dynamic.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:leihfei
 * @description 测试多数据源
 * @date:Create in 11:31 2018/8/29
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/find")
    public List<Object> find() {
        return testService.find();
    }
}
