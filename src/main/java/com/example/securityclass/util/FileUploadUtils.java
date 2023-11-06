package com.example.securityclass.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

public class FileUploadUtils {

    public static String uploadFile(MultipartFile file, String uploadDirectory) {
        try {
            if (!file.isEmpty()) {
                // 获取当前日期
                LocalDate currentDate = LocalDate.now();
                String year = String.valueOf(currentDate.getYear());
                String month = String.valueOf(currentDate.getMonthValue());
                String day = String.valueOf(currentDate.getDayOfMonth());

                // 创建目标文件夹路径
                String targetDirectory = uploadDirectory + File.separator + year + File.separator + month + File.separator + day;
                String databaseDirectory = "/upload" + File.separator + year + File.separator + month + File.separator + day;
                // 创建目标文件夹（如果不存在）
                Path directoryPath = Paths.get(targetDirectory);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                // 生成随机的UUID文件名
                String originalFilename = file.getOriginalFilename();
                String extension = StringUtils.getFilenameExtension(originalFilename);
                String newFilename = UUID.randomUUID().toString() + "." + extension;

                // 保存文件到目标路径
                String filePath = targetDirectory + File.separator + newFilename;
                Path targetPath = Paths.get(filePath);
                Files.write(targetPath, file.getBytes());

                // 返回文件保存的路径
                return databaseDirectory + File.separator + newFilename;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}