package com.example.securityclass.task;

import com.example.securityclass.entity.AxiosResult;
import com.example.securityclass.entity.Device;
import com.example.securityclass.service.DeviceService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
@EnableAsync
public class AsyncTask {

    @Resource
    private DeviceService deviceService;

    @Async
    public Future<String> task4() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread.sleep(2000L);
        long end = System.currentTimeMillis();
        System.out.println("执行时间4=" + (end - begin));
        return new AsyncResult<>("成功执行4");
    }

    @Async
    public Future<String> task5() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread.sleep(3000L);
        long end = System.currentTimeMillis();
        System.out.println("执行时间5=" + (end - begin));
        return new AsyncResult<>("成功执行5");
    }

    @Async
    public Future<String> task6() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread.sleep(1000L);
        long end = System.currentTimeMillis();
        System.out.println("执行时间6=" + (end - begin));
        return new AsyncResult<>("成功执行6");
    }

    // 查询设备使用异步任务CompletableFuture
    @Async
    public CompletableFuture<AxiosResult<List<Device>>> task7() {
        // 任务开始时记录系统时间
        long begin = System.currentTimeMillis();
        return CompletableFuture.supplyAsync(() -> {
            List<Device> devices = deviceService.findAllDevice();
            long end = System.currentTimeMillis();
            System.out.println("执行时间7=" + (end - begin));
            return new AxiosResult<>(0, devices, "查询成功");
        });

    }
}
