package com.starfish.notify.service;

import com.starfish.notify.model.NotifyModel;

/**
 * NotifyService
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-03
 */
public interface NotifyService {

    /**
     * 保存通知
     *
     * @param notifyModel 参数
     * @return 结果
     */
    Long insert(NotifyModel notifyModel);

    /**
     * 通知定时任务
     */
    void notifyTask();

    /**
     * 通知
     *
     * @param notifyModel 参数
     */
    void notify(NotifyModel notifyModel);

    /**
     * 重新通知
     *
     * @param notifyModel 参数
     */
    void retry(NotifyModel notifyModel);

}
