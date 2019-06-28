package com.sugar.file_monitor.service.impl;

import com.sugar.file_monitor.service.FileReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 文件服务
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Slf4j
@Service
public class FileReadServiceImpl implements FileReadService {
    @Override
    public void read(File file) {
        log.info("加载文件：{}", file);
    }

    @Override
    public void delete(File file) {
        log.info("删除文件：{}", file);
    }
}
