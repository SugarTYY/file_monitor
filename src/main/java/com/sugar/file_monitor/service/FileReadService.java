package com.sugar.file_monitor.service;

import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 文件服务
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Service
public interface FileReadService {

    /**
     * 文件读取
     * @param file
     * @return: void
     * @Date: 2019/6/20
     */
    void read(File file);

    /**
     * 文件删除
     * @param file
     * @return: void
     * @Date: 2019/6/20
     */
    void delete(File file);
}
