package com.lnlr.security.controller;

import com.lnlr.common.constains.ApplicationConstants;
import com.lnlr.common.constains.SystemConstants;
import com.lnlr.common.jwt.Audience;
import com.lnlr.common.jwt.JwtUtils;
import com.lnlr.common.response.FailedResponse;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.common.response.SuccessResponse;
import com.lnlr.common.utils.*;
import com.lnlr.security.pojo.master.entity.SysUser;
import com.lnlr.security.pojo.master.param.LoginParam;
import com.lnlr.security.pojo.master.vo.LoginResultVO;
import com.lnlr.security.pojo.master.vo.user.UserVO;
import com.lnlr.security.service.SysUserService;
import com.lnlr.security.service.impl.DefaultUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.lnlr.common.response.Response.*;

/**
 * @author:leihfei
 * @description 登录控制器
 * @date:Create in 17:41 2018/9/14
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sys/authorizing")
@Api(value = "登录管理", description = "权限管理系统登录管理")
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private Audience audience;

    @Autowired
    private DefaultUrlService defaultUrlService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping(value = "/logout")
    @ApiOperation(value = "退出登录")
    public Response logout() {
        // 清理redis缓存
        return new SuccessResponse("退出成功!");
    }

    @PostMapping(value = "/login")
    @ResponseBody
    @ApiOperation(value = "登录")
    @ApiImplicitParam(name = "loginParam", value = "登录用户信息", required = true, dataTypeClass = LoginParam.class, paramType = "query")
    public Response login(@RequestBody LoginParam loginParam, HttpServletRequest request) {
        log.info("IP:{} 尝试登录，登录数据:{}", IpUtils.getIpAddr(request), loginParam);
        Response ret = null;
        if (StringUtils.isEmpty(loginParam.getUsername())) {
            return new FailedResponse<>("用户名为空!");
        } else if (StringUtils.isEmpty(loginParam.getPassword())) {
            return new FailedResponse<>("密码为空!");
        }
        SysUser user = userService.findKeyword(loginParam.getUsername());
        if (user == null) {
            ret = new FailedResponse<>("用户不存在!");
        } else {
            // 当用户存在的时候
            if (user.getStatus() != ApplicationConstants.ENABLED) {
                // 用户被锁定
                ret = new FailedResponse<>("用户被锁定!");
            } else {
                // 判断密码是否相等匹配
                boolean validate = PassUtils.validatePassword(loginParam.getPassword(),
                        user.getPassword(), user.getSalt());
                if (!validate) {
                    ret = new FailedResponse<>("用户名或密码错误!");
                } else {
                    // 登录成功数据，返回jwt,返回菜单数据，返回权限数据
                    ret = loginSuccess(user);
                }
            }
        }
        log.info("登录返回信息：{}", ret);
        return ret;
    }

    /**
     * @param user 登录user
     * @return com.lnlr.common.response.Response
     * @author: leihfei
     * @description 返回登录用户的权限，菜单，jwt等数据
     * @date: 0:34 2018/10/19
     * @email: leihfein@gmail.com
     */
    private Response loginSuccess(SysUser user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        // 返回jwt数据
        LoginResultVO vo = new LoginResultVO(userVO, createToken(userVO.getUsername(), String.valueOf(userVO.getId())), "menus");
        redisUtil.set(String.valueOf(user.getId()), JsonUtils.object2Json(user));
        return new ObjectResponse<>(vo);
    }

    /**
     * 未登录调到此处
     *
     * @return
     */
    @GetMapping(value = "/unauth")
    public Response unauth() {
        return new FailedResponse<>(NO_LOGIN, "没有登录，请登录!");
    }

    /**
     * 重新生成token
     *
     * @return
     */
    @GetMapping(value = "/create_tokem")
    public Response create_tokem(HttpServletRequest request) {
        String userId = (String) request.getAttribute(SystemConstants.JWT_LOGIN_USER);
        String userName = (String) request.getAttribute(SystemConstants.JWT_LOGIN_USERNAME);
        String token = createToken(userName, userId);
        log.info("重新生成token信息:{}", token);
        return new ObjectResponse<>(AGAIN_TOKEN, token);
    }

    private String createToken(String userName, String userId) {
        String jwt = JwtUtils.createJWT(
                userName,
                userId,
                audience.getClientId(),
                audience.getName(),
                audience.getExpiresSecond(),
                audience.getBase64Secret());
        return SystemConstants.TOKEN_TYPE.concat(jwt);
    }

    /**
     * 当没有权限访问时进入此处
     *
     * @return
     */
    @GetMapping(value = "/unauthorized")
    public Response unauthorized() {
        return new FailedResponse<>(NO_PRESSION, "您没有权限访问!");
    }


    /**
     * 对拦截过滤重新进行赋值
     * 刷新拦截过滤url
     *
     * @return
     */
    @GetMapping(value = "flush")
    public Response flush() {
        ExcludePathUtils.paths = defaultUrlService.findAll();
        return new ObjectResponse<>(ExcludePathUtils.paths);
    }
}
