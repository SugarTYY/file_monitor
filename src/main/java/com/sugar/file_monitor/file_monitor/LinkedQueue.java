package com.sugar.file_monitor.file_monitor;

import com.sugar.file_monitor.dto.QueueMsg;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 事件队列
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
public enum LinkedQueue {
    /** 单实例. */
    INStANCE;

    private LinkedBlockingQueue queue = new LinkedBlockingQueue();
    private Integer sequence = 0;

    /**
     * 添加队列
     *
     * 返回队列序列号
     * @param obj
     * @Date: 2019/6/20
     */
    public void put(Object obj) throws InterruptedException {
        this.sequence++;
        QueueMsg msg = new QueueMsg(sequence, obj);
        this.queue.put(msg);
    }

    /**
     * 消费信息
     *
     * 消费后信息消除
     * @param
     * @return: com.sugar.file_monitor.dto.QueueMsg
     * @Date: 2019/6/20
     */
    public QueueMsg take() throws InterruptedException {
        return  (QueueMsg) this.queue.take();
    }

}
