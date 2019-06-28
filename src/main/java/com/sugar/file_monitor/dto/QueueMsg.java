package com.sugar.file_monitor.dto;

import lombok.Data;

/**
 * 队列信息
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Data
public class QueueMsg {

    private Integer sequence;

    private Object msg;

    public QueueMsg() {
    }

    public QueueMsg(Integer sequence, Object msg) {
        this.sequence = sequence;
        this.msg = msg;
    }
}
