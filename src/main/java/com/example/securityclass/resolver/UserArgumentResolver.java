package com.example.securityclass.resolver;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.securityclass.entity.SysUser;
import com.example.securityclass.util.JwtUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    // * 该类是查看是否使用了注解，如果使用了指定的注解就返回true，然后到下方的自定义参数解析，一般是使用注解来标识，因为方便
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // * 检查是否存在标识的注解
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    // * 进行参数解析的方法，可以在方法中获取相对应的参数
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        String token = nativeWebRequest.getHeader("Authorization");
        if (StringUtils.hasText(token)) {
            // 解析token，获取user
            JwtUtils.tokenVerify(token);
            JSONObject jsonObject = JSON.parseObject(JwtUtils.parseToken(token));
            return JSON.parseObject(jsonObject.getString("principal"), SysUser.class);
        }
        return null;
    }
}

