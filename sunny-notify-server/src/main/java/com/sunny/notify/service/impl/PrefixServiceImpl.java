package com.sunny.notify.service.impl;

import com.sunny.notify.service.PrefixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * PrefixServiceImpl
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-08
 */
@Slf4j
@Service
public class PrefixServiceImpl implements PrefixService {

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getString() {
        return profile + ":" + appName + ":";
    }

}
