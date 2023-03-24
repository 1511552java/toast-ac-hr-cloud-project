package com.toast.endpoint.controller.abs;

import com.toast.common.controller.abs.AbstractBaseController;
import com.toast.jwt.constant.ToastConst;
import com.toast.jwt.service.ITokenService;
import com.toast.jwt.util.JWTMemberDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe 负责处理本模块中Controller的公共操作类
 */
public class AbstractEndpointController extends AbstractBaseController {
    @Autowired
    protected JWTMemberDataService jwtMemberDataService;
    @Autowired
    protected ITokenService tokenService;

    /**
     * 通过JWT获取保存的mid数据
     */
    public String mid() {
        return this.jwtMemberDataService.mid(token());
    }

    /**
     * 通过JWT获取name数据
     */
    public String name() {
        return this.jwtMemberDataService.name(token());
    }

    /**
     * 获取token数据
     */
    public String token() {
        return this.jwtMemberDataService.getToken(this.request(), ToastConst.TOKEN_NAME);
    }

    /**
     * 刷新token,获取新的token
     */
    public String refresh() {
        return this.tokenService.refreshToken(this.token());
    }

    /**
     * 获取 HttpServletRequest 对象实例
     */
    public HttpServletRequest request() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();		// 获取请求属性
        HttpServletRequest request = attributes.getRequest();	// 获取Request
        return request;
    }

    /**
     * 获取 HttpServletResponse 对象实例
     */
    public HttpServletResponse response() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();		// 获取请求属性
        HttpServletResponse response = attributes.getResponse();	// 获取Response
        return response;
    }
}
