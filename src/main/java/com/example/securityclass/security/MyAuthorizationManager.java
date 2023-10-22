package com.example.securityclass.security;

import com.example.securityclass.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

// 动态权限配置
@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {


    @Resource
    private UserService userService;

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }


    // 这里是校验其他接口的访问权限，在securityConfig当中的最后的配置
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        // 我们可以获取原始request对象
        HttpServletRequest request = requestAuthorizationContext.getRequest();
        // 访问的URL
        String requestURI = request.getRequestURI();

        // 我们可以获取携带的参数
        Map<String, String> variables = requestAuthorizationContext.getVariables();

        // 根据这些信息 和业务写逻辑即可 最终决定是否授权 isGranted
        boolean isGranted = true;
        System.out.println(requestURI);
        // 请求路径为login和register直接放行
        if (requestURI.equals("/login")) {
            return new AuthorizationDecision(true);
        }
        // 获取到当前用户所具备的权限
        Collection<? extends GrantedAuthority> collect = authentication.get().getAuthorities();
        List<String> authorities = collect.stream().map((Function<GrantedAuthority, String>) GrantedAuthority::getAuthority).toList();
        System.out.println("authorities = " + authorities);
        // 获取用户请求该路径的权限
        List<String> permissionByUrl = userService.queryPermissionByUrl(requestURI);
        // 没有根据url查询出匹配的权限或者用户的权限不包含在里面，则拒绝访问
        if (permissionByUrl.isEmpty() || !new HashSet<>(authorities).containsAll(permissionByUrl)) {
            throw new AccessDeniedException("权限不足");
        }
        return new AuthorizationDecision(isGranted);
    }
}
