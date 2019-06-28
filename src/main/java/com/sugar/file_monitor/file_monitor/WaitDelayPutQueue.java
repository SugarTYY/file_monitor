package com.sugar.file_monitor.file_monitor;

import com.sugar.file_monitor.dto.WaitMsg;

import java.util.Hashtable;
import java.util.Map;

/**
 * 等待入队集合
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
public enum WaitDelayPutQueue {
    /** 单实例. */
    INSTANCE;

    private Map<String, WaitMsg> waitMap = new Hashtable<>(16);

    public synchronized Map<String, WaitMsg> getWaitMap() {
        return waitMap;
    }
}
