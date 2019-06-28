package com.sugar.file_monitor.constant;

/**
 * 监控事件常量
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
public interface MonitorConstant {

    //事件类型
    String EVENT_TYPE_FOLDER = "folder";
    String EVENT_TYPE_FILE = "file";

    //事件
    int EVENT_ADD = 1;
    int EVENT_CHANGE = 2;
    int EVENT_DELETE = 0;

}
