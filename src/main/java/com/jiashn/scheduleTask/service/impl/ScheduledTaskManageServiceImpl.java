package com.jiashn.scheduleTask.service.impl;

import com.jiashn.scheduleTask.domain.ScheduleTask;
import com.jiashn.scheduleTask.domain.UserInfo;
import com.jiashn.scheduleTask.mapper.ScheduleTaskMapper;
import com.jiashn.scheduleTask.service.ScheduledTaskManageService;
import com.jiashn.scheduleTask.utils.ResultUtil;
import com.jiashn.scheduleTask.utils.SchedulingTaskManage;
import com.jiashn.scheduleTask.utils.SchedulingTaskRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/1/12 10:53
 **/
@Service
public class ScheduledTaskManageServiceImpl implements ScheduledTaskManageService {
    @Autowired
    private SchedulingTaskManage taskManage;
    @Resource
    private ScheduleTaskMapper scheduleTaskMapper;

    @Override
    public ResultUtil<?> addTask(ScheduleTask task) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("张三");
        userInfo.setPassword("121212121212");
        String cron = task.getCron();
        boolean validExpression = CronExpression.isValidExpression(cron);
        if (!validExpression){
            return ResultUtil.error("无效的cron格式，请重新填写");
        }
        scheduleTaskMapper.insert(task);
        SchedulingTaskRunnable<UserInfo> taskRunnable = new SchedulingTaskRunnable<>(userInfo, task.getTaskClazz(), task.getTaskMethod());
        taskManage.createSchedulingTask(String.valueOf(task.getId()), taskRunnable,task.getCron());
        return ResultUtil.success();
    }

    @Override
    public ResultUtil<?> deleteTask(Integer id) {
        scheduleTaskMapper.deleteById(id);
        taskManage.stopSchedulingTask(String.valueOf(id));
        return ResultUtil.success();
    }

    @Override
    public ResultUtil<?> editTask(ScheduleTask task) {
        scheduleTaskMapper.updateById(task);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("张三");
        userInfo.setPassword("33333333");
        SchedulingTaskRunnable<UserInfo> taskRunnable = new SchedulingTaskRunnable<>(userInfo, task.getTaskClazz(), task.getTaskMethod());
        taskManage.createSchedulingTask(String.valueOf(task.getId()), taskRunnable,task.getCron());
        return ResultUtil.success();
    }
}