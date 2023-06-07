package com.jiashn.scheduleTask.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author: jiangjs
 * @description: 定时任务管理方法
 * @date: 2023/2/16 15:48
 **/
@Component
@Slf4j
public class SchedulingTaskManage {

    /**
     * 将任务放入map便于管理
     */
    public static ConcurrentHashMap<String, ScheduledFuture<?>> cache = new ConcurrentHashMap<>();

    @Resource(name = "taskSchedulerPool")
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 删除定时任务
     * @param key 自定义定时任务名称
     */
    public void stopSchedulingTask(String key){
        if (Objects.isNull(cache.get(key))){
            log.info(String.format(".......当前key【%s】没有定时任务......",key));
            return;
        }
        ScheduledFuture<?> future = cache.get(key);
        if (Objects.nonNull(future)){
            //关闭当前定时任务
            future.cancel(Boolean.TRUE);
            cache.remove(key);
            log.info(String.format("删除【%s】对应定时任务成功",key));
        }
    }

    /**
     * 创建定时任务
     * @param key 任务key
     * @param runnable 当前线程
     * @param cron 定时任务cron
     */
    public void createSchedulingTask(String key, SchedulingTaskRunnable runnable, String cron){
        this.stopSchedulingTask(key);
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, new CronTrigger(cron));
        assert schedule != null;
        cache.put(key,schedule);
        log.info(String.format("【%s】创建定时任务成功",key));
    }
}
