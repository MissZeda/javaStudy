package com.example.securityclass.controller;


import com.example.securityclass.entity.AxiosResult;
import com.example.securityclass.entity.SysMenu;
import com.example.securityclass.entity.SysUser;
import com.example.securityclass.service.SystemService;
import com.example.securityclass.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * admin用户才可以访问的接口
 */
@RestController
@RequestMapping("/admin/api")
public class AdminController {
    @Resource
    private UserService userService;

    @Resource
    private SystemService systemService;

    @GetMapping("/user/list")
    public AxiosResult<Map<String, Object>> getAllUserList(
            @RequestParam(value = "pageNum", required = false) String pageNum,
            @RequestParam(value = "pageSize", required = false) String pageSize) {

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            List<SysUser> sysUsers = userService.queryAllUser();
            PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
            long total = pageInfo.getTotal();
            Map<String, Object> map = new HashMap<>();
            map.put("users", sysUsers);
            map.put("total", total);
            return new AxiosResult<>(200, map, "查询成功");
        }
        List<SysUser> sysUsers = userService.queryAllUser();
        Map<String, Object> map = new HashMap<>();
        map.put("users", sysUsers);
        return new AxiosResult<>(200, map, "查询成功");
    }

    @PostMapping("/system/add")
    public AxiosResult<Map<String, String>> addNewInterface(@RequestBody SysMenu sysMenu) {
        if (systemService.addNewInterface(sysMenu)) {
            return new AxiosResult<>(200, "添加成功");
        }
        return new AxiosResult<>(50001, "添加失败");
    }

    @DeleteMapping("/system/delete/{id}")
    public AxiosResult<Map<String, String>> deleteInterface(@PathVariable String id) {
        if (systemService.deleteInterface(id)) {
            return new AxiosResult<>(200, "删除成功");
        }
        return new AxiosResult<>(50001, "删除失败");
    }

    @GetMapping("/system/distribute")
    public AxiosResult<Map<String, String>> distributeInterfaceAuthority() {
        if (systemService.distributeInterfaceAuthority()) {
            return new AxiosResult<>(200, "权限自动分发更新成功");
        }
        return new AxiosResult<>(50001, "权限分发更新失败");
    }
}
