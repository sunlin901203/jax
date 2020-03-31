package com.starfish.notify.task;

import com.starfish.notify.service.NotifyService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * NotifyTask
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-03
 */
@Slf4j
@Component
@JobHandler(value = "notifyJobHandler")
public class NotifyJobHandler extends IJobHandler {

    @Resource
    private NotifyService notifyService;

//    @Resource
//    private TaskLock taskLock;

    /**
     * 每5分钟执行一次
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void execute() {
//        // 获取锁
//        Date now = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        long lockTime = calendar.getTime().getTime();
//        Boolean result = taskLock.lock(lockTime, 5 * 60 * 1000);
//        if (!result) {
//            return;
//        }
//
//        log.info("current app get the lock.");
//
//        notifyService.notifyTask();
//    }
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        // 打印日志需要用XxlJobLogger
        XxlJobLogger.log("NotifyTask execute start.param={}", s);
        notifyService.notifyTask();
        XxlJobLogger.log("NotifyTask execute end.param={}", s);
        return ReturnT.SUCCESS;
    }

}
