package com.example.securityclass.controller;


import com.example.securityclass.entity.AxiosResult;
import com.example.securityclass.entity.SysPage;
import com.example.securityclass.entity.SysUser;
import com.example.securityclass.resolver.CurrentUser;
import com.example.securityclass.service.UserService;
import com.example.securityclass.vo.UserMenuVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 普通用户访问的接口
 */
@RestController
@RequestMapping("/user/api")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询用户具有的接口权限
     *
     * @param id 编号
     * @return {@link AxiosResult}<{@link Map}<{@link String}, {@link List<String>}>>
     */
    @GetMapping("/queryAuthoritiesByUserId/{id}")
    public AxiosResult<Map<String, List<String>>> queryAuthorities(@PathVariable String id) {
        List<String> authorities = userService.queryAuthoritiesByUserId(Integer.parseInt(id));
        Map<String, List<String>> map = new HashMap<>();
        map.put("authorities", authorities);
        return new AxiosResult<>(200, map, "查询成功");
    }

    /**
     * 按用户 ID 查询用户的接口和权限
     *
     * @param id 编号
     * @return {@link AxiosResult}<{@link Map}<{@link String}, {@link Object}>>
     */
    @GetMapping("/queryUserMenu/{id}")
    public AxiosResult<Map<String, Object>> queryUserMenu(@PathVariable String id) {
        List<UserMenuVO> roles = userService.queryUserMenu(Integer.parseInt(id));
        Map<String, Object> map = new HashMap<>();
        map.put("roles", roles);
        return new AxiosResult<>(200, map, "查询成功");
    }

    /**
     * 查询用户具有哪些页面权限
     *
     * @param sysUser 系统用户
     * @return {@link AxiosResult}<{@link Map}<{@link String},{@link List}<{@link SysPage}>>>
     */
    @GetMapping("/queryUserPageByUserId")
    public AxiosResult<Map<String, List<SysPage>>> queryUserPageByUserId(@CurrentUser SysUser sysUser) {
        List<SysPage> sysPages = userService.queryUserPageByUserId(sysUser.getId());
        Map<String, List<SysPage>> map = new HashMap<>();
        map.put("pages", sysPages);
        return new AxiosResult<>(200, map, "查询成功");
    }

    @GetMapping("/userInfo")
    public AxiosResult<Map<String, Object>> getUserInfo(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", sysUser);
        return new AxiosResult<>(200, map, "获取成功");
    }

}
