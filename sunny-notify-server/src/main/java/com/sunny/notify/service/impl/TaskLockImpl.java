package com.sunny.notify.service.impl;

import com.sunny.notify.service.PrefixService;
import com.sunny.notify.service.TaskLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TaskLockImpl
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-04
 */
@Slf4j
//@Service
public class TaskLockImpl implements TaskLock {

    @Resource
    private PrefixService prefixService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Boolean lock(long time, long duration) {
        // 判断当前时间是否过期，如果过期直接返回false
        Date currentTime = new Date();
        Date expireTime = new Date(time + duration);
        if (expireTime.before(currentTime)) {
            return false;
        }

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String key = prefixService.getString() + "sunny_notify_task_lock_" + format.format(new Date(time));
        Boolean result = valueOperations.setIfAbsent(key, "1");

        // 设置过期时间
        if (result) {
            valueOperations.getOperations().expireAt(key, expireTime);
        }

        return result;
    }

}
