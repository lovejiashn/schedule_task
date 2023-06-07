package com.jiashn.scheduleTask.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: jiangjs
 * @description: 上下文获取类
 * @date: 2023/1/30 10:28
 **/
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.context = applicationContext;
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }
}