package com.sugar.file_monitor.file_monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 监听
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Slf4j
@Component
public class Monitor {

    @Autowired
    private MonitorTask monitorTask;

    /**
     * 开始监听
     * @param
     * @return: void
     * @Date: 2019/6/20
     */
    @PostConstruct
    private void executeMonitor() throws InterruptedException {
        this.monitorTask.fileMonitor();

        this.monitorTask.listenEvent();

        this.monitorTask.delayPutQueue();
    }


}
