package com.starfish.notify.service;

/**
 * TaskLock
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-04
 */
public interface TaskLock extends java.io.Serializable {

    /**
     * 获取任务锁，在指定时间只能获取一个
     *
     * @param time     锁定时间
     * @param duration 持续时长
     * @return 结果
     */
    Boolean lock(long time, long duration);

}
