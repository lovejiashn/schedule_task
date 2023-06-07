package com.jiashn.scheduleTask.service;

import com.jiashn.scheduleTask.domain.ScheduleTask;
import com.jiashn.scheduleTask.utils.ResultUtil;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/1/12 10:52
 **/
public interface ScheduledTaskManageService {
    /**
     * 添加任务
     * @param task 定时任务实体
     * @return 返回添加结果
     */
    ResultUtil<?> addTask(ScheduleTask task);
    /**
     * 删除任务
     * @param id 主键Id
     * @return 返回添加结果
     */
    ResultUtil<?> deleteTask(Integer id);
    /**
     * 更新任务
     * @param task 定时任务实体
     * @return 返回添加结果
     */
    ResultUtil<?> editTask(ScheduleTask task);
}
