#!/usr/bin/env bash

# 常用arthas命令

trace com.sunny.notify.service.impl.NotifyServiceImpl retry

trace com.sunny.notify.service.impl.NotifyServiceImpl$NotifyRunnable run
