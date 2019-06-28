package com.sugar.file_monitor.file_monitor;

import com.sugar.file_monitor.constant.MonitorConstant;
import com.sugar.file_monitor.dto.FileMonitorDTO;
import com.sugar.file_monitor.dto.WaitMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * 文件监听器
 *
 * @author: Sugar
 * @create: 2019/6/20
 **/
@Slf4j
public class FileListener extends FileAlterationListenerAdaptor {


    /**
     * 文件创建
     * @param file
     * @return: void
     * @Date: 2019/6/20
     */
    @Override
    public void onFileCreate(File file) {
        log.debug("【文件监听】文件创建：{}", file.getName());
        WaitDelayPutQueue.INSTANCE.getWaitMap().put(file.getPath(),
                new WaitMsg(System.currentTimeMillis(), new FileMonitorDTO(MonitorConstant.EVENT_ADD,
                        MonitorConstant.EVENT_TYPE_FILE, file)));
    }

    /**
     * 文件改变
     * @param file
     * @return: void
     * @Date: 2019/6/20
     */
    @Override
    public void onFileChange(File file) {
        log.debug("【文件监听】文件改变：{}", file.getName());
        WaitDelayPutQueue.INSTANCE.getWaitMap().put(file.getPath(),
                new WaitMsg(System.currentTimeMillis(), new FileMonitorDTO(MonitorConstant.EVENT_CHANGE,
                        MonitorConstant.EVENT_TYPE_FILE, file)));
    }

    /**
     * 文件删除
     * @param file
     * @return: void
     * @Date: 2019/6/20
     */
    @Override
    public void onFileDelete(File file) {
        log.debug("【文件监听】文件删除：{}", file.getName());
        WaitDelayPutQueue.INSTANCE.getWaitMap().put(file.getPath(),
                new WaitMsg(System.currentTimeMillis(), new FileMonitorDTO(MonitorConstant.EVENT_DELETE,
                        MonitorConstant.EVENT_TYPE_FILE, file)));
    }
}
