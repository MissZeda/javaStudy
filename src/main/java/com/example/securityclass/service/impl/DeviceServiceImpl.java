package com.example.securityclass.service.impl;

import com.example.securityclass.entity.Device;
import com.example.securityclass.mapper.DeviceMapper;
import com.example.securityclass.service.DeviceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAllDevice();
    }
}
