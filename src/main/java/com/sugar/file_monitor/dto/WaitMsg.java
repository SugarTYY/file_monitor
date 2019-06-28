package com.sugar.file_monitor.dto;

import lombok.Data;

/**
 * 等待入队信息
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Data
public class WaitMsg {

    /** 开始等待时间. */
    private Long start;

    /** 等待信息. */
    private Object msg;

    public WaitMsg() {
    }

    public WaitMsg(Long start, Object msg) {
        this.start = start;
        this.msg = msg;
    }
}
