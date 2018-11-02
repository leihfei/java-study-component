package com.lnlr.security;

import com.lnlr.common.utils.JsonUtils;
import com.lnlr.security.pojo.master.entity.SysUser;

import java.time.LocalDateTime;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 15:53 2018/10/26
 * @email:leihfein@gmail.com
 */
public class TestJson {
    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setOperatorTime(LocalDateTime.now());
        String s = JsonUtils.object2Json(sysUser);
        System.out.println(s);
        SysUser sysUser1 = JsonUtils.json2Object(s, SysUser.class);
        System.out.println(sysUser1);
    }
}
