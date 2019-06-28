package com.sugar.file_monitor.file_monitor;

import com.sugar.file_monitor.config.FileConfig;
import com.sugar.file_monitor.constant.MonitorConstant;
import com.sugar.file_monitor.dto.FileMonitorDTO;
import com.sugar.file_monitor.dto.QueueMsg;
import com.sugar.file_monitor.dto.WaitMsg;
import com.sugar.file_monitor.service.FileReadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Slf4j
@Component
public class MonitorTask {

    @Autowired
    private FileConfig fileConfig;

    @Autowired
    private FileReadService fileReadService;


    /**
     * 开启文件监听
     *
     * @param
     * @return: void
     * @Date: 2019/6/20
     */
    @Async("monitorThreadPool")
    public void fileMonitor() {
        try {
            log.info("【监听文件事件】开启监听");
            FileAlterationObserver observer = new FileAlterationObserver(fileConfig.getLocation());
            observer.addListener(new FileListener());
            FileAlterationMonitor monitor = new FileAlterationMonitor(fileConfig.getInterval(), observer);
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 事件的处理
     *
     * @param
     * @return: void
     * @Date: 2019/6/20
     */
    @Async("monitorThreadPool")
    public void listenEvent() {
        log.info("【监听文件事件】等待事件处理");
        while (true) {
            try {
                QueueMsg queueMsg = (QueueMsg) LinkedQueue.INStANCE.take();
                log.debug("【监听文件事件】事件处理：\r\n{}", queueMsg);
                FileMonitorDTO msg = (FileMonitorDTO) queueMsg.getMsg();
                switch (msg.getEvent()) {
                    case MonitorConstant.EVENT_ADD:
                        this.fileReadService.read(msg.getFile());
                        break;
                    case MonitorConstant.EVENT_CHANGE:
                        this.fileReadService.read(msg.getFile());
                        break;
                    case MonitorConstant.EVENT_DELETE:
                        this.fileReadService.delete(msg.getFile());
                        break;
                    default:
                        log.info("【监听文件事件】事件处理，事件状态：UNKNOWN {}", msg.getEvent());
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 延时等待入队列
     * <p>
     * 避免可能出现短时重复修改文件导致频繁IO操作
     *
     * @param
     * @return: void
     * @Date: 2019/6/20
     */
    @Async("monitorThreadPool")
    public void delayPutQueue() {
        log.info("【监听文件事件】延时等待入队");
        Map<String, WaitMsg> map;
        while (true) {
            try {
                //超时入队列
                Map<String, WaitMsg> waitMap = WaitDelayPutQueue.INSTANCE.getWaitMap();
                map = new HashMap<>(waitMap);
                Iterator<Map.Entry<String, WaitMsg>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, WaitMsg> next = iterator.next();
                    long l = next.getValue().getStart() + this.fileConfig.getTaskwait();
                    if (System.currentTimeMillis() > l) {
                        log.debug("【监听文件事件】入队，file:{}", next.getKey());
                        LinkedQueue.INStANCE.put(next.getValue().getMsg());
                        waitMap.remove(next.getKey());
                    }
                }
                map = null;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
