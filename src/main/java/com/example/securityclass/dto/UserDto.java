package com.example.securityclass.dto;

import com.example.securityclass.entity.SysUser;
import lombok.Data;

@Data
public class UserDto extends SysUser {

    private String code;
}
