package com.example.securityclass.mapper;

import com.example.securityclass.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceMapper {
    List<Device> findAllDevice();
}
