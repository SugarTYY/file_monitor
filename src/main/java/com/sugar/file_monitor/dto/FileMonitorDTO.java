package com.sugar.file_monitor.dto;

import lombok.Data;

import java.io.File;

/**
 * 文件监控传输信息
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Data
public class FileMonitorDTO {

    /** 事件. */
    private int event;

    /** 事件类型. */
    private String eventType;

    /** 文件. */
    private File file;

    public FileMonitorDTO() {
    }

    public FileMonitorDTO(int event, String eventType, File file) {
        this.event = event;
        this.eventType = eventType;
        this.file = file;
    }
}
