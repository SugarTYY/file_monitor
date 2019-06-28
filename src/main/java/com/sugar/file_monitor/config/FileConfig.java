package com.sugar.file_monitor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件配置
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Data
@Component
@ConfigurationProperties(prefix = "fileconf")
public class FileConfig {

    /** 监测路径. */
    private String location;

    /** 监测触发间隔毫秒. */
    private Long interval = 100L;

    /** 任务执行等待时间，单位秒. */
    private Long taskwait = 10L;

    public Long getTaskwait() {
        return taskwait * 1000;
    }
}
