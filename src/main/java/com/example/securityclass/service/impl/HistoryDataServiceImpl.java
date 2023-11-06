package com.example.securityclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.securityclass.entity.Historydata;
import com.example.securityclass.mapper.HistoryDataMapper;
import com.example.securityclass.service.HistoryDataService;
import org.springframework.stereotype.Service;

@Service
public class HistoryDataServiceImpl extends ServiceImpl<HistoryDataMapper, Historydata> implements HistoryDataService {
}
