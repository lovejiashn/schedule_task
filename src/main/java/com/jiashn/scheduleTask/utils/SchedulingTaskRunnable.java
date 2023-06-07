package com.jiashn.scheduleTask.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: jiangjs
 * @description: 实现定时任务线程
 * @date: 2023/2/16 15:31
 **/
@Slf4j
@EnableAsync
public class SchedulingTaskRunnable<T> implements Runnable {
    /**
     * 其他参数
     */
    private final T other;
    /**
     * 定时任务类
     */
    private final String clazz;

    /**
     * 定时任务方法
     */
    private final String methodName;

    public SchedulingTaskRunnable(T other, String clazz, String methodName){
        this.other = other;
        this.clazz = clazz;
        this.methodName = methodName;
    }

    @Override
    public void run() {
        Object bean = SpringContextUtils.getBean(clazz);
        Method method;
        try{
            method = Objects.nonNull(other) ? bean.getClass().getDeclaredMethod(methodName,other.getClass()) :
                    bean.getClass().getDeclaredMethod(methodName);
            ReflectionUtils.makeAccessible(method);
            if (Objects.nonNull(other)){
                method.invoke(bean,other);
            } else {
                method.invoke(bean);
            }
        }catch (Exception e){
            log.error("获取方法信息报错：{}",e.getMessage());
        }
    }
}
