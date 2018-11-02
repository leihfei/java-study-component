package com.lnlr.security.pojo.master.vo;

import com.lnlr.security.pojo.master.vo.user.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:leihfei
 * @description 登录成功返回前端数据
 * @date:Create in 13:02 2018/9/17
 * @email:leihfein@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultVO {

    private UserVO user;

    private String token;

    private String menus;
}
